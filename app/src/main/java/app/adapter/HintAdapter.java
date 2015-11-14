package app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.tools.ScreenUtil;

/**
 * Created by Administrator on 2015/11/14.
 */
public class HintAdapter extends AllAdapter {

    private Activity activity;
    private int h;
    public HintAdapter(Activity activity) {
        this.activity = activity;
        int dip2px = ScreenUtil.instance(activity).dip2px(100);
        h = dip2px;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item_hint, null);
        RelativeLayout rl_hint_height = (RelativeLayout) layout.findViewById(R.id.rl_hint_height);
        ViewGroup.LayoutParams params = rl_hint_height.getLayoutParams();
        params.height = ScreenUtil.getScreenHeight(activity)-h-100 ;
        rl_hint_height.setLayoutParams(params);
        return  layout;
    }
}
