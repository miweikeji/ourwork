package app.entity;

/**
 * Created by Administrator on 2015/11/1.
 */
public class MyWorksListResult extends Meta{

    private MyWorksList message;

    public MyWorksList getMessage() {
        return message;
    }

    public void setMessage(MyWorksList message) {
        this.message = message;
    }
}
