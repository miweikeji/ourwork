package app.activity.mywork.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.List;

import app.adapter.AllAdapter;
import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.Message;
import app.entity.MessageItem;
import app.tools.StatusTools;

/**
 * Created by tlt on 2015/10/18.
 */
public class MessageAdapter extends AllAdapter {

    List<MessageItem> list;
    Activity context;

    public MessageAdapter(Activity context, List<MessageItem> items) {
        this.list = items;
        this.context = context;
    }
    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        }
        return 0;
    }
    @Override
    public View getView(int position, View layout, ViewGroup parent) {
        ViewHolder holder;
        if (layout == null) {
            holder = new ViewHolder();
            layout = context.getLayoutInflater().inflate(R.layout.item_message, null);
            holder.tv_name = (TextView) layout.findViewById(R.id.tv_name);
            holder.tv_message_type = (TextView) layout.findViewById(R.id.tv_message_type);
            holder.tv_plaint = (TextView) layout.findViewById(R.id.tv_plaint);
            layout.setTag(holder);
        } else {
            holder = (ViewHolder) layout.getTag();
        }
        MessageItem item = list.get(position);
        String type = item.getWorkType();

        holder.tv_name.setText("--");
        holder.tv_plaint.setText("--");
        holder.tv_name.setText("--");
        holder.tv_message_type.setText("--");
        String enterDetail = item.getEnterDetail();
        if (!TextUtils.isEmpty(enterDetail)) {

            if ("7".equals(enterDetail)){
                holder.tv_name.setText(item.getTitle());
                holder.tv_plaint.setText(item.getContent());

            }else if("6".equals(enterDetail)){
                holder.tv_name.setText(item.getContent());
                holder.tv_plaint.setText(item.getHouse_name());
            }
            else{
                if (!TextUtils.isEmpty(type)) {
                    String workname = StatusTools.workType(type);
                    holder.tv_name.setText(workname);
                }
                holder.tv_plaint.setText("服务地点：" + item.getWorkplace());
            }
            holder.tv_message_type.setText(item.getRightShow());
                //        enterDetail ：
//        0 表示点击详情进入“有人申请加入”界面
//        1表示点击详情进入“申请接单成功”界面
//        2表示点击详情进入“申请接单失败”界面
//        3表示点击详情进入“邀请施工”界面
//        4表示点击详情进入“邀请施工人员成功”界面
//        5表示点击详情进入“邀请施工人员失败”界面
//        6表示点击详情进入“业主预约”界面
//        7表示点击详情进入“系统消息”界面
        }
        return layout;
    }

    public class ViewHolder {
        TextView tv_name;
        TextView tv_message_type;
        TextView tv_plaint;
    }
}
