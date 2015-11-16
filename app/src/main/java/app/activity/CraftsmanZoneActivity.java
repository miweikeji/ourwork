package app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.entity.Crafts;
import app.entity.CraftsResult;
import app.entity.GroupInfo;
import app.fragment.CaseFragment;
import app.fragment.CraftsmanInfoFragment;
import app.fragment.MouthFragment;

import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;
import app.views.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2015/10/12.
 */
public class CraftsmanZoneActivity extends BaseActivity {
    private ArrayList<Fragment> fragments;
    private int craftId;
    private String telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        craftId = intent.getIntExtra("craftId", 0);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

        HttpRequest.craftsmanInfoHttp(mActivity, craftId + "", new ICallback<CraftsResult>() {
            @Override
            public void onSucceed(CraftsResult result) {
                Crafts crafts = result.getCrafts();
                telephone = crafts.getCworkmobile();
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(mActivity, error);
            }
        });

    }

    @Override
    public void initUI() {
        CraftsmanInfoFragment infoFragment = new CraftsmanInfoFragment();
        infoFragment.setWorkId(craftId);
        CaseFragment caseFragment = new CaseFragment();
        caseFragment.setWorkId(craftId);
        MouthFragment mouthFragment = new MouthFragment();
        mouthFragment.setWorkId(craftId);

        fragments = new ArrayList<Fragment>();
        fragments.add(infoFragment);
        fragments.add(caseFragment);
        fragments.add(mouthFragment);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerSlidingTabStrip pagerSliding = (PagerSlidingTabStrip) findViewById(R.id.pagerSliding);
        pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                        .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pagerSliding.setViewPager(pager);
    }

    class MyAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {"工匠信息", "装修案例", "口碑评价"};

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
        return R.layout.activity_craftsman_zone;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工匠主页");
    }

    public void btn_callphone(View v) {
        if (!TextUtils.isEmpty(telephone)) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone)));
        }
    }

    public static void enterActivity(Activity activity, int craftId) {
        Intent intent = new Intent(activity, CraftsmanZoneActivity.class);
        intent.putExtra("craftId", craftId);
        activity.startActivity(intent);
    }
}
