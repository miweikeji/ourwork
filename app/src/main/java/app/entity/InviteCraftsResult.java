package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/5.
 */
public class InviteCraftsResult extends Meta {
    private List<InviteCrafts> crafts;

    public List<InviteCrafts> getCrafts() {
        return crafts;
    }

    public void setCrafts(List<InviteCrafts> crafts) {
        this.crafts = crafts;
    }
}
