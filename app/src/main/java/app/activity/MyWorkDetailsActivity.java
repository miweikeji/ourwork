package app.activity;

import com.miweikeij.app.R;

import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class MyWorkDetailsActivity extends BaseActivity{


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_my_work_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
    }
}
