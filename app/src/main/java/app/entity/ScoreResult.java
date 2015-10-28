package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/10/29.
 */
public class ScoreResult extends Meta {
    private List<Score> scorList;
    private String page;

    public List<Score> getScorList() {
        return scorList;
    }

    public void setScorList(List<Score> scorList) {
        this.scorList = scorList;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
