package app.activity.mywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

import app.activity.BaseActivity;
import app.activity.mywork.adapter.ApplyDetailsAdapter;
import app.dialog.DialogTools;
import app.entity.ApplyCrafts;
import app.entity.ApplyCraftsResult;
import app.entity.Data;
import app.entity.InviteCrafts;
import app.entity.InviteCraftsResult;
import app.entity.MessageDetail;
import app.entity.MessageDetailResult;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class MessageB_Activity extends BaseActivity implements View.OnClickListener {
    private TextView tv_work_area;
    private TextView tv_service;
    private TextView tv_feestyle;
    private TextView tv_price;
    private List<Data> data;
    private MessageDetail message;
    private String workId;
    private String messageId;
    private int enterState;
    private MyListView listView;
    private TextView tv_applyname;
    private int messageState;
    private boolean isApply;
    private List<ApplyCrafts> applyCraftList;
    private List<InviteCrafts> inviteCraftList;
    private DisplayImageOptions options;
    private ApplyDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        workId = intent.getStringExtra("workId");
        messageId = intent.getStringExtra("messageId");
        enterState = intent.getIntExtra("enterState", 0);

        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();

        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
        netWorkData();
    }

    private void netWorkData() {
        showWaitingDialog();
        HttpRequest.getMessageOrderDetail(this, "101", workId, enterState + "", new ICallback<MessageDetailResult>() {
            @Override
            public void onSucceed(MessageDetailResult result) {
                disMissWaitingDialog();

                message = result.getMessage();
                data = message.getData();
                tv_work_area.setText(message.getS_addr());
                tv_service.setText(message.getContent());
                String type = message.getCharge_type();
                if ("0".equals(type)) {
                    tv_feestyle.setText("按次服务");
                } else if ("1".equals(type)) {
                    tv_feestyle.setText("按平方");
                } else if ("2".equals(type)) {
                    tv_feestyle.setText("承包价格");
                }
                tv_price.setText(message.getW_money());
                messageState = result.getMessage().getMessageStatus();
                if (messageState == 0) {//有人申请接单
                    isApply = true;
                    tv_applyname.setText("申请人列表");
                } else if (messageState == 3 || messageState == 4 || messageState == 5) {
                    isApply = false;
                    tv_applyname.setText("邀请人列表");
                }

                //获得申请人或邀请人列表
                getListData();
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);
            }
        });
    }

    private void getListData() {
        if (isApply) {//有人申请接单

            HttpRequest.getMessageOrderApplyCrafts(mActivity, "100", "133", 1, new ICallback<ApplyCraftsResult>() {
                @Override
                public void onSucceed(ApplyCraftsResult result) {
                    applyCraftList = result.getCrafts();
                    setData();
                }

                @Override
                public void onFail(String error) {
                    Uihelper.showToast(mActivity, error);
                }
            });


        } else {//邀请人列表
            HttpRequest.getMessageInviteCrafts(mActivity, messageId, new ICallback<InviteCraftsResult>() {
                @Override
                public void onSucceed(InviteCraftsResult result) {
                    inviteCraftList = result.getCrafts();
                    setData();
                }

                @Override
                public void onFail(String error) {
                    Uihelper.showToast(mActivity, error);
                }
            });

        }


    }

    private void setData() {

        adapter=new ApplyDetailsAdapter(mActivity,options,isApply);
        if (isApply){
            adapter.setApplyList(applyCraftList);
            adapter.setMessageID(messageId);
        }else {
            adapter.setInviteList(inviteCraftList);
        }
        listView.setAdapter(adapter);
    }


    @Override
    public void initUI() {

        listView = (MyListView) findViewById(R.id.listViewq);
        tv_applyname = (TextView) findViewById(R.id.tv_applyname);

        tv_work_area = (TextView) findViewById(R.id.tv_work_area);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_feestyle = (TextView) findViewById(R.id.tv_feestyle);
        tv_price = (TextView) findViewById(R.id.tv_price);
        RelativeLayout toTime = (RelativeLayout) findViewById(R.id.rl_to_time);
        toTime.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_message_b;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_to_time:
                DialogTools.timeShow(this, data).show();
                break;
        }
    }

    public static void enterActivity(Activity activity, String workId, int enterstate, String messageId) {

        Intent intent = new Intent(activity, MessageB_Activity.class);
        intent.putExtra("workId", workId);
        intent.putExtra("enterState", enterstate);
        intent.putExtra("messageId", messageId);
        activity.startActivity(intent);
    }
}
