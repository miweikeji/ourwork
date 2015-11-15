package app.activity.user;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.BaseActivity;
import app.adapter.HintAdapter;
import app.adapter.ProtectRecordAdapter;
import app.adapter.ScoreAdapter;
import app.entity.MyScore;
import app.entity.ProtectRecord;
import app.entity.Score;
import app.entity.ScoreResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class IntegralActivity extends BaseActivity implements AbsListView.OnScrollListener {
    private ScoreAdapter adapter;
    List<Score> items = new ArrayList<>();
    private TextView tv_score;
    private int p=1;
    private PullToRefreshListView pull_list;
    private int page;
    private boolean isOver;
    private boolean isFirstLoaded;
    private View inflate;

    @Override
    public void obtainData() {
        if (!isFirstLoaded){
            showWaitingDialog();
        }
        HttpRequest.getScoreList(mActivity, p, new ICallback<ScoreResult>() {
            @Override
            public void onSucceed(ScoreResult result) {
                isFirstLoaded=true;
                pull_list.onRefreshComplete();
                disMissWaitingDialog();
                List<Score> list = result.getScorList();
                page=result.getPage();
                if(page==0){
                    isOver = false;
                }else {
                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                        items.addAll(list);
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_list, mActivity, inflate);
                    }
                    if(p==1){
                        adapter = new ScoreAdapter(mActivity, items);
                        pull_list.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                    }
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
        pull_list= (PullToRefreshListView)findViewById(R.id.listView);
        pull_list.setOnScrollListener(this);
        pull_list.setMode(PullToRefreshBase.Mode.DISABLED);

        inflate = getLayoutInflater().inflate(R.layout.footview, null);

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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (visibleItemCount+firstVisibleItem- totalItemCount>= Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_list, mActivity, inflate);
            }
            isOver=false;
            obtainData();
        }

    }
}
