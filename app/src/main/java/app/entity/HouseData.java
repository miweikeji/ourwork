package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/6.
 */
public class HouseData {
    private String num;
    private String charge;
    private String chargetype;
    private String wtype;
    private List<Ctime> time;
    private List<Cname> name;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargetype() {
        return chargetype;
    }

    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }

    public String getWtype() {
        return wtype;
    }

    public void setWtype(String wtype) {
        this.wtype = wtype;
    }

    public List<Ctime> getTime() {
        return time;
    }

    public void setTime(List<Ctime> time) {
        this.time = time;
    }

    public List<Cname> getName() {
        return name;
    }

    public void setName(List<Cname> name) {
        this.name = name;
    }
}
