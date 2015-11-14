package app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.entity.MyFriends;

/**
 * Created by Administrator on 2015/10/11.
 */
public class MyFriendsAdapter extends AllAdapter{
    Activity activity;
    private List<MyFriends> allList;
    private ImageLoader instance;
    private DisplayImageOptions options;
    public MyFriendsAdapter(Activity activity, List<MyFriends> allList,
                            ImageLoader instance, DisplayImageOptions options) {
        this.activity = activity;
        this.allList = allList;
        this.instance =instance;
        this.options = options;
    }

    @Override
    public int getCount() {
        return allList==null?0:allList.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup parent) {
        ViewHolder holder =null;//cimg : "http://img.miweikj.cn/portrait/56050508e5b27.jpg"
        if(layout==null){
            holder = new ViewHolder();
            layout =  activity.getLayoutInflater().inflate(R.layout.item_my_friends,null);
            holder.img_head = (ImageView)layout.findViewById(R.id.img_head);
            holder.tv_name = (TextView)layout.findViewById(R.id.tv_name);
            holder.tv_age = (TextView)layout.findViewById(R.id.tv_age);
            holder.tv_case = (TextView)layout.findViewById(R.id.tv_case);
            holder.tv_home = (TextView)layout.findViewById(R.id.tv_home);
            holder.tv_type = (TextView)layout.findViewById(R.id.tv_type);
            layout.setTag(holder);
        }else{
            holder = (ViewHolder) layout.getTag();
        }
        MyFriends friends = allList.get(position);
        holder.img_head.setImageResource(R.mipmap.test);
        instance.displayImage(friends.getFcimg(), holder.img_head, options);
        holder.tv_name.setText(friends.getFname());
        holder.tv_age.setText(friends.getFage()+"岁/"+friends.getFcworkold()+"年工龄");
        holder.tv_home.setText(friends.getFcworkhome());
        holder.tv_case.setText(friends.getFprofession());
        holder.tv_type.setVisibility(View.INVISIBLE);
        if(friends.getType().equals("0")){
            holder.tv_type.setVisibility(View.INVISIBLE);
        }else {
            holder.tv_type.setVisibility(View.VISIBLE);
        }
        return layout;
    }

    public class ViewHolder{
        ImageView img_head;
        TextView tv_name;
        TextView tv_age;
        TextView tv_case;
        TextView tv_home;
        TextView tv_type;
    }
}
