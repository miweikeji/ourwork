package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.fragment.BaseInfoFragment;
import app.fragment.DecorationDiaryFragment;
import app.fragment.WorkCompletedFragment;
import app.fragment.WorkConstructionFragment;
import app.views.NavigationBar;
import app.views.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2015/10/12.
 */
public class HouseActivity extends BaseActivity{

    private ArrayList<Fragment> fragments;
    private int houseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        houseId=getIntent().getIntExtra("houseId",0);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        BaseInfoFragment baseInfoFragment = new BaseInfoFragment();
        baseInfoFragment.setHouseId(houseId);
        DecorationDiaryFragment diaryFragment = new DecorationDiaryFragment();
        diaryFragment.setHouseId(houseId);

        fragments = new ArrayList<Fragment>();
        fragments.add(baseInfoFragment);
        fragments.add(diaryFragment);
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
        private final String[] TITLES = {"基本信息","装修日记"};
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
        return R.layout.activity_house;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("清晰小屋");
    }

    public static  void enterActivity(Activity activity,int houseId){
        Intent intent =new Intent(activity,HouseActivity.class);
        intent.putExtra("houseId", houseId);
        activity.startActivity(intent);
    }
}
