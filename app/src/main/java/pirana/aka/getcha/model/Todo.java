package pirana.aka.getcha.model;

/**
 * Created by aka on 2/1/16.
 */
public class Todo {
    private int _id;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
    private String Title;

    public String getTitle() {

        return Title;
    }

    public Todo(int _id, String title) {
        this._id = _id;
        Title = title;
    }

    public void setTitle(String title) {
        Title = title;

    }
}
