package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class CommentResult extends Meta{

    private List<Comment> commentList;

    private int totalpage;

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
