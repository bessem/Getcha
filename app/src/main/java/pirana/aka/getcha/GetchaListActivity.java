package pirana.aka.getcha;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import pirana.aka.getcha.db.TodoOperations;
import pirana.aka.getcha.model.Todo;

public class GetchaListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_getcha_list);
        TodoOperations opInstance = TodoOperations.getInstance(getApplicationContext());
       /* final String[] MOBILE_OS =
                new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
        for (int i =0;i<MOBILE_OS.length;i++) {
            Todo todo = new Todo(i,MOBILE_OS[i]);
            opInstance.addTodo(todo);
        }*/
        setListAdapter(new TodosArrayAdapter(this,opInstance.getAllTodos()));
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        Todo selectedValue = (Todo) getListAdapter().getItem(position);

    }
}
