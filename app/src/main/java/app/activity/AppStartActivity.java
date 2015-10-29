package app.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import com.miweikeij.app.R;

import app.entity.UserInfo;
import app.entity.UserInfoResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Constants;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/28.
 */
public class AppStartActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        if(UserUtil.hasLogin(this)){
            String phone = UserUtil.getUserPhone(this);
            String psw = UserUtil.getUserPsw(this);
            getUserInfo(phone, psw);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashFinish();
            }
        }, 1500);



    }

    private void splashFinish() {
        Intent intent =new Intent(AppStartActivity.this,MainActivity.class);
        if(UserUtil.hasLogin(this)){
            intent.putExtra(Constants.USER_PROFESSION_TYPE, UserUtil.getUserProfession(AppStartActivity.this));
        }
        startActivity(intent);

    }

    private void getUserInfo(String phone, String psw) {//每次打开获取个人信息
        HttpRequest.loginHttp(this, phone, psw, new ICallback<UserInfoResult>() {
            @Override
            public void onSucceed(UserInfoResult result) {

            }

            @Override
            public void onFail(String error) {
//                Uihelper.showToast(mActivity, error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        // 隐藏android系统的状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_app_start;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }
}
