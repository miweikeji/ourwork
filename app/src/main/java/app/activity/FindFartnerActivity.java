package app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.miwei.jzj_system.R;

import app.views.NavigationBar;

public class FindFartnerActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        RelativeLayout rl_un_arrangeTask= (RelativeLayout)findViewById(R.id.rl_un_arrangeTask);
        rl_un_arrangeTask.setOnClickListener(this);
        RelativeLayout rl_new_decoration= (RelativeLayout)findViewById(R.id.rl_new_decoration);
        rl_new_decoration.setOnClickListener(this);
        RelativeLayout rl_built_task= (RelativeLayout)findViewById(R.id.rl_built_task);
        rl_built_task.setOnClickListener(this);
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
            case R.id.rl_new_decoration:
                startActivity(new Intent(this,NewDecorationActivity.class));
                break;
            case R.id.rl_built_task:
                startActivity(new Intent(this,BuiltTaskActivity.class));
                break;
        }
    }
}
