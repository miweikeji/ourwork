package app.entity;

import java.util.List;

/**
 * Created by tlt on 2015/10/28.
 */
public class ProtectRecordResult extends Meta {

    private List<ProtectRecord> message;
    private int page;

    public List<ProtectRecord> getMessage() {
        return message;
    }

    public void setMessage(List<ProtectRecord> message) {
        this.message = message;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
