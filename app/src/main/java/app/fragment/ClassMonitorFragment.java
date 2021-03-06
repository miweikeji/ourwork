package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.activity.CraftsmanZoneActivity;
import app.adapter.ClassMonitorAdapter;
import app.adapter.HintAdapter;
import app.entity.Allcrafts;
import app.entity.craftsListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.ProgressDialogView;

/**
 * Created by Administrator on 2015/10/10.
 */
public class ClassMonitorFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

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
    private boolean isOver;
    private boolean isFisrstShow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_class_monitor, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        dialog = ProgressDialogView.create(getActivity());
        initUI(layout);
        netWorkData();
        return layout;
    }

    private void netWorkData() {
        if(!isFisrstShow){
            dialog.show();
        }
        HttpRequest.getAllcrafts(getActivity(), "0", p, new ICallback<craftsListResult>() {
            @Override
            public void onSucceed(craftsListResult result) {
                isFisrstShow = true;
                pull_list.onRefreshComplete();
                List<Allcrafts> list = result.getCrafts().getList();
                 page = result.getCrafts().getPage();
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
                        adapter = new ClassMonitorAdapter(getActivity(),allList,imageLoader,options,0);
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

    private void initUI(View layout) {
        inflate = getActivity().getLayoutInflater().inflate(R.layout.footview, null);
        pull_list = (PullToRefreshListView)layout.findViewById(R.id.pull_list);
        pull_list.setOnItemClickListener(this);
        list = pull_list.getRefreshableView();
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_list.setOnScrollListener(this);
        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                p=1;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position <= 0) {
            return;
        }
        Allcrafts allcrafts = allList.get(position - 1);
        String  cid= allcrafts.getId();
        if (!TextUtils.isEmpty(cid)){
            CraftsmanZoneActivity.enterActivity(getActivity(),Integer.parseInt(cid));
        }
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
