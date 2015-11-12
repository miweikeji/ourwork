package app.entity;

import java.io.Serializable;

/**
 * Created by tlt on 2015/11/3.
 */
public class MessageItem implements Serializable{

    private String id;
    private String craftsId;
    private String workId;
    private String workplace;
    private String addTime;
    private String workType;
    private String title;
    private String content;
    private String rightShow;
    private String enterDetail;
    private String house_name;
    private String house_type;
    private String house_area;
    private String house_id;
    private String house_style;
    private String craft_mode;
    private String house_total_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCraftsId() {
        return craftsId;
    }

    public void setCraftsId(String craftsId) {
        this.craftsId = craftsId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRightShow() {
        return rightShow;
    }

    public void setRightShow(String rightShow) {
        this.rightShow = rightShow;
    }

    public String getEnterDetail() {
        return enterDetail;
    }

    public void setEnterDetail(String enterDetail) {
        this.enterDetail = enterDetail;
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

    public String getHouse_id() {
        return house_id;
    }

    public void setHouse_id(String house_id) {
        this.house_id = house_id;
    }

    public String getHouse_style() {
        return house_style;
    }

    public void setHouse_style(String house_style) {
        this.house_style = house_style;
    }

    public String getCraft_mode() {
        return craft_mode;
    }

    public void setCraft_mode(String craft_mode) {
        this.craft_mode = craft_mode;
    }

    public String getHouse_total_price() {
        return house_total_price;
    }

    public void setHouse_total_price(String house_total_price) {
        this.house_total_price = house_total_price;
    }
}
