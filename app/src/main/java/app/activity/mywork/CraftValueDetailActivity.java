package app.activity.mywork;

import app.activity.BaseActivity;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/26.
 */
public class CraftValueDetailActivity extends BaseActivity {
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
        mBar.setTitle("评价详情");
    }
}
