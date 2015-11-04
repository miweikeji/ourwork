package app.activity.mywork.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miweikeij.app.R;

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
        if (!TextUtils.isEmpty(type)) {
            String workname = StatusTools.workType(type);
            holder.tv_name.setText(workname);
        }
        holder.tv_plaint.setText("服务地点：" + item.getWorkplace());

        String enterDetail = item.getEnterDetail();
        if (!TextUtils.isEmpty(enterDetail)) {
            switch (enterDetail) {
                //        enterDetail ：
//        0 表示点击详情进入“有人申请加入”界面
//        1表示点击详情进入“申请接单成功”界面
//        2表示点击详情进入“申请接单失败”界面
//        3表示点击详情进入“邀请施工”界面
//        4表示点击详情进入“邀请施工人员成功”界面
//        5表示点击详情进入“邀请施工人员失败”界面
//        6表示点击详情进入“业主预约”界面
//        7表示点击详情进入“系统消息”界面
                case "0":
                    holder.tv_message_type.setText("有人申请加入");
                    break;
                case "1":
                    holder.tv_message_type.setText("申请接单成功");
                    break;
                case "2":
                    holder.tv_message_type.setText("申请接单失败");
                    break;
                case "3":
                    holder.tv_message_type.setText("邀请施工");
                    break;
                case "4":
                    holder.tv_message_type.setText("邀请施工人员成功");
                    break;
                case "5":
                    holder.tv_message_type.setText("邀请施工人员失败");
                    break;
                case "6":
                    holder.tv_message_type.setText("业主预约");
                    break;
                case "7":
                    holder.tv_message_type.setText("系统消息");
                    break;
                default:
                    break;

            }
        }
        return layout;
    }

    public class ViewHolder {
        TextView tv_name;
        TextView tv_message_type;
        TextView tv_plaint;
    }
}
