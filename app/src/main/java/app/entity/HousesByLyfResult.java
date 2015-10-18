package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/18.
 */
public class HousesByLyfResult extends Meta {

    private int totalpage;

    private List<HousesByLyf> houseList;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<HousesByLyf> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HousesByLyf> houseList) {
        this.houseList = houseList;
    }
}
