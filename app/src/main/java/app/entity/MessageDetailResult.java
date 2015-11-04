package app.entity;

/**
 * Created by tlt on 2015/11/4.
 */
public class MessageDetailResult extends Meta {
    private MessageDetail message;

    public MessageDetail getMessage() {
        return message;
    }

    public void setMessage(MessageDetail message) {
        this.message = message;
    }
}
