package app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.miweikeij.app.R;

import app.adapter.GroupMemberAdapter;
import app.views.MyGridView;
import app.views.NavigationBar;

public class GroupMembersActivity extends BaseActivity implements NavigationBar.RightOnClick {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        PullToRefreshScrollView pull_to_grid = (PullToRefreshScrollView)findViewById(R.id.pull_to_grid);
        MyGridView grid_memeber = (MyGridView)findViewById(R.id.grid_memeber);
        grid_memeber.setFocusable(false);
        GroupMemberAdapter adapter = new GroupMemberAdapter(this);
        grid_memeber.setAdapter(adapter);
        pull_to_grid.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_grid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

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
