package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.adapter.ClassMonitorAdapter;
import app.entity.Allcrafts;
import app.entity.SearchResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.UIEventUpdate;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/14.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private PullToRefreshListView pull_list;
    private ListView list;
    private ClassMonitorAdapter adapter;
    private List<Allcrafts> allList = new ArrayList<Allcrafts>();
    private int p=1;
    private ImageLoader imageLoader;
    private EditText et_search_contents;
    private String from_Activity;
    private String contents;
    private int view_id;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
        imageLoader = ImageLoader.getInstance();
        Intent intent = getIntent();
        from_Activity = intent.getStringExtra("FROM_ConstructionTasksActivity");
        if("ConstructionTasksActivity".equals(from_Activity)){
            contents = intent.getStringExtra("CASE_TYPE");
            view_id = intent.getIntExtra("VIEW_ID", 0);
            netWorkData();
        }else if("ChangeTasksActivity".equals(from_Activity)){
            contents = intent.getStringExtra("CASE_TYPE");
            view_id = intent.getIntExtra("VIEW_ID", 0);
            netWorkData();
        }
    }

    @Override
    public void initUI() {

        et_search_contents = (EditText) findViewById(R.id.et_search_contents);
        RelativeLayout rl_find= (RelativeLayout) findViewById(R.id.rl_find);
        TextView search = (TextView)findViewById(R.id.tv_search);
        search.setOnClickListener(this);
        rl_find.setOnClickListener(this);
        pull_list = (PullToRefreshListView)findViewById(R.id.pull_list);
        list = pull_list.getRefreshableView();
        pull_list.setMode(PullToRefreshBase.Mode.BOTH);
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

    }
    private void netWorkData() {
        HttpRequest.searchcraftsByType(SearchActivity.this, contents, p, new ICallback<SearchResult>() {
            @Override
            public void onSucceed(SearchResult result) {
                pull_list.onRefreshComplete();
                List<Allcrafts> list = result.getCrafts();
                allList.addAll(list);
                if (p == 1) {
                    adapter = new ClassMonitorAdapter(SearchActivity.this, allList, imageLoader, options, 0);
                    pull_list.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String error) {
                pull_list.onRefreshComplete();
                Uihelper.showToast(SearchActivity.this, error);
            }
        });
    }
    @Override
    public int onCreateMyView() {
        return R.layout.activity_search;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setBarInVisibile();
    }

    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:

                break;
            case R.id.rl_find:
                contents =et_search_contents.getText().toString().trim();
                netWorkData();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Allcrafts allcrafts = allList.get(position-1);
        if("ConstructionTasksActivity".equals(from_Activity)){
            String craftsName;
            String name = allcrafts.getName();
            if(name!=null&&!"".equals(name)){
                craftsName=name;
            }else {
                craftsName="  ";
            }
            UIEventUpdate.getInstance().notifyUIUpdate(1,contents+"#"+allcrafts.getId()+"#"+allcrafts.getName()+"#"+view_id);
            finish();
        }else if("ChangeTasksActivity".equals(from_Activity)){
            UIEventUpdate.getInstance().notifyUIUpdate(3,contents+"#"+allcrafts.getId()+"#"+allcrafts.getName()+"#"+view_id);
            finish();
        }
    }
}
