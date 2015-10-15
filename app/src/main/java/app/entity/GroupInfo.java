package app.entity;

/**
 * Created by Administrator on 2015/10/15.
 */
public class GroupInfo {

    private String id;
    private String name;
    private String style;
    private String expert;
    private String description;
    private String ganger_id;
    private String create_time;
    private String createTimeShow;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGanger_id() {
        return ganger_id;
    }

    public void setGanger_id(String ganger_id) {
        this.ganger_id = ganger_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreateTimeShow() {
        return createTimeShow;
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
    }

    @Override
    public String toString() {
        return "GroupInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", expert='" + expert + '\'' +
                ", description='" + description + '\'' +
                ", ganger_id='" + ganger_id + '\'' +
                ", create_time='" + create_time + '\'' +
                ", createTimeShow='" + createTimeShow + '\'' +
                ", count=" + count +
                '}';
    }
}
