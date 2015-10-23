package app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;

import app.views.NavigationBar;

public class FindFartnerActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        RelativeLayout rl_un_arrangeTask= (RelativeLayout)findViewById(R.id.rl_un_arrangeTask);
        rl_un_arrangeTask.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_find_fartner;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("找搭档");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_un_arrangeTask:
                startActivity(new Intent(this,UnArrangeTaskActivity.class));
                break;
        }
    }
}
