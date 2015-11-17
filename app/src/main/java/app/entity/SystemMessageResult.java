package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/17.
 */
public class SystemMessageResult extends Meta {

    private List<SystemMessage> message;

    public List<SystemMessage> getMessage() {
        return message;
    }

    public void setMessage(List<SystemMessage> message) {
        this.message = message;
    }
}
