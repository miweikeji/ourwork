package app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

/**
 * Created by Administrator on 2015/10/12.
 */
public class JobAdapter extends AllAdapter{
    private Activity activity;
    public JobAdapter(FragmentActivity activity) {
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.item_job_list, null);
        return inflate;
    }
}
