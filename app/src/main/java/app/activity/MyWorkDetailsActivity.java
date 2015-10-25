package app.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.adapter.MyWorkAdapter;
import app.entity.MyWork;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/12.
 */
public class MyWorkDetailsActivity extends BaseActivity implements NavigationBar.RightOnClick , MyWorkAdapter.MyItemClickListener{


    private LinearLayout linearDuty;
    private RecyclerView recyclerView;

    @Override
    public void obtainData() {
           View  item=mActivity.getLayoutInflater().inflate(R.layout.item_person_shigongrenwu,null);
            linearDuty.addView(item);

    }

    @Override
    public void initUI() {
        //工作任务
        linearDuty=(LinearLayout)findViewById(R.id.linear_shigongrenwu);
        linearDuty.setOrientation(LinearLayout.VERTICAL);

    }
    //查看施工项目
    public void toProdDetails(View view){
        startActivity(new Intent(this,HouseActivity.class));
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_my_work_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作详情");
        mBar.setRightTwoTitle("看他主页");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        startActivity(new Intent(MyWorkDetailsActivity.this,CraftsmanZoneActivity.class));
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
