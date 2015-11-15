package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import app.adapter.HintAdapter;
import app.adapter.MyFriendsAdapter;
import app.entity.MyFriends;
import app.entity.MyFriendsResult;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.Uihelper;
import app.views.NavigationBar;

public class MyFriendsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {


    private MyFriendsAdapter adapter;
    private ListView list;
    private PullToRefreshListView pull_list;
    private int p = 1;
    private List<MyFriends> allList = new ArrayList<MyFriends>();
    private DisplayImageOptions options;

    private int page;
    private View inflate;
    private boolean isOver;
    private boolean isFisrstShow;

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
        inflate = getLayoutInflater().inflate(R.layout.footview, null);
        pull_list = (PullToRefreshListView) findViewById(R.id.pull_list);
        list = pull_list.getRefreshableView();
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        pull_list.setOnScrollListener(this);
        pull_list.setOnItemClickListener(this);
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
//        listView.setAdapter(adapter);

        netWorkData();
    }

    private void netWorkData() {
        if (!isFisrstShow) {
            showWaitingDialog();
        }
        HttpRequest.getMyfriend(this, UserInfo.getInstance().getId(), p, new ICallback<MyFriendsResult>() {
            @Override
            public void onSucceed(MyFriendsResult result) {
                isFisrstShow = true;
                List<MyFriends> message = result.getMessage();
                page = result.getPage();
                if (page == 0) {
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(MyFriendsActivity.this);
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                } else {

                    if (p <= page) {
                        if (p <= page - 1) {
                            isOver = true;
                        }
                        allList.addAll(message);
                    } else {
                        isOver = false;
                        Footools.removeFoot(pull_list, MyFriendsActivity.this, inflate);
                    }

                    if (p == 1) {
                        adapter = new MyFriendsAdapter(MyFriendsActivity.this, allList, imageLoader, options);
                        pull_list.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                }
                pull_list.onRefreshComplete();
                disMissWaitingDialog();
            }

            @Override
            public void onFail(String error) {
                pull_list.onRefreshComplete();
                Uihelper.showToast(MyFriendsActivity.this, error);
                isFisrstShow = true;
                disMissWaitingDialog();
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

        if (position <= 0) {
            return;
        }

        MyFriends myFriends = allList.get(position - 1);
        String cid = myFriends.getCid();
        if (!TextUtils.isEmpty(cid)) {
            CraftsmanZoneActivity.enterActivity(mActivity, Integer.parseInt(cid));
        }

        startActivity(new Intent(this, CraftsmanZoneActivity.class));
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (visibleItemCount + firstVisibleItem >= totalItemCount - Config.NUMBER && isOver) {
            p++;
            if (page > 1 && p != page) {
                Footools.addFoot(pull_list, this, inflate);
            }
            isOver = false;
            netWorkData();
        }
    }
}
