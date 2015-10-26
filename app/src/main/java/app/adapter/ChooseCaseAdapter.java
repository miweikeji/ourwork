package app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ChooseCaseAdapter extends AllAdapter{

    private Activity activity;
    private int count;
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
        return layout;
    }

    public void setData(Activity activity , int count){
        this.activity = activity;
        this.count = count;
    }
}
