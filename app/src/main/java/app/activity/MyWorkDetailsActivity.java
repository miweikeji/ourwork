package app.activity;

import android.content.Intent;
import android.view.View;

import com.miweikeij.app.R;

import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class MyWorkDetailsActivity extends BaseActivity implements NavigationBar.RightOnClick {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    public void toProdDetails(View view){
        startActivity(new Intent(this,HouseActivity.class));
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_my_work_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
        mBar.setRightTwoTitle("看他主页");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        startActivity(new Intent(MyWorkDetailsActivity.this,CraftsmanZoneActivity.class));
    }
}
