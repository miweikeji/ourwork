package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.HintAdapter;
import app.adapter.HousesByLyfAdapter;
import app.entity.HousesByLyf;
import app.entity.HousesByLyfResult;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/18.
 */
public class ReservationHistoryActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<HousesByLyf> allCases = new ArrayList<HousesByLyf>();
    private HousesByLyfAdapter adapter;

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
        pull_case.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_case.setOnScrollListener(this);
        pull_case.setOnItemClickListener(this);
        pull_case.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                allCases.clear();
                p = 1;
                if(adapter!=null){
                    adapter.notifyDataSetChanged();
                }
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
        HttpRequest.getHousesByHistoryLyf(this, UserInfo.getInstance().getId(), p, new ICallback<HousesByLyfResult>() {
            @Override
            public void onSucceed(HousesByLyfResult result) {
                List<HousesByLyf> mHousesByLyf = result.getHouseList();
                page = result.getTotalpage();
                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(ReservationHistoryActivity.this);
                    pull_case.getRefreshableView().setDividerHeight(0);
                    pull_case.setAdapter(hintAdapter);
                } else {
                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                        allCases.addAll(mHousesByLyf);
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_case, ReservationHistoryActivity.this, inflate);
                    }

                    if (p == 1) {
                        adapter = new HousesByLyfAdapter(ReservationHistoryActivity.this, allCases);
                        pull_case.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }
                pull_case.onRefreshComplete();
                disMissWaitingDialog();
                isFisrstShow = true;
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(ReservationHistoryActivity.this, error);
                disMissWaitingDialog();
                isFisrstShow = true;
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_reservation_history;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("预约历史");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ReservationHistoryActivity.this,ReservationDetailsActivity.class);
        Bundle bundle = new Bundle();
        HousesByLyf byLyf = allCases.get(position-1);
        bundle.putSerializable("ReservationDetailsActivity",byLyf);
        intent.putExtras(bundle);
        intent.putExtra("FromeActvity","ReservationHistoryActivity");
        startActivity(intent);
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
