package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import app.adapter.MyFriendsAdapter;
import app.entity.MyFriends;
import app.entity.MyFriendsResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class MyFriendsActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    private MyFriendsAdapter adapter;
    private ListView list;
    private  PullToRefreshListView pull_list;
    private int p=1;
    private List<MyFriends> allList = new ArrayList<MyFriends>();
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
    }

    @Override
    public void initUI() {
        pull_list = (PullToRefreshListView) findViewById(R.id.pull_list);
        list = pull_list.getRefreshableView();
        pull_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_list.setOnItemClickListener(this);
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
//        listView.setAdapter(adapter);

        netWorkData();
    }

    private void netWorkData() {

        HttpRequest.getMyfriend(this, "102", p, new ICallback<MyFriendsResult>() {
            @Override
            public void onSucceed(MyFriendsResult result) {
                List<MyFriends> message = result.getMessage();
                allList.addAll(message);
                if(p==1){
                    adapter = new MyFriendsAdapter(MyFriendsActivity.this, allList,imageLoader,options);
                    list.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                pull_list.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_list.onRefreshComplete();
                Uihelper.showToast(MyFriendsActivity.this,error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_my_friends;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("我的好友");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, CraftsmanZoneActivity.class));
    }
}
