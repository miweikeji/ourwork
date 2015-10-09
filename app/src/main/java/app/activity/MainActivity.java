package app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

        switch (v.getId()){
            case R.id.rl_mine:
                index = 3;
                break;
            case R.id.rl_my_job:
                index = 2;
                break;
            case R.id.rl_carpenter_home:
                index = 1;
                break;
            case R.id.rl_job_opportunity:
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
