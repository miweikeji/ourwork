package app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.fragment.ClassMonitorFragment;
import app.fragment.SelectionCraftsmanFragment;
import app.fragment.WorkCompletedFragment;
import app.fragment.WorkConstructionFragment;
import app.views.NavigationBar;
import app.views.PagerSlidingTabStrip;

public class WorkArrangementActivity extends BaseActivity {

    private ArrayList<Fragment> fragments;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        WorkConstructionFragment workConstructionFragment = new WorkConstructionFragment();
        WorkCompletedFragment workCompletedFragment = new WorkCompletedFragment();

        fragments = new ArrayList<Fragment>();
        fragments.add(workConstructionFragment);
        fragments.add(workCompletedFragment);
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        PagerSlidingTabStrip pagerSliding =(PagerSlidingTabStrip)findViewById(R.id.pagerSliding);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                        .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pagerSliding.setViewPager(pager);
    }

    class MyAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {"施工中","已完成"};
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_work_arrangement;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工作安排");
    }
}
