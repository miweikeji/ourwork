package app.activity.mywork.adapter;

import android.content.Context;

import com.miweikeij.app.R;

import java.util.List;

import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.Message;

/**
 * Created by tlt on 2015/10/18.
 */
public class MessageAdapter extends SingleTypeAdapter<Message> {


    public MessageAdapter(Context context, List<Message> items) {
        super(context, R.layout.item_message);
        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_name, R.id.tv_message_type, R.id.tv_plaint};}

    @Override
    protected void update(int position, Message item) {

    }
}
