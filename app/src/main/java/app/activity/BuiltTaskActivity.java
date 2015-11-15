package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.BuiltTaskAdapter;
import app.adapter.HintAdapter;
import app.entity.BuiltTask;
import app.entity.BuiltTaskResult;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/28.
 */
public class BuiltTaskActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<BuiltTask> allCases = new ArrayList<BuiltTask>();
    private BuiltTaskAdapter adapter;

    private int page;
    private View inflate;
    private boolean isOver;
    private boolean isFisrstShow;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        inflate = getLayoutInflater().inflate(R.layout.footview, null);
        pull_case =(PullToRefreshListView)findViewById(R.id.pull_case);
        pull_case.setOnItemClickListener(this);
        pull_case.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_case.setOnScrollListener(this);
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
        if(!isFisrstShow){
            showWaitingDialog();
        }
        HttpRequest.getTask(BuiltTaskActivity.this, UserInfo.getInstance().getId(), p, new ICallback<BuiltTaskResult>() {
            @Override
            public void onSucceed(BuiltTaskResult result) {
                isFisrstShow = true;
                page = result.getPage();

                List<BuiltTask> cases = result.getHouseList();

                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(BuiltTaskActivity.this);
                    pull_case.getRefreshableView().setDividerHeight(0);
                    pull_case.setAdapter(hintAdapter);
                } else {
                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                        allCases.addAll(cases);
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_case, BuiltTaskActivity.this, inflate);
                    }

                    if (p == 1) {
                        adapter = new BuiltTaskAdapter(BuiltTaskActivity.this, allCases);
                        pull_case.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }

                pull_case.onRefreshComplete();
                disMissWaitingDialog();
            }


            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(BuiltTaskActivity.this, error);
                disMissWaitingDialog();
                isFisrstShow = true;
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
//            startActivity(new Intent(this,ChangeTasksActivity.class));
        String bao = allCases.get(position - 1).getBao();
        if("0".equals(bao)){
            String house_id = allCases.get(position - 1).getHouse_id();
            Intent intent = new Intent(this,ChangeTasksActivity.class);
            intent.putExtra("hourseID",house_id);
            startActivity(intent);
        }else {
            Uihelper.showToast(this,"该计划已发布不能进行修改");
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(visibleItemCount+firstVisibleItem>=totalItemCount- Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_case, this, inflate);
            }
            isOver=false;
            netWorkData();
        }
    }
}
