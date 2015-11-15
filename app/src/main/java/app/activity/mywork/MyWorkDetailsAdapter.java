package app.activity.mywork;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.activity.CraftsmanZoneActivity;
import app.activity.WorkPlanDetailsActivity;
import app.adapter.AllAdapter;
import app.entity.CaseItem;
import app.entity.DetailPlan;
import app.tools.ImageLoadTools;
import app.tools.StatusTools;

/**
 * Created by Administrator on 2015/10/27.
 */
public class MyWorkDetailsAdapter extends AllAdapter {

    private final ImageLoader imageloader;
    private List<CaseItem> allCases;
    private Activity activity;
    private DisplayImageOptions options;
    public MyWorkDetailsAdapter(Activity activity, List<CaseItem> allCases, DisplayImageOptions options) {
        this.activity =activity;
        this.allCases=allCases;
        this.options = options;
      imageloader=ImageLoader.getInstance();
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
        final CaseItem caseItem = allCases.get(position);
        holder.tv_name_and_case.setText(caseItem.getName()+"-"+ StatusTools.workType(caseItem.getWorkType()));
        holder.tv_time.setText(caseItem.getDatatime());
        if("1".equals(caseItem.getAm())){
            holder.tv_pam.setText("上午");
        }
        if("1".equals(caseItem.getPm())){
            holder.tv_pam.setText("下午");
        }

        if("0".equals(caseItem.getAm())&&"0".equals(caseItem.getPm())){
            holder.tv_pam.setText("未安排");
        }
        imageloader.displayImage(caseItem.getCraftsImg(),holder.img_head,options);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  cid= caseItem.getDid();
                if (!TextUtils.isEmpty(cid)){
                    CraftsmanZoneActivity.enterActivity(activity,Integer.parseInt(cid));
                }
            }
        });
        return layout;
    }

    public class ViewHolder{
        TextView tv_name_and_case;
        TextView tv_time;
        TextView tv_pam;
        ImageView img_head;
    }
}
