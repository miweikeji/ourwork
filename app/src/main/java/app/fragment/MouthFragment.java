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

import app.activity.MyWorkDetailsActivity;
import app.activity.mywork.adapter.CraftValueAdapter;
import app.adapter.MyWorkAdapter;
import app.entity.Comment;
import app.entity.CommentResult;
import app.entity.MyWork;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/** 工匠评价
 * Created by Administrator on 2015/10/12.
 */
public class MouthFragment extends Fragment implements  AdapterView.OnItemClickListener {

    private int p=1;
    private View layout;
    private PullToRefreshListView pull_list;
    private CraftValueAdapter adapter;
    private List<Comment> allList = new ArrayList<>();
    private int totalpage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mouth, null);
        initUI();
        netWorkData();
        return layout;
    }

    private void netWorkData() {

        HttpRequest.getCommentByCrafts(getActivity(), "147", p, new ICallback<CommentResult>() {
            @Override
            public void onSucceed(CommentResult result) {
                List<Comment> commentList = result.getCommentList();
                totalpage = result.getTotalpage();
                allList.addAll(commentList);
                if (p == 1) {
                    adapter = new CraftValueAdapter(getActivity(), allList);
                    pull_list.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                pull_list.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(), error);
                pull_list.onRefreshComplete();
            }
        });

    }

    private void initUI() {

        pull_list= (PullToRefreshListView) layout.findViewById(R.id.pull_list);
        pull_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_list.setOnItemClickListener(this);
        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                allList.clear();
                p=1;
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(p<totalpage){
                    p++;
                    netWorkData();
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));

    }




}
