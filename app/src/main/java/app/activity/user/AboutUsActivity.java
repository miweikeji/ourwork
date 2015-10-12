package app.activity.user;

import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.utils.MobileOS;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/12.
 */
public class AboutUsActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
       TextView tvVersion= (TextView) findViewById(R.id.tv_version);
        TextView tvPlain= (TextView) findViewById(R.id.tv_aboutus_plain);
        tvPlain.setText(getResources().getText(R.string.aboutus));
        tvVersion.setText("版本号" + MobileOS.getAppVersionName(this));

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_aboutus;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("关于一匠一家");

    }
}
