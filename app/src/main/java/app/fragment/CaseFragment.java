package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.CaseAdapter;
import app.entity.Case;
import app.entity.CaseResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/12.
 */
public class CaseFragment extends Fragment {

    private PullToRefreshListView pull_case;
    private int p=1;
    private ArrayList<Case> allCases = new ArrayList<Case>();
    private CaseAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_case, null);
        initUI(layout);
        netWorkData();
        return layout;
    }

    private void netWorkData() {

        HttpRequest.allCaseHttp(getActivity(), "101", p, new ICallback<CaseResult>() {
            @Override
            public void onSucceed(CaseResult result) {
                List<Case> cases = result.getHouseList();
                allCases.addAll(cases);
                if(p==1){
                    adapter = new CaseAdapter(getActivity(),allCases);
                    pull_case.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                pull_case.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
                Uihelper.showToast(getActivity(),error);
            }
        });

    }

    private void initUI(View layout) {
         pull_case =(PullToRefreshListView)layout.findViewById(R.id.pull_case);
        pull_case.setMode(PullToRefreshBase.Mode.BOTH);
        pull_case.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                allCases.clear();
                p=1;
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                p++;
                netWorkData();
            }
        });
    }

}