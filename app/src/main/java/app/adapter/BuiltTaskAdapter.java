package app.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.activity.BuiltTaskActivity;
import app.entity.BuiltTask;
import app.entity.ConstructPlan;

/**
 * Created by Administrator on 2015/10/28.
 */
public class BuiltTaskAdapter extends AllAdapter {

    private BuiltTaskActivity activity;
    private ArrayList<BuiltTask> allCases;
    public BuiltTaskAdapter(BuiltTaskActivity activity, ArrayList<BuiltTask> allCases) {
        this.activity=activity;
        this.allCases=allCases;
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
        BuiltTask mCase = allCases.get(position);
        holder.tv_name.setText(mCase.getHouse_name());
        holder.tv_type.setText(" "+mCase.getHouse_type()+" |");
        holder.tv_area.setText(" "+mCase.getHouse_area()+"平 |");
        holder.tv_style.setText(" "+mCase.getHouse_style()+" |");
        holder.tv_mode.setText(" "+mCase.getHouse_craft_mode());
        holder.tv_total_price.setText("￥"+mCase.getHouse_total_price());
        if("0".equals(mCase.getBao())){
            holder.tv_status.setText("未发布");
        }else {
            holder.tv_status.setText("已发布");
        }
//        setStatus(holder.tv_status, mCase.getHouse_status());
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
