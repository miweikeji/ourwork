package app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.miweikeij.app.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

import app.adapter.GroupMemberAdapter;
import app.entity.GroupGanger;
import app.entity.GroupGangerResult;
import app.entity.GroupMembe;
import app.entity.GroupMemberResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.CircleBitmapDisplayer;
import app.views.MyGridView;
import app.views.NavigationBar;

public class GroupMembersActivity extends BaseActivity implements
        NavigationBar.RightOnClick, AdapterView.OnItemClickListener {


    private int p=1;
    private PullToRefreshScrollView pull_to_grid;
    private MyGridView grid_memeber;
    private GroupMemberAdapter adapter;
    private ArrayList<GroupMembe> AllList = new ArrayList<GroupMembe>();
    private ImageView userImage;
    private TextView age;
    private TextView jobAge;
    private TextView jobType;
    private TextView area;
    private TextView foremanName;
    private String groupid;
    public static DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.test).cacheInMemory(true).cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer()).build();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {
        gangerData();
        netWorkData();
    }

    @Override
    public void initUI() {

        userImage = (ImageView)findViewById(R.id.iv_me_userimage);
        area = (TextView)findViewById(R.id.tv_me_area);
        age = (TextView)findViewById(R.id.tv_me_age);
        jobAge = (TextView)findViewById(R.id.tv_me_jobage);
        jobType = (TextView)findViewById(R.id.tv_me_job);
        foremanName = (TextView)findViewById(R.id.tv_me_name);
        pull_to_grid = (PullToRefreshScrollView)findViewById(R.id.pull_to_grid);
        grid_memeber = (MyGridView)findViewById(R.id.grid_memeber);
        grid_memeber.setOnItemClickListener(this);
        grid_memeber.setFocusable(false);

        pull_to_grid.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_grid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                p = 1;
                AllList.clear();
                netWorkData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                p++;
                netWorkData();
            }
        });

    }

    private void gangerData() {

        HttpRequest.getGroupGangerHttp(this, "106", new ICallback<GroupGangerResult>() {
            @Override
            public void onSucceed(GroupGangerResult result) {
                GroupGanger crafts = result.getCrafts();
                 groupid = crafts.getCra_groupid();
                foremanName.setText(crafts.getName());
                age.setText(""+crafts.getAge()+"岁");
                area.setText(crafts.getCworkhome());
                jobAge.setText(""+crafts.getCworkold());
                jobType.setText(crafts.getProfession());
                imageLoader.displayImage(crafts.getCimg(), userImage, options);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    private void netWorkData() {
        HttpRequest.getGroupCraftsHttp(this, groupid, "1", p, new ICallback<GroupMemberResult>() {
            @Override
            public void onSucceed(GroupMemberResult result) {
                List<GroupMembe> craftsList = result.getCraftsList();
                String page = result.getTotalpage();
                if(p<=Integer.valueOf(page).intValue()){
                    AllList.addAll(craftsList);
                }else {
                    Uihelper.showToast(GroupMembersActivity.this,"没有更多数据了");
                }
                if(p==1){
                    adapter = new GroupMemberAdapter(GroupMembersActivity.this,AllList,imageLoader,options);
                    grid_memeber.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }

                pull_to_grid.onRefreshComplete();
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(GroupMembersActivity.this,error);
                pull_to_grid.onRefreshComplete();
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_group_members;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠班组");
        mBar.setRightTitle("添加");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        Intent intent = new Intent(GroupMembersActivity.this,AddMembersActivity.class);
        intent.putExtra("groupId",groupid);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(GroupMembersActivity.this, CraftsmanZoneActivity.class));

    }
}
