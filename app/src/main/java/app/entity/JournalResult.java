package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class JournalResult extends Meta {

    private List<Journal> dialy;

    public List<Journal> getDialy() {
        return dialy;
    }

    public void setDialy(List<Journal> dialy) {
        this.dialy = dialy;
    }
}
