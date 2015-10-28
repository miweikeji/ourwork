package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/28.
 */
public class BuiltTaskResult extends Meta{

    private int page;
    private List<BuiltTask> houseList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BuiltTask> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<BuiltTask> houseList) {
        this.houseList = houseList;
    }
}
