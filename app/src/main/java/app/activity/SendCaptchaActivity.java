package app.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.miweikeij.app.R;

import app.entity.Meta;
import app.entity.RegisterInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.utils.MobileOS;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class SendCaptchaActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_phone;
    private EditText et_psw;
    private EditText et_code;
    private String phone;
    private Button btn_summit;
    private Button btn_send_code;
    private boolean isTimer;// 是否可以计时
    private static Handler handler;
    private Thread thread;
    private MyRunnable myRunnable;
    private boolean isSending;
    private ImageView btnViewState;
    private boolean isView;
    private boolean isForgetPsw;


    @Override
    public void obtainData() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String timeInfo = msg.getData().getString("time");
                btn_send_code.setText(timeInfo + "秒后重新获取");
                if ("0".equals(timeInfo)) {
                    isSending = false;
                    btn_send_code.setEnabled(true);
                    btn_send_code.setBackgroundResource(R.drawable.sl_get_code);
                    btn_send_code.setText("重新获取");
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void initUI() {
        btn_send_code = (Button) findViewById(R.id.btn_send_code);
        btn_summit = (Button) findViewById(R.id.btn_summit);
        et_psw = (EditText) findViewById(R.id.et_psw);
        et_code = (EditText) findViewById(R.id.et_code);
        btn_summit.setOnClickListener(this);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_send_code.setOnClickListener(this);
        btnViewState = (ImageView) findViewById(R.id.tv_view_state);
        btnViewState.setOnClickListener(this);

        isForgetPsw = getIntent().getBooleanExtra("isForgetPsw", false);
        if (isForgetPsw) {
            btn_summit.setText("完成");
            et_psw.setHint("输入新密码");
            mBar.setTitle("修改密码");
        }

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

        String psw = et_psw.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        phone = et_phone.getText().toString().trim();

        switch (view.getId()) {
            case R.id.btn_send_code:
                if (!TextUtils.isEmpty(phone)) {
                    boolean isPhoneExit = MobileOS.isMobileNO(phone);
                    if (isPhoneExit) {
                        sendCode(phone);
                    } else {
                        Toast.makeText(this, "您输入的是一个无效的手机号码", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Uihelper.showToast(mActivity, "手机号码不能为空");
                }

                break;
            case R.id.btn_summit:
                if (!TextUtils.isEmpty(phone)) {
                    boolean isPhoneExit = MobileOS.isMobileNO(phone);
                    if (isPhoneExit) {
                        if (isSending) {
                            if (!TextUtils.isEmpty(code)) {

                                if (!TextUtils.isEmpty(psw)) {

                                    if (psw.length() > 5) {
                                        summit(psw, code);
                                    } else {
                                        Toast.makeText(this, "密码长度不能小于6六位", Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
                                }

                            } else {
                                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(this, "请先获取验证码", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "您输入的是一个无效的手机号码", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Uihelper.showToast(mActivity, "手机号码不能为空");
                }

                break;
            case R.id.tv_view_state:
                if (isView) {
                    et_psw.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    et_psw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                isView = !isView;
                break;
            default:
                break;
        }
    }

    private void summit(String psw, String code) {
        if (isForgetPsw) {

            HttpRequest.findPsw(this, new ICallback<Meta>() {
                @Override
                public void onSucceed(Meta result) {
                    MyLog.e("", "result=" + result.toString());
                    Uihelper.showToast(mActivity, "修改成功");
                    finish();
                }

                @Override
                public void onFail(String error) {
                    Uihelper.showToast(mActivity, error);

                }
            }, phone, psw, code);
        } else {

            HttpRequest.registerHttp(this, new ICallback<RegisterInfo>() {
                @Override
                public void onSucceed(RegisterInfo result) {
                    MyLog.e("", "result=" + result.toString());
                    Toast.makeText(SendCaptchaActivity.this, "result.toString()", Toast.LENGTH_LONG).show();
                    BasicInfoActivity.startActivity(SendCaptchaActivity.this, result.getId());
                    finish();
                }

                @Override
                public void onFail(String error) {
                    Uihelper.showToast(mActivity, error);

                }
            }, phone, psw, code);
        }

    }

    private void sendCode(String phone) {
        String type = "";
        if (isForgetPsw) {
            type = "1";
        } else {
            type = "0";
        }
        HttpRequest.sendCaptcha(this, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                isSending = true;
//                mWaitingDialog.dismiss();
                btn_send_code.setEnabled(false);
                btn_send_code.setBackgroundResource(R.drawable.sl_get_code);
                myRunnable = new MyRunnable();
                thread = new Thread(myRunnable);
                thread.start(); // 启动线程，进行倒计时
                isTimer = true;
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(mActivity, error);

            }
        }, phone, type);
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {

            for (int i = 60; i >= 0; i--) {
                if (isTimer) {
                    Bundle bundle = new Bundle();
                    bundle.putString("time", i + "");
                    Message message = Message.obtain();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000); // 休眠1秒钟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            isTimer = false;

        }
    }

}