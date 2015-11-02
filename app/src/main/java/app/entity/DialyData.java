package app.entity;

/**
 * Created by tlt on 2015/11/2.
 */
public class DialyData {

     private String id;
    private String time;//2015-09-06 16:50:33
    private String decription;
    private int house_state;
    private String image_1;
    private String image_2;
    private String image_3;
    private String image_4;
    private String image_5;
    private String image_6;
    private String image_7;
    private String image_8;
    private String image_9;
    private String type;
    private String houseId;
    private String ownerId;
    private String cid;
    private String addtime;
    private String wid;
    private String wname;
    private String title;
    private int title_state;
//    正常的情况下标题更加房子的状态来显示比如状态为house_state  为  0那么显示为标题显示为量房。但是当title_state（默认为0）大于0的时候房子的标题显示为title
//    title_state的状态 表示
//    1 水电隐蔽验收
//    2防水工程验收
//    3木作隐蔽验收
//    4中期验收
//    5竣工验收
//    6量房
//    7 婉拒
//    8 已量房


    public int getHouse_state() {
        return house_state;
    }

    public void setHouse_state(int house_state) {
        this.house_state = house_state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_3() {
        return image_3;
    }

    public void setImage_3(String image_3) {
        this.image_3 = image_3;
    }

    public String getImage_4() {
        return image_4;
    }

    public void setImage_4(String image_4) {
        this.image_4 = image_4;
    }

    public String getImage_5() {
        return image_5;
    }

    public void setImage_5(String image_5) {
        this.image_5 = image_5;
    }

    public String getImage_6() {
        return image_6;
    }

    public void setImage_6(String image_6) {
        this.image_6 = image_6;
    }

    public String getImage_7() {
        return image_7;
    }

    public void setImage_7(String image_7) {
        this.image_7 = image_7;
    }

    public String getImage_8() {
        return image_8;
    }

    public void setImage_8(String image_8) {
        this.image_8 = image_8;
    }

    public String getImage_9() {
        return image_9;
    }

    public void setImage_9(String image_9) {
        this.image_9 = image_9;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitle_state() {
        return title_state;
    }

    public void setTitle_state(int title_state) {
        this.title_state = title_state;
    }
}
