package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

import java.util.ArrayList;

import app.views.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2015/10/8.
 */
public class MyJobFragment extends Fragment {

    private View layout;
    private ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_myjob,null);
        initUI();
        return layout;
    }

    private void initUI() {
        CompletedFragment completedFragment = new CompletedFragment();
        ConstructionFragment constructionFragment = new ConstructionFragment();
        fragments = new ArrayList<Fragment>();
        fragments.add(completedFragment);
        fragments.add(constructionFragment);
       ViewPager pager = (ViewPager) layout.findViewById(R.id.pager);
       PagerSlidingTabStrip pagerSliding =(PagerSlidingTabStrip) layout.findViewById(R.id.pagerSliding);
        pager.setAdapter(new MyAdapter(getChildFragmentManager()));
        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                        .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pagerSliding.setViewPager(pager);
    }

     class MyAdapter extends FragmentPagerAdapter{
         private final String[] TITLES = {"已完成","施工中"};
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
}
