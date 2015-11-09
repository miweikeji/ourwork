package app.activity;

import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.HousesByLyfAdapter;
import app.entity.HousesByLyf;
import app.entity.HousesByLyfResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/18.
 */
public class ReservationHistoryActivity extends BaseActivity {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<HousesByLyf> allCases = new ArrayList<HousesByLyf>();
    private HousesByLyfAdapter adapter;
    @Override
    public void obtainData() {


    }

    @Override
    public void initUI() {
        pull_case =(PullToRefreshListView)findViewById(R.id.pull_case);
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
        HttpRequest.getHousesByHistoryLyf(this, "100", p, new ICallback<HousesByLyfResult>() {
            @Override
            public void onSucceed(HousesByLyfResult result) {
                List<HousesByLyf> mHousesByLyf = result.getHouseList();
                allCases.addAll(mHousesByLyf);
                if (p == 1) {
                    adapter = new HousesByLyfAdapter(ReservationHistoryActivity.this, allCases);
                    pull_case.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                pull_case.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(ReservationHistoryActivity.this, error);
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
}
