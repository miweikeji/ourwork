package app.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.List;

import app.dialog.DialogTools;
import app.entity.Data;
import app.entity.Message;
import app.entity.Meta;
import app.entity.UserInfo;
import app.entity.WorkDetailResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class WorkDetailsActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_work_area;
    private TextView tv_service;
    private TextView tv_feestyle;
    private TextView tv_price;
    private List<Data> data;
    private Message message;
    private Button btn_apply;
    private String workId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        workId = getIntent().getStringExtra("workId");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
        netWorkData();
    }

    private void netWorkData() {
        if (TextUtils.isEmpty(workId)){
            return;
        }
        showWaitingDialog();
        HttpRequest.getWorkDetail(this, UserUtil.getUserId(mActivity), workId, new ICallback<WorkDetailResult>() {
            @Override
            public void onSucceed(WorkDetailResult result) {

                message = result.getMessage();
                data = result.getData();
                String apply = result.getApply();
                if ("1".equals(apply)) {
                    btn_apply.setText("接单成功");
                    btn_apply.setEnabled(false);
                    btn_apply.setClickable(false);
                    btn_apply.setBackgroundResource(R.drawable.grey_bg_false);
                } else if ("2".equals(apply)) {
                    btn_apply.setText("接单失败");
                }


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
        Button btn_apply = (Button) findViewById(R.id.btn_apply);
        tv_work_area = (TextView) findViewById(R.id.tv_work_area);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_feestyle = (TextView) findViewById(R.id.tv_feestyle);
        tv_price = (TextView) findViewById(R.id.tv_price);
        RelativeLayout toTime = (RelativeLayout) findViewById(R.id.rl_to_time);
        toTime.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_work_details;
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

    public void apply(View view) {
        if (message != null) {
            String id = message.getId();
            showWaitingDialog();
            HttpRequest.applyOder(this, UserInfo.getInstance().id, id, new ICallback<Meta>() {
                @Override
                public void onSucceed(Meta result) {
                    Uihelper.showToast(WorkDetailsActivity.this, result.getMsg());
                    disMissWaitingDialog();
                }

                @Override
                public void onFail(String error) {
                    Uihelper.showToast(WorkDetailsActivity.this, error);
                    disMissWaitingDialog();
                }
            });
        }
    }


}
