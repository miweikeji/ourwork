package app.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/18.
 */
public class HousesByLyf implements Serializable{

    private String id;
    private String type;
    private String house_id;
    private String house_name;
    private String house_type;
    private String house_area;
    private String house_style;
    private String house_craft_mode;
    private String house_total_price;
    private String owner_id;
    private String owner_name;
    private String owner_phone;
    private String crafts_id;
    private String crafts_name;
    private String designer_id;
    private String addtime;
    private String lftime;
    private String state;
    private String stats_des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
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

    public String getHouse_style() {
        return house_style;
    }

    public void setHouse_style(String house_style) {
        this.house_style = house_style;
    }

    public String getHouse_craft_mode() {
        return house_craft_mode;
    }

    public void setHouse_craft_mode(String house_craft_mode) {
        this.house_craft_mode = house_craft_mode;
    }

    public String getHouse_total_price() {
        return house_total_price;
    }

    public void setHouse_total_price(String house_total_price) {
        this.house_total_price = house_total_price;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getCrafts_id() {
        return crafts_id;
    }

    public void setCrafts_id(String crafts_id) {
        this.crafts_id = crafts_id;
    }

    public String getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(String designer_id) {
        this.designer_id = designer_id;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getLftime() {
        return lftime;
    }

    public void setLftime(String lftime) {
        this.lftime = lftime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStats_des() {
        return stats_des;
    }

    public void setStats_des(String stats_des) {
        this.stats_des = stats_des;
    }

    public String getCrafts_name() {
        return crafts_name;
    }

    public void setCrafts_name(String crafts_name) {
        this.crafts_name = crafts_name;
    }
}
