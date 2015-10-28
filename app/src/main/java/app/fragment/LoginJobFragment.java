package app.fragment;

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

import app.activity.LoginActivity;
import app.activity.WorkDetailsActivity;
import app.adapter.JobAdapter;
import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/10/10.
 */
public class LoginJobFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View layout;
    LoginJobFragmentDelegate delegate;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_login_job, null);
        profession = UserUtil.getUserProfession(getActivity());
        initUI();
        return layout;
    }

    private void initUI() {
        tv_profession = (TextView) layout.findViewById(R.id.tv_profession);
        tv_profession.setText(profession);
        LinearLayout button =(LinearLayout) layout.findViewById(R.id.line_two_text);
        button.setOnClickListener(this);
        PullToRefreshListView pull_to_list = (PullToRefreshListView) layout.findViewById(R.id.pull_to_list);
        JobAdapter adapter = new JobAdapter(getActivity());
        pull_to_list.setAdapter(adapter);
        pull_to_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_list.setOnItemClickListener(this);
        pull_to_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    public void setCraftsmanType(String type) {
        tv_profession.setText(type);
    }
}
