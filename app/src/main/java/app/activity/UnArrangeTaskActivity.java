package app.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.adapter.UnArrangeTaskAdapter;
import app.entity.ArrangeTask;
import app.entity.ArrangeTaskResult;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/22.
 */
public class UnArrangeTaskActivity extends BaseActivity{

    private  PullToRefreshScrollView pull_scro;
    private MyListView my_list;
    private UnArrangeTaskAdapter adapter;
    private int p=1;
    private List<ArrangeTask> allList= new ArrayList<ArrangeTask>();
    private ImageLoader instance ;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

        instance = ImageLoader.getInstance();
    }

    @Override
    public void initUI() {

        pull_scro = (PullToRefreshScrollView) findViewById(R.id.pull_scro);
        pull_scro.setMode(PullToRefreshBase.Mode.BOTH);
        my_list = (MyListView) findViewById(R.id.my_list);
        pull_scro.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                p=1;
                allList.clear();
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                p++;
                netWorkData();
            }
        });


        netWorkData();
    }

    private void netWorkData() {

        HttpRequest.unArrangeTask(this, UserInfo.getInstance().getJiang(), p, new ICallback<ArrangeTaskResult>() {
            @Override
            public void onSucceed(ArrangeTaskResult result) {
                List<ArrangeTask> houseList = result.getHouseList();
                allList.addAll(houseList);
                if(p==1){
                    adapter = new UnArrangeTaskAdapter(UnArrangeTaskActivity.this,allList,instance,options);
                    my_list.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }
                pull_scro.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                pull_scro.onRefreshComplete();
                Uihelper.showToast(UnArrangeTaskActivity.this,error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_un_arrange_task;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("分配施工任务");
    }
}
