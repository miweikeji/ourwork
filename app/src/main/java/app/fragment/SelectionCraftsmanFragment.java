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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.adapter.ClassMonitorAdapter;
import app.entity.Allcrafts;
import app.entity.craftsListResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/10.
 */
public class SelectionCraftsmanFragment extends Fragment{

    private PullToRefreshListView pull_list;
    private ListView list;
    private ClassMonitorAdapter adapter;
    private List<Allcrafts> allList = new ArrayList<Allcrafts>();
    private int p=1;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_selection_craftsman, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        initUI(layout);
        netWorkData();
        return layout;
    }

    private void netWorkData() {
        HttpRequest.getAllcrafts(getActivity(), "1", p, new ICallback<craftsListResult>() {
            @Override
            public void onSucceed(craftsListResult result) {
                List<Allcrafts> list = result.getCrafts().getList();
                allList.addAll(list);
                if (p == 1) {
                    adapter = new ClassMonitorAdapter(getActivity(), allList, imageLoader, options, 1);
                    pull_list.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(), error);
            }
        });
    }

    private void initUI(View layout) {
        pull_list = (PullToRefreshListView)layout.findViewById(R.id.pull_list);
        list = pull_list.getRefreshableView();
        pull_list.setMode(PullToRefreshBase.Mode.BOTH);
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
}
