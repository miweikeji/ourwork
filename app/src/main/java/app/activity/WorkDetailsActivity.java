package app.activity;

import android.view.View;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;

import app.dialog.DialogTools;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class WorkDetailsActivity  extends BaseActivity implements View.OnClickListener {
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        RelativeLayout toTime = (RelativeLayout)findViewById(R.id.rl_to_time);
        toTime.setOnClickListener(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_message_a;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_to_time:
                DialogTools.timeShow(this).show();
                break;
        }
    }
}
