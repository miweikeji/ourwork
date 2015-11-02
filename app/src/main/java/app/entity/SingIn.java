package app.entity;

/**
 * Created by tlt on 2015/10/29.
 */
public class SingIn {

    private String cid;
    private int is_sign;
    private String houseId;
    private String status;
    private String hasTask;
    private int signNum;
    private String signtime;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getIs_sign() {
        return is_sign;
    }

    public void setIs_sign(int is_sign) {
        this.is_sign = is_sign;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasTask() {
        return hasTask;
    }

    public void setHasTask(String hasTask) {
        this.hasTask = hasTask;
    }

    public int getSignNum() {
        return signNum;
    }

    public void setSignNum(int signNum) {
        this.signNum = signNum;
    }

    public String getSigntime() {
        return signtime;
    }

    public void setSigntime(String signtime) {
        this.signtime = signtime;
    }
}
