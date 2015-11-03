package app.activity;

import com.miweikeij.app.R;

import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ServerTimeActivity extends BaseActivity{
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_server_time;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("服务时间");

    }
}
