package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/1.
 */
public class MyWorksList {

    private int page;
    private List<MyWorks> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MyWorks> getList() {
        return list;
    }

    public void setList(List<MyWorks> list) {
        this.list = list;
    }
}
