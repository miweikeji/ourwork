package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/11/8.
 */
public class CraftsByHouseIdResult extends Meta {

    private List<Allcrafts>   craftsList;

    public List<Allcrafts> getCraftsList() {
        return craftsList;
    }

    public void setCraftsList(List<Allcrafts> craftsList) {
        this.craftsList = craftsList;
    }
}
