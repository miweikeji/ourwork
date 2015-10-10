package app.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.miweikeij.app.R;

import java.util.ArrayList;

import app.fragment.BespeakingFragment;
import app.fragment.SureBespeakFragment;
import app.views.NavigationBar;
import app.views.PagerSlidingTabStrip;

public class OwnerReservationActivity extends BaseActivity {

    private ArrayList<Fragment> fragments;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        SureBespeakFragment bespeakFragment = new SureBespeakFragment();
        BespeakingFragment bespeakingFragment = new BespeakingFragment();
        fragments = new ArrayList<Fragment>();
        fragments.add(bespeakingFragment);
        fragments.add(bespeakFragment);
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
        private final String[] TITLES = {"预约中","确定预约"};
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
        return R.layout.content_owner_reservation;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("业主预约");
    }
}
