package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class ConstructPlanResult extends Meta{
    private int page;
    private List<ConstructPlan> houseList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ConstructPlan> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<ConstructPlan> houseList) {
        this.houseList = houseList;
    }
}
