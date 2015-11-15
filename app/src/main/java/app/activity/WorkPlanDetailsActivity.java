package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.miwei.jzj_system.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import app.adapter.WorkPlanDetailsAdapter;
import app.entity.ConstructPlan;
import app.entity.DetailPlan;
import app.entity.DetailPlanResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.StatusTools;
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/27.
 */
public class WorkPlanDetailsActivity extends BaseActivity implements View.OnClickListener {

    private int p=1;
    private ConstructPlan constructPlan;
    private MyListView listView;
    private WorkPlanDetailsAdapter adapter;
    private PullToRefreshScrollView scrollView;
    private ArrayList<DetailPlan> allCases = new ArrayList<DetailPlan>();
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
        Intent intent = getIntent();
        constructPlan = (ConstructPlan) intent.getSerializableExtra("ConstructPlan");

        RelativeLayout rl_to_house = (RelativeLayout) findViewById(R.id.rl_to_house);
        rl_to_house.setOnClickListener(this);
        TextView tv_name= (TextView)findViewById(R.id.tv_name);
        TextView tv_status= (TextView)findViewById(R.id.tv_status);
        TextView tv_type= (TextView)findViewById(R.id.tv_type);
        TextView tv_area= (TextView)findViewById(R.id.tv_area);
        TextView tv_style= (TextView)findViewById(R.id.tv_style);
        TextView tv_mode= (TextView)findViewById(R.id.tv_mode);
        TextView tv_total_price= (TextView)findViewById(R.id.tv_total_price);

        tv_name.setText(constructPlan.getHouse_name());
        tv_type.setText(" "+constructPlan.getHouse_type()+" |");
        tv_area.setText(" "+constructPlan.getHouse_area()+"平 |");
        tv_style.setText(" "+constructPlan.getHouse_style()+" |");
        tv_mode.setText(" "+constructPlan.getHouse_craft_mode());
        tv_total_price.setText("￥"+constructPlan.getHouse_total_price());
        StatusTools.setStatus(tv_status, constructPlan.getHouse_status());

        scrollView = (PullToRefreshScrollView) findViewById(R.id.pull_scroll);
        listView = (MyListView) findViewById(R.id.listViewq);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

        netWorkData();
    }
    private void netWorkData() {

        HttpRequest.detailPlan(this, "100",constructPlan.getHouse_id(), p, new ICallback<DetailPlanResult>() {
            @Override
            public void onSucceed(DetailPlanResult result) {
                List<DetailPlan> list = result.getList();
                allCases.addAll(list);
                if (p == 1) {
                    adapter = new WorkPlanDetailsAdapter(WorkPlanDetailsActivity.this, allCases,options);
                    listView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                scrollView.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                scrollView.onRefreshComplete();
                Uihelper.showToast(WorkPlanDetailsActivity.this, error);
            }
        });

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_work_plan_detials;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("安排详情");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_to_house:
                startActivity(new Intent(this,HouseActivity.class));
                break;
        }
    }

}
