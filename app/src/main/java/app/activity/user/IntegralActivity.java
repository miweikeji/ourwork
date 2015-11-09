package app.activity.user;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.adapter.ScoreAdapter;
import app.entity.MyScore;
import app.entity.Score;
import app.entity.ScoreResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class IntegralActivity extends BaseActivity {
    private ScoreAdapter adapter;
    private ListView listView;
    List<Score> items = new ArrayList<>();
    private TextView tv_score;

    @Override
    public void obtainData() {

        showWaitingDialog();
        HttpRequest.getScoreList(mActivity, 1, new ICallback<ScoreResult>() {
            @Override
            public void onSucceed(ScoreResult result) {
                disMissWaitingDialog();
                List<Score> list = result.getScorList();
                if (list != null && list.size() > 0) {
                    items.addAll(list);
                    adapter = new ScoreAdapter(mActivity, items);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);

            }
        });

        HttpRequest.getMyScore(mActivity, new ICallback<MyScore>() {
            @Override
            public void onSucceed(MyScore result) {
                String score = result.getScore();
                tv_score.setText(score);
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(mActivity, error);

            }
        });


    }

    @Override
    public void initUI() {

        tv_score = (TextView) findViewById(R.id.tv_integral);
        listView = (ListView) findViewById(R.id.listView);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_integral;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("我的积分");
        mBar.setRightTitle("积分规则");
        mBar.setRightOnClick(new NavigationBar.RightOnClick() {
            @Override
            public void setRightOnClick() {
                startActivity(new Intent(mActivity, IntegralRuleActivity.class));
            }
        });

    }
}
