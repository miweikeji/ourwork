package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class GroupMemberResult extends Meta {
    private String totalpage;

    private List<GroupMembe> craftsList;

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public List<GroupMembe> getCraftsList() {
        return craftsList;
    }

    public void setCraftsList(List<GroupMembe> craftsList) {
        this.craftsList = craftsList;
    }
}
