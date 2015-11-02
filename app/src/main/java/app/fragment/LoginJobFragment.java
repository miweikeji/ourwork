package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.LoginActivity;
import app.activity.WorkDetailsActivity;
import app.adapter.JobAdapter;
import app.entity.HouseInfoResult;
import app.entity.UserInfo;
import app.entity.WorkList;
import app.entity.WorkListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.StatusTools;
import app.utils.Constants;
import app.utils.Uihelper;
import app.utils.UserUtil;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/10.
 */
public class LoginJobFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View layout;
    LoginJobFragmentDelegate delegate;
    private int p=1;
    private int workType;
    private PullToRefreshListView pull_to_list;
    private JobAdapter adapter;
    private List<WorkList> allList = new ArrayList<WorkList>();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_two_text:
                delegate.toJobOpportunityFragment();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getActivity(), WorkDetailsActivity.class));
    }

    public interface LoginJobFragmentDelegate {
        public void toJobOpportunityFragment();
    }
    public void setLoginJobFragmentDelegate(LoginJobFragmentDelegate delegate){
        this.delegate = delegate;
    }

    private  String profession;
    private TextView tv_profession;
    private Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_login_job, null);
        profession = UserUtil.getUserProfession(getActivity());
        String profession = UserInfo.getInstance().getProfession();
        workType = StatusTools.getWorkType(profession);
        dialog = ProgressDialogView.create(getActivity());
        initUI();
        return layout;
    }

    private void initUI() {
        tv_profession = (TextView) layout.findViewById(R.id.tv_profession);
        tv_profession.setText(profession);
        LinearLayout button =(LinearLayout) layout.findViewById(R.id.line_two_text);
        button.setOnClickListener(this);
        pull_to_list = (PullToRefreshListView) layout.findViewById(R.id.pull_to_list);

        pull_to_list.setAdapter(adapter);
        pull_to_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_list.setOnItemClickListener(this);
        pull_to_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                netWorkData();
            }
        });


        netWorkData();
    }

    private void netWorkData() {
        dialog.show();//UserInfo.getInstance().getId()//workType
        HttpRequest.getWorkList(getActivity(), "101", p, workType, new ICallback<WorkListResult>() {
            @Override
            public void onSucceed(WorkListResult result) {
                List<WorkList> workList = result.getWorkList();
                allList.addAll(workList);
                if(p==1){
                    adapter = new JobAdapter(getActivity(),allList);
                    pull_to_list.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                pull_to_list.onRefreshComplete();
                dialog.dismiss();
            }

            @Override
            public void onFail(String error) {
                pull_to_list.onRefreshComplete();
                if(!error.equals(Constants.JSON_HAS_NULL)){
                    Uihelper.showToast(getActivity(),error);
                }
                dialog.dismiss();
            }
        });
    }

    public void setCraftsmanType(String type) {
        tv_profession.setText(type);
        workType = StatusTools.getWorkType(type);
        allList.clear();
        netWorkData();
    }
}
