package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.BuiltTaskAdapter;
import app.adapter.MyWorkAdapter;
import app.entity.BuiltTask;
import app.entity.BuiltTaskResult;
import app.entity.ConstructPlan;
import app.entity.ConstructPlanResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/28.
 */
public class BuiltTaskActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<BuiltTask> allCases = new ArrayList<BuiltTask>();
    private BuiltTaskAdapter adapter;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        pull_case =(PullToRefreshListView)findViewById(R.id.pull_case);
        pull_case.setOnItemClickListener(this);
        pull_case.setMode(PullToRefreshBase.Mode.BOTH);
        pull_case.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                allCases.clear();
                p = 1;
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                p++;
                netWorkData();
            }
        });

        netWorkData();
    }

    private void netWorkData() {

        HttpRequest.getTask(BuiltTaskActivity.this, "100", p, new ICallback<BuiltTaskResult>() {
            @Override
            public void onSucceed(BuiltTaskResult result) {
                List<BuiltTask> cases = result.getHouseList();
                allCases.addAll(cases);
                if (p == 1) {
                    adapter = new BuiltTaskAdapter(BuiltTaskActivity.this, allCases);
                    pull_case.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                pull_case.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(BuiltTaskActivity.this, error);
            }
        });

    }
    @Override
    public int onCreateMyView() {
        return R.layout.activity_built_task;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("修改已建计划");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            startActivity(new Intent(this,ChangeTasksActivity.class));
    }
}
