package app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import app.activity.GroupMembersActivity;
import app.entity.GroupMembe;
import app.views.CircleImageView;

/**
 * Created by Administrator on 2015/10/13.
 */
public class GroupMemberAdapter extends AllAdapter{
    Activity activity;
    private ArrayList<GroupMembe> allList;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    public GroupMemberAdapter(GroupMembersActivity activity, ArrayList<GroupMembe> allList,
                              ImageLoader imageLoader, DisplayImageOptions options) {
        this.activity = activity;
        this.allList = allList;
        this.imageLoader = imageLoader;
        this.options = options;
    }

    @Override
    public int getCount() {
        return allList==null?0:allList.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if(layout==null){
            holder = new ViewHolder();
           layout = activity.getLayoutInflater().inflate(R.layout.item_group_member, null);
            holder.img_head=(CircleImageView)layout.findViewById(R.id.img_head);
            holder.tv_name = (TextView)layout.findViewById(R.id.tv_name);
            holder.tv_age = (TextView)layout.findViewById(R.id.tv_age);
            holder.tv_work_age = (TextView)layout.findViewById(R.id.tv_work_age);
            holder.tv_work_type = (TextView)layout.findViewById(R.id.tv_work_type);
            holder.tv_home = (TextView)layout.findViewById(R.id.tv_home);
            layout.setTag(holder);
        }else {
            holder=(ViewHolder) layout.getTag();
        }
        GroupMembe membe = allList.get(position);
        imageLoader.displayImage(membe.getCimg(),holder.img_head,options);
        holder.tv_name.setText(membe.getName());
        holder.tv_age.setText(membe.getAge());
        holder.tv_work_age.setText(membe.getCworkold());
        holder.tv_work_type.setText(membe.getProfession());
        holder.tv_home.setText(membe.getCworkhome());
        return layout;
    }

    public class ViewHolder{
        CircleImageView img_head;
        TextView tv_name;
        TextView tv_age;
        TextView tv_work_age;
        TextView tv_work_type;
        TextView tv_home;
    }
}
