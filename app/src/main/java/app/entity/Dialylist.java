package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/2.
 */
public class Dialylist {

    private List<DialyData> list;
    private int totalpage;

    public List<DialyData> getList() {
        return list;
    }

    public void setList(List<DialyData> list) {
        this.list = list;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}
