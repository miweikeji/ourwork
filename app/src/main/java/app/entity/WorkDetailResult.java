package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/30.
 */
public class WorkDetailResult extends Meta{

    private String apply;
    private Message message;
    private List<Data> data;

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
