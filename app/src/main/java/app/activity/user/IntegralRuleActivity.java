package app.activity.user;

import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.adapter.ProtectRecordAdapter;
import app.entity.ProtectRecord;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/18.
 */
public class IntegralRuleActivity extends BaseActivity {

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_integral_rule;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("积分规则");

    }
}
