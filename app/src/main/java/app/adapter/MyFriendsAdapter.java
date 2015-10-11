package app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

import app.activity.MyFriendsActivity;

/**
 * Created by Administrator on 2015/10/11.
 */
public class MyFriendsAdapter extends AllAdapter{
    Activity activity;
    int count;
    public MyFriendsAdapter(MyFriendsActivity activity, int count) {
        this.activity = activity;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item_my_friends, null);
        return layout;
    }
}
