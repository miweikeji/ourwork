package app.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/3.
 */
public class JsonData implements Serializable{

    private List<Info> info;
    private String checktime;

    private String workplace;

    private String whoid;
    private String owner_name;
    private String who;
    private String type;
    private String servertype;
    private String owner_sex;
    private String house_xiaoqu;
    private String house_type;
    private String house_area;
    private String house_craftmode;
    private String house_id;


    //注意：上传的信息  如果有某个工总或工作被接
    // 如果没有删除或者改变修改原来上传上去，
    // 如果增加工种那么把新的上传上去与新建上传是一样的

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhoid() {
        return whoid;
    }

    public void setWhoid(String whoid) {
        this.whoid = whoid;
    }

    public String getServertype() {
        return servertype;
    }

    public void setServertype(String servertype) {
        this.servertype = servertype;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_sex() {
        return owner_sex;
    }

    public void setOwner_sex(String owner_sex) {
        this.owner_sex = owner_sex;
    }

    public String getHouse_xiaoqu() {
        return house_xiaoqu;
    }

    public void setHouse_xiaoqu(String house_xiaoqu) {
        this.house_xiaoqu = house_xiaoqu;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getHouse_area() {
        return house_area;
    }

    public void setHouse_area(String house_area) {
        this.house_area = house_area;
    }

    public String getHouse_craftmode() {
        return house_craftmode;
    }

    public void setHouse_craftmode(String house_craftmode) {
        this.house_craftmode = house_craftmode;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }
}
