package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/10/24.
 */
public class AddMemberError extends Meta{

    private List<Phone>  unRegisterCrafts;

    public List<Phone> getUnRegisterCrafts() {
        return unRegisterCrafts;
    }

    public void setUnRegisterCrafts(List<Phone> unRegisterCrafts) {
        this.unRegisterCrafts = unRegisterCrafts;
    }
}
