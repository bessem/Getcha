package pirana.aka.getcha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pirana.aka.getcha.db.TodoOperations;
import pirana.aka.getcha.model.Todo;

/**
 * Created by aka on 2/1/16.
 */
public class TodosArrayAdapter extends ArrayAdapter<Todo> {
    private final Context context;
    private final List<Todo> todos;

    public TodosArrayAdapter(Context context, List<Todo> values) {
        super(context, R.layout.todo_sample,values);
        this.context = context;
        this.todos = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TodoOperations opInstance = TodoOperations.getInstance(context);
        View rowView = inflater.inflate(R.layout.todo_sample, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText((CharSequence) opInstance.getAllTodos().get(position).getTitle());
        imageView.setImageResource(0); //to be overriden

        return rowView;
    }

}
