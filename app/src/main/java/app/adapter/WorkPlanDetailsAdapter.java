package app.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import app.activity.CraftsmanZoneActivity;
import app.activity.WorkPlanDetailsActivity;
import app.entity.DetailPlan;
import app.tools.ImageLoadTools;
import app.tools.StatusTools;

/**
 * Created by Administrator on 2015/10/27.
 */
public class WorkPlanDetailsAdapter extends AllAdapter {

    private ArrayList<DetailPlan> allCases;
    private Activity activity;
    private DisplayImageOptions options;
    public WorkPlanDetailsAdapter(WorkPlanDetailsActivity activity,
                                  ArrayList<DetailPlan> allCases, DisplayImageOptions options) {
        this.activity =activity;
        this.allCases=allCases;
        this.options = options;
    }

    @Override
    public int getCount() {
        return allCases==null?0:allCases.size();
    }

    @Override
    public View getView(int position, View layout, ViewGroup parent) {
        ViewHolder holder= null;
        if(layout==null){
            holder = new ViewHolder();
            layout = activity.getLayoutInflater().inflate(R.layout.item_work_plan_detials, null);
            holder.tv_name_and_case = (TextView) layout.findViewById(R.id.tv_name_and_case);
            holder.tv_time = (TextView) layout.findViewById(R.id.tv_time);
            holder.tv_pam = (TextView) layout.findViewById(R.id.tv_pam);
            holder.img_head = (ImageView) layout.findViewById(R.id.img_head);
            layout.setTag(holder);
        }else {
             holder = (ViewHolder) layout.getTag();
        }
        DetailPlan plan = allCases.get(position);
        holder.tv_name_and_case.setText(plan.getName()+"-"+ StatusTools.workType(plan.getWorktype()));
        holder.tv_time.setText(plan.getDatatime());
        if("1".equals(plan.getAm())){
            holder.tv_pam.setText("上午");
        }
        if("1".equals(plan.getPm())){
            holder.tv_pam.setText("下午");
        }

        if("0".equals(plan.getAm())&&"0".equals(plan.getPm())){
            holder.tv_pam.setText("未安排");
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, CraftsmanZoneActivity.class));
            }
        });
//        ImageLoadTools.displayImage(plan.get);
        return layout;
    }

    public class ViewHolder{
        TextView tv_name_and_case;
        TextView tv_time;
        TextView tv_pam;
        ImageView img_head;
    }
}
