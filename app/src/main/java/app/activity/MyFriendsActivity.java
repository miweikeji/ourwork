package app.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.miweikeij.app.R;


import app.adapter.MyFriendsAdapter;
import app.views.AutoLoadListView;
import app.views.NavigationBar;

public class MyFriendsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, AutoLoadListView.AutoLoadingListener {


    private int count = 9;
    private MyFriendsAdapter adapter;
    private SwipeRefreshLayout srl_refresh;
    private ListView listView;
    private Handler handler = new Handler();

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        srl_refresh = (SwipeRefreshLayout) findViewById(R.id.srl_refresh);
        listView = (ListView) findViewById(R.id.listView);
        srl_refresh.setOnRefreshListener(this);
        adapter = new MyFriendsAdapter(this, count);
        listView.setAdapter(adapter);
//        listView.setAutoLoadListener(this);

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
    public void onRefresh() {
        srl_refresh.setRefreshing(false);

        adapter.notifyDataSetChanged();
    }


    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                count += 6;
                adapter.notifyDataSetChanged();
//                listView.setIsOver(true);
            }
        }, 4000);
    }
}
