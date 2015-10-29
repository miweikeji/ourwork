package app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.WorkPlanDetailsActivity;
import app.adapter.MyWorkAdapter;
import app.entity.ConstructPlan;
import app.entity.ConstructPlanResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/11.
 */
public class WorkConstructionFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<ConstructPlan> allCases = new ArrayList<ConstructPlan>();
    private MyWorkAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_work_construction, null);
        initUI(layout);
        netWorkData();
        return layout;
    }
    private void netWorkData() {

        HttpRequest.constructPlan(getActivity(), "100", p, new ICallback<ConstructPlanResult>() {
            @Override
            public void onSucceed(ConstructPlanResult result) {
                List<ConstructPlan> cases = result.getHouseList();
                allCases.addAll(cases);
                if (p == 1) {
                    adapter = new MyWorkAdapter(getActivity(), allCases);
                    pull_case.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                pull_case.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(getActivity(), error);
            }
        });

    }

    private void initUI(View layout) {
        pull_case =(PullToRefreshListView)layout.findViewById(R.id.pull_case);
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ConstructPlan plan = allCases.get(position - 1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ConstructPlan",plan);
        Intent intent = new Intent(getActivity(), WorkPlanDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
