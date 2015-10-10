package app.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.entity.Meta;
import app.entity.RegisterInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.utils.MobileOS;
import app.views.NavigationBar;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_phone;
    private EditText et_psw;
    private int status;
    private  EditText et_code;
    private  String phone;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
       Button btn_send_code = (Button)findViewById(R.id.btn_send_code);
        Button btn_register = (Button)findViewById(R.id.btn_register);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_code = (EditText) findViewById(R.id.et_code);
        btn_register.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_send_code.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_register;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("注册");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send_code:
                phone = et_phone.getText().toString().trim();
                boolean isPhoneExit = MobileOS.isMobileNO(phone);
                if(isPhoneExit){
                    sendCode(phone);
                }else{
                    Toast.makeText(this,"您输入的电话号码有误",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_register:
               String psw =  et_psw.getText().toString().trim();
                String code = et_code.getText().toString().trim();
                if(psw.length()>5){
                    if(status!=0&&!"".equals(code)&&code!=null){
                        register(psw,code);
                    }
                }else{
                    Toast.makeText(this,"密码长度不能小于6六位",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void register(String psw,String code) {
        HttpRequest.registerHttp(this, new ICallback<RegisterInfo>() {
            @Override
            public void onSucceed(RegisterInfo result) {
                MyLog.e("","result="+result.toString());
                Toast.makeText(RegisterActivity.this,"result.toString()",Toast.LENGTH_LONG).show();
                BasicInfoActivity.startActivity(RegisterActivity.this,result.getId());
                finish();
            }

            @Override
            public void onFail(String error) {

            }
        },phone,psw,code);

    }

    private void sendCode(String phone ) {
         HttpRequest.testHttp(this, new ICallback<Meta>() {
              @Override
              public void onSucceed(Meta result) {
                  status=789;
              }

                @Override
               public void onFail(String error) {

                }
         }, phone, "0");
    }
}
