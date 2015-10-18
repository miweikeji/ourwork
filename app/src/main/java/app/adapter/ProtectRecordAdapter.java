package app.adapter;

import android.content.Context;

import com.miweikeij.app.R;

import java.util.List;

import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.Meta;
import app.entity.ProtectRecord;

/**
 * Created by tlt on 2015/10/18.
 */
public class ProtectRecordAdapter extends SingleTypeAdapter<ProtectRecord> {


    public ProtectRecordAdapter(Context context, List<ProtectRecord> items) {
        super(context, R.layout.item_protect_record);
        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_money, R.id.tv_time};}

    @Override
    protected void update(int position, ProtectRecord item) {

    }
}
