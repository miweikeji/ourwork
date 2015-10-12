package app.activity.user;

import com.miweikeij.app.R;

import app.activity.BaseActivity;
import app.views.NavigationBar;

/**
 * 工头认证
 * Created by tlt on 2015/10/12.
 */

public class JobAuthentActivity extends BaseActivity{
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_jobauthent;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工头认证");

    }
}
