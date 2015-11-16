package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.adapter.HintAdapter;
import app.adapter.UnArrangeTaskAdapter;
import app.entity.ArrangeTask;
import app.entity.ArrangeTaskResult;
import app.entity.JsonData;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/22.
 */
public class UnArrangeTaskActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private  PullToRefreshScrollView pull_scro;
    private MyListView my_list;
    private UnArrangeTaskAdapter adapter;
    private int p=1;
    private List<ArrangeTask> allList= new ArrayList<ArrangeTask>();
    private ImageLoader instance ;
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

        instance = ImageLoader.getInstance();
    }

    @Override
    public void initUI() {

        pull_scro = (PullToRefreshScrollView) findViewById(R.id.pull_scro);
        pull_scro.setMode(PullToRefreshBase.Mode.BOTH);
        my_list = (MyListView) findViewById(R.id.my_list);
        my_list.setOnItemClickListener(this);
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

        HttpRequest.unArrangeTask(this, "100", p, new ICallback<ArrangeTaskResult>() {
            @Override
            public void onSucceed(ArrangeTaskResult result) {
                List<ArrangeTask> houseList = result.getHouseList();
                page = result.getTotalpage();
                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(UnArrangeTaskActivity.this);
                    my_list.setDividerHeight(0);
                    my_list.setAdapter(hintAdapter);
                } else {
                    allList.addAll(houseList);
                    if(p==1){
                        adapter = new UnArrangeTaskAdapter(UnArrangeTaskActivity.this,allList,instance,options);
                        my_list.setAdapter(adapter);
                    }else {
                        adapter.notifyDataSetChanged();
                    }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(allList!=null&&allList.size()>0){
//            ArrangeTask task = allList.get(position);
//            JsonData jsonData = new JsonData();
//            jsonData.setWorkplace(task.getName());
//            jsonData.setType(task.getType());
//            jsonData.setWho("0");
//            jsonData.setWhoid(UserInfo.getInstance().getId());
//            jsonData.setServertype("0");
//            jsonData.setHouse_id(task.getId());
//
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("NewDecorationActivity", jsonData);
//            Intent intent = new Intent(this, ConstructionTasksActivity.class);
//            intent.putExtra("FROM_ACTIVITY","UnArrangeTaskActivity");
//            intent.putExtras(bundle);
//            startActivity(intent);
        }
    }
}
