package app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.miwei.jzj_system.R;

/**
 * Created by Administrator on 2015/10/10.
 */
public class WorkTypeAdater extends AllAdapter{
    private Activity context;
    private  String[] items;
    public WorkTypeAdater(Activity mContext, String[] items) {
        super();
        this.context = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View layout =  context.getLayoutInflater().inflate(R.layout.item_work_type,null);
        TextView worktype = (TextView)layout.findViewById(R.id.tv_worktype);
        worktype.setText(items[position]);
        return layout;
    }
}
