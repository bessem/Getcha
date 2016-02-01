package pirana.aka.getcha.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pirana.aka.getcha.model.Todo;

/**
 * Created by aka on 2/1/16.
 */
public class TodoOperations extends SQLiteOpenHelper {
    private Context mCtx;
    private static TodoOperations mInstance = null;
    final static String TODO_DATA_BASE = "TODOSDB";
    final static String TODO_TABLE = "TODOS";
    final public String ID = "ID";
    final public String TITLE = "TITLE";
    public static final int VERSION = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY   AUTOINCREMENT, " + TITLE + " TEXT );";

    public static TodoOperations getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new TodoOperations(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private TodoOperations(Context context) {

        super(context, TODO_DATA_BASE, null, VERSION);
        this.mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        System.out.println("data Base Operation:Table Created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        //onCreate(db);
    }

    // Adding new Todo
    public boolean addTodo(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(TITLE, todo.getTitle());
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TODO_TABLE, null, values);
            return true;
        } catch (SQLiteException e) {
            return false;
        }
    }

    // Getting single Todo
    public Todo getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TODO_TABLE, new String[]{ID,
                TITLE}, ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Todo todo = new Todo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return todo;
    }

    // Getting All Todos
    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        String selectQuery = "SELECT  * FROM " + TODO_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        return todos;
    }

    // Getting Todos Count
    public int getTodosCount() {
        String countQuery = "SELECT  * FROM " + TODO_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating single Todo
    public int updateTodo(Todo todo) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TITLE, todo.getTitle());
            // updating row
            return db.update(TODO_TABLE, values, ID + " = ?",
                    new String[]{String.valueOf(todo.get_id())});
        } catch (SQLiteException e) {
            return 0;
        }
    }

    // Deleting
    public void deleteTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TODO_TABLE, ID + " = ?",
                new String[]{String.valueOf(todo.get_id())});
        db.close();
    }
}

