package app.entity;

/**
 * Created by tlt on 2015/11/4.
 */
public class UmentMessage {

    private String title;
    private String ticker;
    private String text;
    private String afte_open;
    private String activity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAfte_open() {
        return afte_open;
    }

    public void setAfte_open(String afte_open) {
        this.afte_open = afte_open;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
