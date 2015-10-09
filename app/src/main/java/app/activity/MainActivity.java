package app.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.fragment.CarpenterHomeFragment;
import app.fragment.JobOpportunityFragment;
import app.fragment.MyJobFragment;
import com.miweikeij.app.R;

import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.views.NavigationBar;
import app.fragment.MineFragment;

public class MainActivity extends FragmentActivity {

    private Fragment[] fragments;
    private MineFragment mineFragment;
    private MyJobFragment jobFragment;
    private CarpenterHomeFragment homeFragment;
    private JobOpportunityFragment opportunityFragment;

    private TextView tv_tabs_mine;
    private TextView tv_tabs_myjob;
    private TextView tv_tabs_home;
    private TextView tv_tabs_job;
    private int index;
    // 当前fragment的index
    private int currentTabIndex;
    private RelativeLayout[] mTabs;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initUI();

            HttpRequest.testHttp(this, new ICallback<Meta>() {
                @Override
                public void onSucceed(Meta result) {

                }

                @Override
                public void onFail(String error) {

                }
            }, "13365047950", "0");
        }
    private void initUI() {
        tv_tabs_mine= (TextView) findViewById(R.id.tv_tabs_mine);
        tv_tabs_myjob = (TextView)findViewById(R.id.tv_tabs_myjob);
        tv_tabs_home = (TextView)findViewById(R.id.tv_tabs_home);
        tv_tabs_job = (TextView)findViewById(R.id.tv_tabs_job);

        mTabs = new RelativeLayout[4];
        mTabs[3] = (RelativeLayout)findViewById(R.id.rl_mine);
        mTabs[2] = (RelativeLayout)findViewById(R.id.rl_my_job);
        mTabs[1] = (RelativeLayout)findViewById(R.id.rl_carpenter_home);
        mTabs[0]= (RelativeLayout)findViewById(R.id.rl_job_opportunity);
        mTabs[0].setSelected(true);

        mineFragment = new MineFragment();
        jobFragment = new MyJobFragment();
        homeFragment = new CarpenterHomeFragment();
        opportunityFragment = new JobOpportunityFragment();
        fragments = new Fragment[]{opportunityFragment,homeFragment,jobFragment,mineFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.contents, opportunityFragment)
                .add(R.id.contents, homeFragment).hide(homeFragment).
                add(R.id.contents, jobFragment).hide(jobFragment).
                add(R.id.contents, mineFragment).hide(mineFragment).
                show(opportunityFragment).commit();


    }


    public void onTabClicked(View v) {
        Drawable tabs_mine_false=getResources().getDrawable(R.mipmap.tabs_mine_false);
        Drawable tabs_myjob_false=getResources().getDrawable(R.mipmap.tabs_myjob_false);
        Drawable tabs_home_false=getResources().getDrawable(R.mipmap.tabs_home_false);
        Drawable tabs_job_false=getResources().getDrawable(R.mipmap.tabs_job_false);
        switch (v.getId()){
            case R.id.rl_mine:

                Drawable tabs_mine=getResources().getDrawable(R.mipmap.tabs_mine_true);
                tabs_mine.setBounds(0, 0, tabs_mine.getMinimumWidth(), tabs_mine.getMinimumHeight());
                tv_tabs_mine.setCompoundDrawables(null, tabs_mine,null, null);
                tv_tabs_mine.setTextColor(getResources().getColor(R.color.base_green));

                tabs_myjob_false.setBounds(0, 0, tabs_myjob_false.getMinimumWidth(), tabs_myjob_false.getMinimumHeight());
                tv_tabs_myjob.setCompoundDrawables(null, tabs_myjob_false,null, null);
                tv_tabs_myjob.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_home_false.setBounds(0, 0, tabs_home_false.getMinimumWidth(), tabs_home_false.getMinimumHeight());
                tv_tabs_home.setCompoundDrawables(null, tabs_home_false,null, null);
                tv_tabs_home.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_job_false.setBounds(0, 0, tabs_job_false.getMinimumWidth(), tabs_job_false.getMinimumHeight());
                tv_tabs_job.setCompoundDrawables(null, tabs_job_false,null, null);
                tv_tabs_job.setTextColor(getResources().getColor(R.color.tabs_tv));
                index = 3;
                break;
            case R.id.rl_my_job:
                tabs_mine_false.setBounds(0, 0, tabs_mine_false.getMinimumWidth(), tabs_mine_false.getMinimumHeight());
                tv_tabs_mine.setCompoundDrawables(null, tabs_mine_false,null, null);
                tv_tabs_mine.setTextColor(getResources().getColor(R.color.tabs_tv));

                Drawable tabs_myjob=getResources().getDrawable(R.mipmap.tabs_myjob_true);
                tabs_myjob.setBounds(0, 0, tabs_myjob.getMinimumWidth(), tabs_myjob.getMinimumHeight());
                tv_tabs_myjob.setCompoundDrawables(null, tabs_myjob,null, null);
                tv_tabs_myjob.setTextColor(getResources().getColor(R.color.base_green));

                tabs_home_false.setBounds(0, 0, tabs_home_false.getMinimumWidth(), tabs_home_false.getMinimumHeight());
                tv_tabs_home.setCompoundDrawables(null, tabs_home_false,null, null);
                tv_tabs_home.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_job_false.setBounds(0, 0, tabs_job_false.getMinimumWidth(), tabs_job_false.getMinimumHeight());
                tv_tabs_job.setCompoundDrawables(null, tabs_job_false,null, null);
                tv_tabs_job.setTextColor(getResources().getColor(R.color.tabs_tv));
                index = 2;
                break;
            case R.id.rl_carpenter_home:
                tabs_mine_false.setBounds(0, 0, tabs_mine_false.getMinimumWidth(), tabs_mine_false.getMinimumHeight());
                tv_tabs_mine.setCompoundDrawables(null, tabs_mine_false,null, null);
                tv_tabs_mine.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_myjob_false.setBounds(0, 0, tabs_myjob_false.getMinimumWidth(), tabs_myjob_false.getMinimumHeight());
                tv_tabs_myjob.setCompoundDrawables(null, tabs_myjob_false,null, null);
                tv_tabs_myjob.setTextColor(getResources().getColor(R.color.tabs_tv));

                Drawable tabs_home=getResources().getDrawable(R.mipmap.tabs_home_true);
                tabs_home.setBounds(0, 0, tabs_home.getMinimumWidth(), tabs_home.getMinimumHeight());
                tv_tabs_home.setCompoundDrawables(null, tabs_home,null, null);
                tv_tabs_home.setTextColor(getResources().getColor(R.color.base_green));

                tabs_job_false.setBounds(0, 0, tabs_job_false.getMinimumWidth(), tabs_job_false.getMinimumHeight());
                tv_tabs_job.setCompoundDrawables(null, tabs_job_false,null, null);
                tv_tabs_job.setTextColor(getResources().getColor(R.color.tabs_tv));
                index = 1;
                break;
            case R.id.rl_job_opportunity:
                tabs_mine_false.setBounds(0, 0, tabs_mine_false.getMinimumWidth(), tabs_mine_false.getMinimumHeight());
                tv_tabs_mine.setCompoundDrawables(null, tabs_mine_false,null, null);
                tv_tabs_mine.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_myjob_false.setBounds(0, 0, tabs_myjob_false.getMinimumWidth(), tabs_myjob_false.getMinimumHeight());
                tv_tabs_myjob.setCompoundDrawables(null, tabs_myjob_false,null, null);
                tv_tabs_myjob.setTextColor(getResources().getColor(R.color.tabs_tv));

                tabs_home_false.setBounds(0, 0, tabs_home_false.getMinimumWidth(), tabs_home_false.getMinimumHeight());
                tv_tabs_home.setCompoundDrawables(null, tabs_home_false,null, null);
                tv_tabs_home.setTextColor(getResources().getColor(R.color.tabs_tv));

                Drawable tabs_job=getResources().getDrawable(R.mipmap.tabs_job_true);
                tabs_job.setBounds(0, 0, tabs_job.getMinimumWidth(), tabs_job.getMinimumHeight());
                tv_tabs_job.setCompoundDrawables(null, tabs_job,null, null);
                tv_tabs_job.setTextColor(getResources().getColor(R.color.base_green));
                index = 0;
                break;



        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.contents, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}
