package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import org.litepal.crud.DataSupport;

import app.db.DBConversion;
import app.entity.UserInfo;
import app.entity.UserInfoResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.utils.MobileOS;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.NavigationBar;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_psw;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_phone = (EditText) findViewById(R.id.et_phone);
        Button login = (Button)findViewById(R.id.btn_login);
        TextView forgetPsw = (TextView)findViewById(R.id.forget_psw);
        TextView register = (TextView)findViewById(R.id.toRegister);
        forgetPsw.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        forgetPsw.getPaint().setAntiAlias(true);
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        register.getPaint().setAntiAlias(true);
        register.setOnClickListener(this);
        forgetPsw.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_login;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toRegister:
                Intent intent=new Intent(this,SendCaptchaActivity.class);
                intent.putExtra("isForgetPsw",false);
                startActivity(intent);
                break;
            case R.id.forget_psw:
                Intent intent_psw=new Intent(this,SendCaptchaActivity.class);
                intent_psw.putExtra("isForgetPsw",true);
                startActivity(intent_psw);
                break;
            case R.id.btn_login:
               String phone = et_phone.getText().toString().trim();
               String psw = et_psw.getText().toString().trim();

                if (!TextUtils.isEmpty(phone)){
                    if(MobileOS.isMobileNO(phone)){
                        if(!"".equals(psw)&&psw!=null){
                            login(phone,psw);
                        }else {
                            Uihelper.showToast(this, "请输入密码");
                        }
                    }else{
                        Uihelper.showToast(this, "您输入的电话号码有误");
                    }
                }else {
                    Uihelper.showToast(this, "电话号码不能为空");
                }


                break;
        }
    }

    private void login(final String phone, final String psw) {
        showWaitingDialog();
        HttpRequest.loginHttp(this, phone, psw, new ICallback<UserInfoResult>() {
            @Override
            public void onSucceed(UserInfoResult result) {
                disMissWaitingDialog();
                Uihelper.trace(result.getCrafts().toString());
                UserUtil.saveUserId(LoginActivity.this, result.getCrafts().getId());
                UserUtil.saveUserPsw(LoginActivity.this, psw);
                UserUtil.saveUserPhone(LoginActivity.this, phone);
                UserUtil.saveUserProfession(LoginActivity.this, result.getCrafts().getProfession());
                if (!result.getCrafts().isHasinfo()){
                    BasicInfoActivity.startActivity(LoginActivity.this, result.getCrafts().getId());
                }
                finish();
            }
            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);
            }
        });
    }

    public static void enterActivity(Activity activity) {

        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }
}
