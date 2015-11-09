package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.popupwindow.WorkTypePopup;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.NavigationBar;

public class BasicInfoActivity extends BaseActivity implements View.OnClickListener,
        WorkTypePopup.OnPopupWindowClickListener {


    private EditText et_address;
    private EditText et_work_age;
    private RelativeLayout rl_work_type;
    private EditText et_age;
    private EditText et_name;
    private TextView tv_work_type;
    private String workType="1";
    private String id;
    String[] items = {"水电工", "泥水工 ", "木工 ", "油漆工", "门窗安装工", "敲打搬运工"};

    @Override
    public void obtainData() {
        id = getIntent().getStringExtra("USERID");
    }

    @Override
    public void initUI() {

        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        rl_work_type = (RelativeLayout) findViewById(R.id.rl_work_type);
        et_work_age = (EditText) findViewById(R.id.et_work_age);
        et_address = (EditText) findViewById(R.id.et_address);
        tv_work_type = (TextView) findViewById(R.id.tv_work_type);
        Button btn_complete = (Button) findViewById(R.id.btn_complete);
        rl_work_type.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_first_info;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setImgBackInVisible();
        mBar.setCenterTitle("基础信息");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                String name = et_name.getText().toString().trim();
                String age = et_age.getText().toString().trim();

                String workage = et_work_age.getText().toString().trim();
                String address = et_address.getText().toString().trim();
                if (!"".equals(name) && !"".equals(age) && !"".equals(workage) && !"".equals(address) && !"".equals(workType)) {
                    complete(name, age, workType, workage, address);
                } else {
                    Toast.makeText(this, "请填写信息完整", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.rl_work_type:
                WorkTypePopup popup = new WorkTypePopup(this, items);
                popup.showAsDropDown(v);
                popup.setOnPopupWindowClickListener(this);
                break;
        }
    }

    private void complete(String name, String age, String worktype, String workage, String address) {
        showWaitingDialog();
        HttpRequest.infoHttp(this, UserUtil.getUserId(mActivity), name, age, worktype, workage, address, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "设置信息成功");
                finish();

            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);

            }
        });
    }

    @Override
    public void onPopupWindowItemClick(int position) {
        workType = (position + 1) + "";
        tv_work_type.setText(items[position]);
    }

    public static void startActivity(Activity activity, String id) {

        Intent intent = new Intent(activity, BasicInfoActivity.class);
        intent.putExtra("USERID", id);
        activity.startActivity(intent);
    }
}
