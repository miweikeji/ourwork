package app.entity;

/**
 * Created by Administrator on 2015/11/1.
 */
public class MyWorkDetailsResult extends Meta{
    private MyWorkDetails message;

    public MyWorkDetails getMessage() {
        return message;
    }

    public void setMessage(MyWorkDetails message) {
        this.message = message;
    }
}
