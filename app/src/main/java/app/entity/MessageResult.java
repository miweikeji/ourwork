package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/3.
 */
public class MessageResult extends Meta {

    private List<MessageItem> messages;
    private int page;

    public List<MessageItem> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageItem> messages) {
        this.messages = messages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
