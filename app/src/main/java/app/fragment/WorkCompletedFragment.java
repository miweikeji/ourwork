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
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.WorkPlanDetailsActivity;
import app.adapter.HintAdapter;
import app.adapter.MyWorkAdapter;
import app.entity.ConstructPlan;
import app.entity.ConstructPlanResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/11.
 */
public class WorkCompletedFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private int p=1;
    private PullToRefreshListView pull_case;
    private ArrayList<ConstructPlan> allCases = new ArrayList<ConstructPlan>();
    private MyWorkAdapter adapter;

    private Dialog dialog;
    private int page;
    private View inflate;
    private boolean isOver;
    private boolean isFisrstShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_work_completed, null);

        dialog = ProgressDialogView.create(getActivity());

        initUI(layout);
        netWorkData();
        return layout;
    }
    private void netWorkData() {
        if(!isFisrstShow){
            dialog.show();
        }
        HttpRequest.finishPlan(getActivity(), "100", p, new ICallback<ConstructPlanResult>() {
            @Override
            public void onSucceed(ConstructPlanResult result) {
                List<ConstructPlan> cases = result.getHouseList();

                page = result.getPage();

                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(getActivity());
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
                        Footools.removeFoot(pull_case, getActivity(), inflate);
                    }

                    if (p == 1) {
                        adapter = new MyWorkAdapter(getActivity(), allCases);
                        pull_case.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }


                pull_case.onRefreshComplete();
                dialog.dismiss();
                isFisrstShow=true;
            }

            @Override
            public void onFail(String error) {
                pull_case.onRefreshComplete();
//                Uihelper.showToast(getActivity(), error);
                dialog.dismiss();
                isFisrstShow=true;
            }
        });

    }

    private void initUI(View layout) {
        inflate = getActivity().getLayoutInflater().inflate(R.layout.footview, null);
        pull_case =(PullToRefreshListView)layout.findViewById(R.id.pull_case);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(visibleItemCount+firstVisibleItem>=totalItemCount- Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_case, getActivity(), inflate);
            }
            isOver=false;
            netWorkData();
        }
    }
}
