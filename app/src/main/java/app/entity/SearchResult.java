package app.entity;

import java.util.List;

import app.entity.Meta;
import app.entity.craftsList;

/**
 * Created by Administrator on 2015/10/26.
 */
public class SearchResult extends Meta{
    private List<Allcrafts> crafts;
    private int page;

    public List<Allcrafts> getCrafts() {
        return crafts;
    }

    public void setCrafts(List<Allcrafts> crafts) {
        this.crafts = crafts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
