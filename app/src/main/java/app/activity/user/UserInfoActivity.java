package app.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.popupwindow.WorkTypePopup;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener,
        WorkTypePopup.OnPopupWindowClickListener {


    private EditText et_address;
    private EditText et_work_age;
    private EditText et_case;
    private EditText et_bankname;
    private EditText et_cardNum;
    private EditText et_baseprice;
    private EditText et_servicezone;
    private EditText et_area;
    private EditText et_ceommend_telephone;
    private EditText et_introduce;
    private EditText et_openbank;
    private RelativeLayout rl_work_type;
    private EditText et_age;
    private EditText et_name;
    private TextView tv_work_type;
    private String workType;
    private String id;
    String[] items = {"水电工", "泥水工 ", "木工 ", "油漆工", "门窗安装工", "敲打搬运工"};
    private TextView tv_account;

    @Override
    public void obtainData() {
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

        et_area = (EditText) findViewById(R.id.et_area);
        et_case = (EditText) findViewById(R.id.et_case);
        et_baseprice = (EditText) findViewById(R.id.et_baseprice);
        et_servicezone = (EditText) findViewById(R.id.et_servicezone);
        et_openbank = (EditText) findViewById(R.id.et_openbank);
        et_bankname = (EditText) findViewById(R.id.et_bankname);
        et_cardNum = (EditText) findViewById(R.id.et_cardNum);
        et_ceommend_telephone = (EditText) findViewById(R.id.et_ceommend_telephone);
        et_introduce = (EditText) findViewById(R.id.et_introduce);

        tv_account = (TextView) findViewById(R.id.tv_account);
        et_introduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();
                if (text.length() > 140) {
                    Uihelper.showToast(mActivity, "最多输入140个字");
                } else {
                    tv_account.setText(text.length() + "");
                }

            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_basic_info;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setImgBackInVisible();
        mBar.setCenterTitle("个人资料");
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

        String area = et_area.getText().toString();
        String user_case = et_case.getText().toString();
        String baseprice = et_baseprice.getText().toString();
        String servicezone = et_servicezone.getText().toString();
        String openbank = et_openbank.getText().toString();
        String bankname = et_bankname.getText().toString();
        String cardnum = et_cardNum.getText().toString();
        String commendphone = et_ceommend_telephone.getText().toString();
        String introduce = et_introduce.getText().toString();


        showWaitingDialog();
        HttpRequest.myInfoEdit(mActivity, name, age, worktype, workage, area, user_case, address, bankname,
                cardnum, bankname, commendphone, baseprice, servicezone, introduce, new ICallback<Meta>() {
                    @Override
                    public void onSucceed(Meta result) {
                        disMissWaitingDialog();
                        Uihelper.showToast(mActivity, "保存信息成功");
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
}
