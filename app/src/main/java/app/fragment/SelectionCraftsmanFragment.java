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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.activity.CraftsmanZoneActivity;
import app.adapter.ClassMonitorAdapter;
import app.entity.Allcrafts;
import app.entity.craftsListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/10.
 */
public class SelectionCraftsmanFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private PullToRefreshListView pull_list;
    private ListView list;
    private ClassMonitorAdapter adapter;
    private List<Allcrafts> allList = new ArrayList<Allcrafts>();
    private int p=1;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private Dialog dialog;
    private int page;
    private View inflate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_selection_craftsman, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        dialog = ProgressDialogView.create(getActivity());
        initUI(layout);
        netWorkData();
        return layout;
    }

    private void netWorkData() {
        if(page==1){
            dialog.show();
        }
        HttpRequest.getAllcrafts(getActivity(), "1", p, new ICallback<craftsListResult>() {
            @Override
            public void onSucceed(craftsListResult result) {

                List<Allcrafts> list = result.getCrafts().getList();
                 page = result.getCrafts().getPage();
                if(p<=page){
                    if(p<=page-1){
                        isOver = true;
                    }
                    allList.addAll(list);
                }else {
                    isOver = false;
                    Footools.removeFoot(pull_list,getActivity(),inflate);
                }
                if (p == 1) {
                    adapter = new ClassMonitorAdapter(getActivity(), allList, imageLoader, options, 1);
                    pull_list.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
                pull_list.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(), error);
                dialog.dismiss();
                pull_list.onRefreshComplete();
            }
        });
    }

    private void initUI(View layout) {

        inflate = getActivity().getLayoutInflater().inflate(R.layout.footview, null);

        pull_list = (PullToRefreshListView)layout.findViewById(R.id.pull_list);
        list = pull_list.getRefreshableView();
        pull_list.setOnItemClickListener(this);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_list.setOnScrollListener(this);

        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                p = 1;
                allList.clear();
                netWorkData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                p++;
                netWorkData();
            }
        });

    }

    private boolean isOver;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(), CraftsmanZoneActivity.class));
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if(visibleItemCount+firstVisibleItem>=totalItemCount&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_list,getActivity(),inflate);
            }
            isOver=false;
            netWorkData();
        }
    }
}
