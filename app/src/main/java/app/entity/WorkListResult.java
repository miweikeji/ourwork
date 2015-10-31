package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/30.
 */
public class WorkListResult extends Meta{

    private List<WorkList> workList;
    private int totalPages;

    public List<WorkList> getWorkList() {
        return workList;
    }

    public void setWorkList(List<WorkList> workList) {
        this.workList = workList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
