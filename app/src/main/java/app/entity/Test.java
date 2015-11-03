package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Test {
    private String who;
    private String type;
    private String servertype;
    private List<Meta> phone;

    public List<Meta> getPhone() {
        return phone;
    }

    public void setPhone(List<Meta> phone) {
        this.phone = phone;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServertype() {
        return servertype;
    }

    public void setServertype(String servertype) {
        this.servertype = servertype;
    }
}
