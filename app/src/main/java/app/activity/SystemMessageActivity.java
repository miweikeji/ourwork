package app.activity;

import com.miweikeij.app.R;

import app.views.NavigationBar;

/**
 * Created by tlt on 2015/11/12.
 */
public class SystemMessageActivity extends BaseActivity {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_system;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("系统消息");


    }
}
