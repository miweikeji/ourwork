package app.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.HttpUtils;
import app.net.ICallback;
import app.utils.MobileOS;
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
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.forget_psw:

                break;
            case R.id.btn_login:
               String phone = et_phone.getText().toString().trim();
               String psw = et_psw.getText().toString().trim();
                if(MobileOS.isMobileNO(phone)){
                    if(!"".equals(psw)&&psw!=null){
                        login(phone,psw);
                    }else {
                        Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this,"您输入的电话号码有误",Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private void login(String phone,String psw) {

        HttpRequest.loginHttp(this, phone, psw, new ICallback<UserInfo>() {
            @Override
            public void onSucceed(UserInfo result) {

                if(result.isHasinfo()){
                    startActivity(new Intent(LoginActivity.this,BasicInfoActivity.class));
                }
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}
