package app.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.List;

import app.entity.WorkList;
import app.tools.StatusTools;

/**
 * Created by Administrator on 2015/10/12.
 */
public class JobAdapter extends AllAdapter{
    private Activity activity;
    private List<WorkList> allList;
    public JobAdapter(FragmentActivity activity, List<WorkList> allList) {
        this.activity = activity;
        this.allList = allList;
    }

    @Override
    public int getCount() {
        return allList==null?0:allList.size();
    }

    @Override
    public View getView(int position, View inflate, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(inflate==null){
            holder = new ViewHolder();
            inflate = activity.getLayoutInflater().inflate(R.layout.item_job_list, null);
            holder.tv_work_area = (TextView) inflate.findViewById(R.id.tv_work_area);
            holder.tv_work_type = (TextView) inflate.findViewById(R.id.tv_work_type);
            holder.tv_time = (TextView) inflate.findViewById(R.id.tv_time);
            inflate.setTag(holder);
        }else {
            holder = (ViewHolder)inflate.getTag();
        }
        WorkList workList = allList.get(position);
        StatusTools.setStatus(holder.tv_work_type,workList.getWorktype());
        holder.tv_work_area.setText(workList.getS_addr());
        holder.tv_time.setText(workList.getAddtime());
        return inflate;
    }

    public class ViewHolder{
        TextView tv_work_area;
        TextView tv_work_type;
        TextView tv_time;
    }
}
