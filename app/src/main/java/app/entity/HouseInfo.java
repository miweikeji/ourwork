package app.entity;

/**
 * Created by tlt on 2015/10/29.
 */
public class HouseInfo {

    private  House house;
    private  Designer designer;
    private String relation;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
