package app.entity;

/**
 * Created by Administrator on 2015/10/10.
 */
public class RegisterInfo extends Meta {

    private String cworkmobile;
    private String id;
    private boolean hasinfo;

    public String getCworkmobile() {
        return cworkmobile;
    }

    public void setCworkmobile(String cworkmobile) {
        this.cworkmobile = cworkmobile;
    }

    public boolean isHasinfo() {
        return hasinfo;
    }

    public void setHasinfo(boolean hasinfo) {
        this.hasinfo = hasinfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "cworkmobile='" + cworkmobile + '\'' +
                ", id='" + id + '\'' +
                ", hasinfo=" + hasinfo +
                '}';
    }
}
