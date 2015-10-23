package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class MyFriendsResult extends Meta {

    private List<MyFriends> message;
    private int page;

    public List<MyFriends> getMessage() {
        return message;
    }

    public void setMessage(List<MyFriends> message) {
        this.message = message;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
