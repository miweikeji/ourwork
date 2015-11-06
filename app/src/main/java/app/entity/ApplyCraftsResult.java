package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/5.
 */
public class ApplyCraftsResult extends Meta {
    private List<ApplyCrafts> crafts;

    public List<ApplyCrafts> getCrafts() {
        return crafts;
    }

    public void setCrafts(List<ApplyCrafts> crafts) {
        this.crafts = crafts;
    }
}
