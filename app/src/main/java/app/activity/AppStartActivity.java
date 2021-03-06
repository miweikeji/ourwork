package app.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.miwei.jzj_system.R;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import app.entity.Meta;
import app.entity.UserInfoResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.utils.Constants;
import app.utils.MobileOS;
import app.utils.Pref;
import app.utils.UserUtil;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/28.
 */
public class AppStartActivity extends BaseActivity  {
    @Override
    public void obtainData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private ImageView img_start;
    private static final int pageCount = 4;
    private ViewPager mViewPager;
    private LinearLayout framePages;
    @Override
    public void initUI() {
        ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        img_start = (ImageView) findViewById(R.id.img_start);
        framePages = (LinearLayout) findViewById(R.id.frame_pages);


        //      设置友盟渠道号
        String channelId = Pref.getString(Pref.CHANNEL_ID, this, "");
        if (TextUtils.isEmpty(channelId)) {
            Pref.saveString(Pref.CHANNEL_ID, MobileOS.getChannelName(this), this);
        } else {
            AnalyticsConfig.setChannel(channelId);
        }


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
        }, 2000);



    }


    private void splashFinish() {
        Boolean isFitst = UserUtil.getIsFitst(this);
        if(isFitst){
            Intent intent =new Intent(AppStartActivity.this,MainActivity.class);
            if(UserUtil.hasLogin(this)){
                intent.putExtra(Constants.USER_PROFESSION_TYPE, UserUtil.getUserProfession(AppStartActivity.this));
            }
            else {

            }
            startActivity(intent);
        }else {
            startActivity(new Intent(AppStartActivity.this,GuideActivity.class));
        }

        finish();
    }

    private void getUserInfo(String phone, String psw) {//每次打开获取个人信息
        HttpRequest.loginHttp(this, phone, psw, new ICallback<UserInfoResult>() {
            @Override
            public void onSucceed(UserInfoResult result) {
                MyLog.e("","UserInfoResult=="+result.getCrafts().toString());
            }

            @Override
            public void onFail(String error) {
//                Uihelper.showToast(mActivity, error);
            }
        });
        //传设备token到服务器，用于推送消息
        HttpRequest.addUmengDeviceToken(this, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {

            }

            @Override
            public void onFail(String error) {

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
