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
import app.activity.mywork.adapter.CraftValueAdapter;
import app.adapter.ClassMonitorAdapter;
import app.adapter.HintAdapter;
import app.entity.Allcrafts;
import app.entity.Comment;
import app.entity.CommentResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.ProgressDialogView;

/** 工匠评价
 * Created by Administrator on 2015/10/12.
 */
public class MouthFragment extends Fragment implements  AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private int p=1;
    private View layout;
    private PullToRefreshListView pull_list;
    private CraftValueAdapter adapter;
    private List<Comment> allList = new ArrayList<>();
    private int page;
    private int craftId;
    private View inflate;
    private boolean isOver;
    private boolean isFisrstShow;
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mouth, null);
        initUI();
        netWorkData();
        return layout;
    }

    public void setWorkId(int craftId) {
        this.craftId=craftId;
    }

    private void netWorkData() {
        if(!isFisrstShow){
            dialog.show();
        }
        HttpRequest.getCommentByCrafts(getActivity(), craftId+"", p, new ICallback<CommentResult>() {
            @Override
            public void onSucceed(CommentResult result) {
                isFisrstShow = true;
                pull_list.onRefreshComplete();
                List<Comment> list = result.getCommentList();
                page = result.getTotalpage();
                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(getActivity());
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                } else {

                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                        allList.addAll(list);
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_list, getActivity(), inflate);
                    }

                    if(p==1){
                        adapter = new CraftValueAdapter(getActivity(), allList);
                        pull_list.setAdapter(adapter);
                    }else {
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

        pull_list= (PullToRefreshListView) layout.findViewById(R.id.pull_list);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_list.setOnItemClickListener(this);
        pull_list.setOnScrollListener(this);
        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                p=1;
                allList.clear();
                if (adapter!=null){
                    adapter.notifyDataSetChanged();
                }
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


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
