package app.activity.user;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class IntegralActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_integral;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("我的积分");

    }
}
