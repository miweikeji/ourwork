package app.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.util.TypedValue;


import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.fragment.BespeakingFragment;
import app.fragment.SureBespeakFragment;
import app.views.NavigationBar;
import app.views.PagerSlidingTabStrip;

public class OwnerReservationActivity extends BaseActivity implements NavigationBar.RightOnClick {

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
        mBar.setRightTwoTitle("历史预约");
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        startActivity(new Intent(OwnerReservationActivity.this,ReservationHistoryActivity.class));
    }
}
