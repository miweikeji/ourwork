package app.activity.mywork;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.activity.SystemMessageActivity;
import app.activity.mywork.adapter.MessageAdapter;
import app.entity.MessageItem;
import app.entity.MessageResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class ParterMessageActivity extends BaseActivity {

    private MessageAdapter adapter;
    private ListView listView;
    List<MessageItem> items = new ArrayList<>();
    private int page;

    @Override
    public void obtainData() {

        showWaitingDialog();
        page = 1;
        HttpRequest.getMessages(mActivity, "101", page, new ICallback<MessageResult>() {
            @Override
            public void onSucceed(MessageResult result) {
                disMissWaitingDialog();
                items = result.getMessages();
                if (items != null && items.size() > 0) {
                    adapter = new MessageAdapter(mActivity, items);
                    listView.setAdapter(adapter);
                }

            }

            @Override
            public void onFail(String error) {

                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);
            }
        });
    }

    @Override
    public void initUI() {

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageItem messageItem = items.get(position);
                String enterDetail = messageItem.getEnterDetail();
                if (!TextUtils.isEmpty(enterDetail)) {
                    switch (enterDetail) {
//        0 表示点击详情进入“有人申请加入”界面
//        1表示点击详情进入“申请接单成功”界面
//        2表示点击详情进入“申请接单失败”界面
//        3表示点击详情进入“邀请施工”界面
//        4表示点击详情进入“邀请施工人员成功”界面
//        5表示点击详情进入“邀请施工人员失败”界面
//        6表示点击详情进入“业主预约”界面
//        7表示点击详情进入“系统消息”界面
                        case "0":
                            MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 1, messageItem.getId());
                            break;
                        case "1":
                            MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 1, messageItem.getId());
                            break;
                        case "2":
                            MessageA_Activity.enterActivity(mActivity, messageItem.getWorkId(), 2, messageItem.getId());
                            break;
                        case "3":
                            MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                            break;
                        case "4":
                            MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                            break;
                        case "5":
                            MessageB_Activity.enterActivity(mActivity, messageItem.getWorkId(), 3, messageItem.getId());
                            break;
                        case "6":

                            Intent intent = new Intent(mActivity, YuyueDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("messageItem", messageItem);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            break;
                        case "7":

                            Intent intent_system = new Intent(mActivity, SystemMessageActivity.class);
                            startActivity(intent_system);

                            break;
                        default:
                            break;

                    }
                }


            }
        });


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_parter_message;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("搭档反馈消息");

    }
}
