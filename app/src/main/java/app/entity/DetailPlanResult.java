package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class DetailPlanResult extends Meta {

    private int page;
    private List<DetailPlan> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<DetailPlan> getList() {
        return list;
    }

    public void setList(List<DetailPlan> list) {
        this.list = list;
    }
}
