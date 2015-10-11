package app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.miweikeij.app.R;

import app.views.NavigationBar;

public class GroupMembersActivity extends BaseActivity implements NavigationBar.RightOnClick {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_group_members;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠班组");
        mBar.setRightTitle("添加");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        startActivity(new Intent(GroupMembersActivity.this,AddMembersActivity.class));
    }
}
