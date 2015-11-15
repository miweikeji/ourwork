package app.activity.mywork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.List;

import app.activity.BaseActivity;
import app.dialog.DialogTools;
import app.entity.Data;
import app.entity.MessageDetail;
import app.entity.MessageDetailResult;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/27.
 */
public class MessageA_Activity extends BaseActivity implements View.OnClickListener {
    private TextView tv_work_area;
    private TextView tv_service;
    private TextView tv_feestyle;
    private TextView tv_price;
    private List<Data> data;
    private MessageDetail message;
    private String workId;
    private String messageId;
    private int enterState;
    private int messageState;
    private Button appstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        workId = intent.getStringExtra("workId");
        messageId = intent.getStringExtra("messageId");
        enterState = intent.getIntExtra("enterState", 0);

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

                if (messageState == 1) {
                    appstate.setVisibility(View.VISIBLE);
                    appstate.setText("申请接单成功");
                } else if (messageState == 2) {
                    appstate.setVisibility(View.VISIBLE);
                    appstate.setText("申请接单失败");
                } else if (messageState == 3) {
                    findViewById(R.id.frame_apply).setVisibility(View.VISIBLE);
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
        tv_work_area = (TextView) findViewById(R.id.tv_work_area);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_feestyle = (TextView) findViewById(R.id.tv_feestyle);
        tv_price = (TextView) findViewById(R.id.tv_price);
        RelativeLayout toTime = (RelativeLayout) findViewById(R.id.rl_to_time);

        appstate = (Button) findViewById(R.id.btn_applystate);

        toTime.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_message_a;
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

    //接受邀请
    public void btn_acceptapply(View view) {
        if (TextUtils.isEmpty(messageId)){
            return;
        }
        showWaitingDialog();
        HttpRequest.acceptInvite(mActivity, messageId, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "发送成功");
                finish();
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);

            }
        });
    }

    //拒绝邀请
    public void btn_refuseapply(View view) {
        if (TextUtils.isEmpty(messageId)){
            return;
        }
        showWaitingDialog();
        HttpRequest.refuseInvite(mActivity, messageId, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,"发送成功");
                finish();
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);

            }
        });

    }

    public static void enterActivity(Activity activity, String workId, int enterstate,String messageId) {

        Intent intent = new Intent(activity, MessageA_Activity.class);
        intent.putExtra("workId", workId);
        intent.putExtra("enterState", enterstate);
        intent.putExtra("messageId", messageId);
        activity.startActivity(intent);
    }
}
