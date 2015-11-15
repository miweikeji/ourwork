package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.MyWorkDetailsActivity;
import app.adapter.HintAdapter;
import app.adapter.MyWorksAdapter;
import app.entity.MyWorks;
import app.entity.MyWorksListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/9.
 */
public class CompletedFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener{


    private int p = 1;
    private PullToRefreshListView pull_list;
    private ArrayList<MyWorks> allCases = new ArrayList<MyWorks>();
    private MyWorksAdapter adapter;

    private int page;
    private View inflate;
    private boolean isOver;
    private boolean isFisrstShow;

    private View layout;
    private Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_construction, null);
        initUI();
        netWorkData();
        return layout;
    }

    private void netWorkData() {

        if (!isFisrstShow) {
            dialog.show();
        }
        HttpRequest.myWorks(getActivity(), UserUtil.getUserId(getActivity()), "1", p, new ICallback<MyWorksListResult>() {
            @Override
            public void onSucceed(MyWorksListResult result) {
                isFisrstShow = true;
                pull_list.onRefreshComplete();
                List<MyWorks> myWorkses = result.getMessage().getList();
                page = result.getMessage().getPage();
                if (page == 0) {
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(getActivity());
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                } else {

                    if (p <= page) {
                        if (p <= page - 1) {
                            isOver = true;
                        }
                        allCases.addAll(myWorkses);
                    } else {
                        isOver = false;
                        Footools.removeFoot(pull_list, getActivity(), inflate);
                    }

                    if (p == 1) {
                        adapter = new MyWorksAdapter(getActivity(), allCases);
                        pull_list.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFail(String error) {
                pull_list.onRefreshComplete();
                Uihelper.showToast(getActivity(), error);
                isFisrstShow = true;
                dialog.dismiss();
            }
        });

    }

    private void initUI() {

        dialog = ProgressDialogView.create(getActivity());

        inflate = getActivity().getLayoutInflater().inflate(R.layout.footview, null);

        pull_list = (PullToRefreshListView) layout.findViewById(R.id.pull_case);
        pull_list.setOnItemClickListener(this);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_list.setOnScrollListener(this);
        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                p = 1;
                allCases.clear();
                if (adapter != null) {
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
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position <= 0) {
            return;
        }
        MyWorks myWorks = allCases.get(position - 1);
        Intent intent = new Intent(getActivity(), MyWorkDetailsActivity.class);
        intent.putExtra("wordId",myWorks.getWorkid());
        intent.putExtra("state",1);
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
                Footools.addFoot(pull_list, getActivity(), inflate);
            }
            isOver=false;
            netWorkData();
        }
    }
}
