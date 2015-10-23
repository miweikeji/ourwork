package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class ArrangeTaskResult extends Meta {
    private int totalpage;
    private List<ArrangeTask> houseList;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<ArrangeTask> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<ArrangeTask> houseList) {
        this.houseList = houseList;
    }
}
