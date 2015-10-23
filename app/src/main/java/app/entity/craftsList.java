package app.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class craftsList{

    private List<Allcrafts> list;
    private int page;

    public List<Allcrafts> getList() {
        return list;
    }

    public void setList(List<Allcrafts> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
