package app.activity.mywork.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.adapter.AllAdapter;
import app.entity.Allcrafts;

/**
 * Created by Administrator on 2015/10/22.
 */
public class CraftsAdapter extends AllAdapter {
    private Activity activity;
    private List<Allcrafts> allList;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    public CraftsAdapter(Activity activity, List<Allcrafts> allList,
                         ImageLoader imageLoader, DisplayImageOptions options) {
        this.activity = activity;
        this.allList = allList;
        this.options = options;
        this.imageLoader = imageLoader;
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
        Allcrafts allcrafts = allList.get(position);
//        imageLoader.displayImage(allcrafts.getCimg(), holder.img_head, options);
        holder.tv_name.setText(allcrafts.getName());
        holder.tv_home.setText(allcrafts.getCworkhome());
        holder.tv_case.setText(allcrafts.getProfession());
        holder.tv_age.setText(allcrafts.getAge()+"岁/"+allcrafts.getCworkold()+"年工龄");
//        0表示已认证1表示未认证（工头才有的属性）2表示审核中，客户端可以根据0判断为工头，其他为工匠

        if ("0".equals(allcrafts.getJiang())){
        }else {
            holder.tv_type.setVisibility(View.INVISIBLE);
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
