package app.activity.mywork.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.miweikeij.app.R;

import java.util.List;

import app.adapter.typeadapter.SingleTypeAdapter;
import app.entity.Message;
import app.entity.MessageItem;

/**
 * Created by tlt on 2015/10/18.
 */
public class MessageAdapter extends SingleTypeAdapter<MessageItem> {


    public MessageAdapter(Context context, List<MessageItem> items) {
        super(context, R.layout.item_message);
        setItems(items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_name, R.id.tv_message_type, R.id.tv_plaint};
    }

    @Override
    protected void update(int position, MessageItem item) {

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
                    textView(1).setText("有人申请加入");
                    break;
                case "1":
                    textView(1).setText("申请接单成功");
                    break;
                case "2":
                    textView(1).setText("申请接单失败");
                    break;
                case "3":
                    textView(1).setText("邀请施工");
                    break;
                case "4":
                    textView(1).setText("邀请施工人员成功");
                    break;
                case "5":
                    textView(1).setText("邀请施工人员失败");
                    break;
                case "6":
                    textView(1).setText("业主预约");
                    break;
                case "7":
                    textView(1).setText("系统消息");
                    break;
                default:
                    break;

            }
        }


    }
}
