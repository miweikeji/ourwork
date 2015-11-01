package app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import app.activity.HouseActivity;
import app.activity.MyWorkDetailsActivity;
import app.adapter.MyWorkAdapter;
import app.adapter.MyWorksAdapter;
import app.entity.MyWork;
import app.entity.MyWorks;
import app.entity.MyWorksListResult;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/9.
 */
public class CompletedFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View layout;
    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<MyWorks> allCases = new ArrayList<MyWorks>();
    private MyWorksAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_completed,null);
        initUI();
        netWorkData();
        return layout;
    }

    private void netWorkData() {

        HttpRequest.myWorks(getActivity(), "101", "1", p, new ICallback<MyWorksListResult>() {
            @Override
            public void onSucceed(MyWorksListResult result) {
                List<MyWorks> myWorkses = result.getMessage().getList();
                allCases.addAll(myWorkses);
                if (p == 1) {
                    adapter = new MyWorksAdapter(getActivity(), allCases);
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

    private void initUI() {

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

        startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));
    }
}
