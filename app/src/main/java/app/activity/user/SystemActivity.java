package app.activity.user;

import app.activity.BaseActivity;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/11/11.
 */
public class SystemActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return 0;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("系统消息");

    }
}
