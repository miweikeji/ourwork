package app.activity.mywork;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.List;

import app.activity.BaseActivity;
import app.dialog.DialogTools;
import app.entity.Data;
import app.entity.MessageDetail;
import app.entity.MessageDetailResult;
import app.net.HttpRequest;
import app.net.ICallback;
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
    private Button btn_apply;
    @Override
    public void obtainData() {
        netWorkData();
    }

    private void netWorkData() {
        showWaitingDialog();
        HttpRequest.getMessageOrderDetail(this, "101", "133","1", new ICallback<MessageDetailResult>() {
            @Override
            public void onSucceed(MessageDetailResult result) {

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
                disMissWaitingDialog();
            }

            @Override
            public void onFail(String error) {

                disMissWaitingDialog();
            }
        });
    }

    @Override
    public void initUI() {
        Button btn_apply = (Button)findViewById(R.id.btn_apply);
        tv_work_area = (TextView)findViewById(R.id.tv_work_area);
        tv_service = (TextView)findViewById(R.id.tv_service);
        tv_feestyle = (TextView)findViewById(R.id.tv_feestyle);
        tv_price = (TextView)findViewById(R.id.tv_price);
        RelativeLayout toTime = (RelativeLayout)findViewById(R.id.rl_to_time);
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
        switch (view.getId()){
            case R.id.rl_to_time:
                DialogTools.timeShow(this, data).show();
                break;
        }
    }

    public void apply(View view){
    }
}
