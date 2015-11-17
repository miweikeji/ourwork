package app.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import app.activity.mywork.adapter.DailyAdapter;
import app.activity.mywork.adapter.SystemMessageAdapter;
import app.adapter.HintAdapter;
import app.entity.DailyListResult;
import app.entity.DialyData;
import app.entity.Dialylist;
import app.entity.SystemMessage;
import app.entity.SystemMessageResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/11/12.
 */
public class SystemMessageActivity extends BaseActivity {
    private PullToRefreshListView pull_list;
    private boolean isFirstLoaded;
    private DisplayImageOptions options;
    private SystemMessageAdapter adapter;
    private List<SystemMessage> items;
    private String messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        messageId = getIntent().getStringExtra("messageId");
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
        if (!isFirstLoaded) {
            showWaitingDialog();
        }
        HttpRequest.getSystemMessage(mActivity, messageId + "", new ICallback<SystemMessageResult>() {
            @Override
            public void onSucceed(SystemMessageResult result) {
                disMissWaitingDialog();
                isFirstLoaded = true;
                pull_list.onRefreshComplete();
                items = result.getMessage();
                if (items != null && items.size() > 0) {
                    adapter = new SystemMessageAdapter(mActivity, items, imageLoader, options);
                    pull_list.setAdapter(adapter);
                } else {
                    HintAdapter hintAdapter = new HintAdapter(mActivity);
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                }

            }

            @Override
            public void onFail(String error) {
                isFirstLoaded = true;
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);

            }
        });

    }

    @Override
    public void initUI() {

        pull_list = (PullToRefreshListView) findViewById(R.id.listView);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);

        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                items.clear();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                obtainData();
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_system;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("系统消息");


    }
}
