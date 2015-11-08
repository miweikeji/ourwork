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

import app.activity.MyWorkDetailsActivity;
import app.adapter.MyWorksAdapter;
import app.entity.MyWorks;
import app.entity.MyWorksListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/9.
 */
public class ConstructionFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<MyWorks> allCases = new ArrayList<MyWorks>();
    private MyWorksAdapter adapter;

    private View layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_construction,null);
        initUI();
        netWorkData();
        return layout;
    }
    private void netWorkData() {

        HttpRequest.myWorks(getActivity(), "101", "0",p, new ICallback<MyWorksListResult>() {
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



