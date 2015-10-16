package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class CaseResult extends Meta{
    private int totalpage;
    private List<Case> houseList;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<Case> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<Case> houseList) {
        this.houseList = houseList;
    }
}
