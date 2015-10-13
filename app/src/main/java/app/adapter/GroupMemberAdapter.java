package app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

import app.activity.GroupMembersActivity;

/**
 * Created by Administrator on 2015/10/13.
 */
public class GroupMemberAdapter extends AllAdapter{
    Activity activity;
    public GroupMemberAdapter(GroupMembersActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View layout = activity.getLayoutInflater().inflate(R.layout.item_group_member, null);
        return layout;
    }
}
