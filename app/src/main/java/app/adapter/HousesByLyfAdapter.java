package app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.ArrayList;

import app.entity.HousesByLyf;

/**
 * Created by Administrator on 2015/10/18.
 */
public class HousesByLyfAdapter extends AllAdapter {
    private Activity activity;
    private ArrayList<HousesByLyf> allCases;
    public HousesByLyfAdapter(FragmentActivity activity, ArrayList<HousesByLyf> allCases) {
        this.activity = activity;
       this.allCases = allCases;
    }

    @Override
    public int getCount() {
        return allCases==null?0:allCases.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup parent) {

        ViewHolder holder;
        if(layout==null){
            holder = new ViewHolder();
            layout= activity.getLayoutInflater().inflate(R.layout.item_mywork,null);
            holder.tv_name= (TextView)layout.findViewById(R.id.tv_name);
            holder.tv_status= (TextView)layout.findViewById(R.id.tv_status);
            holder.tv_type= (TextView)layout.findViewById(R.id.tv_type);
            holder.tv_area= (TextView)layout.findViewById(R.id.tv_area);
            holder.tv_style= (TextView)layout.findViewById(R.id.tv_style);
            holder.tv_mode= (TextView)layout.findViewById(R.id.tv_mode);
            holder.tv_total_price= (TextView)layout.findViewById(R.id.tv_total_price);
            layout.setTag(holder);
        }else{
            holder = (ViewHolder)layout.getTag();
        }
        HousesByLyf lyf = allCases.get(position);
        holder.tv_name.setText(lyf.getHouse_name());
        holder.tv_type.setText(" "+lyf.getHouse_type()+" |");
        holder.tv_area.setText(" "+lyf.getHouse_area()+"平 |");
        holder.tv_style.setText(" "+lyf.getHouse_style()+" |");
        holder.tv_mode.setText(" "+lyf.getHouse_craft_mode());
        holder.tv_total_price.setText("￥"+lyf.getHouse_total_price());
        holder.tv_status.setText(lyf.getStats_des());
        return layout;
    }

    public class ViewHolder{
        TextView tv_name;
        TextView tv_status;
        TextView tv_type;
        TextView tv_area;
        TextView tv_style;
        TextView tv_mode;
        TextView tv_total_price;
    }
}
