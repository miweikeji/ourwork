package app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.entity.Case;
import app.entity.ConstructPlan;
import app.entity.MyWork;

/**
 * Created by TuLiangTan on 2015/9/25.
 */
public class MyWorkAdapter extends AllAdapter {


    private Activity activity;
    private ArrayList<ConstructPlan> allCases;
    public MyWorkAdapter(FragmentActivity activity, ArrayList<ConstructPlan> allCases) {
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
        ConstructPlan mCase = allCases.get(position);
        holder.tv_name.setText(mCase.getHouse_name());
        holder.tv_type.setText(" "+mCase.getHouse_type()+" |");
        holder.tv_area.setText(" "+mCase.getHouse_area()+"平 |");
        holder.tv_style.setText(" "+mCase.getHouse_style()+" |");
        holder.tv_mode.setText(" "+mCase.getHouse_craft_mode());
        holder.tv_total_price.setText("￥"+mCase.getHouse_total_price());
        setStatus(holder.tv_status, mCase.getHouse_status());
        return layout;
    }

    private void setStatus(TextView tv_status, String status) {
        if("0".equals(status)){
            tv_status.setText("量房阶段");
        }else if("1".equals(status)){
            tv_status.setText("验房阶段");
        }else if("2".equals(status)){
            tv_status.setText("设计阶段");
        }else if("3".equals(status)){
            tv_status.setText("合同阶段");
        }else if("4".equals(status)){
            tv_status.setText("主体拆改阶段");
        }else if("5".equals(status)){
            tv_status.setText("水电工程阶段");
        }else if("6".equals(status)){
            tv_status.setText("泥瓦工程阶段");
        }else if("7".equals(status)){
            tv_status.setText("木作工程阶段");
        }else if("8".equals(status)){
            tv_status.setText("油漆工程阶段");
        }else if("9".equals(status)){
            tv_status.setText("设备安装阶段");
        }else if("10".equals(status)){
            tv_status.setText("清洁验收阶段");
        }else if("11".equals(status)){
            tv_status.setText("竣工阶段");
        }
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
