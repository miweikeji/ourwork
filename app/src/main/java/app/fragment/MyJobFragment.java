package app.fragment;

import android.content.Intent;
import android.graphics.Paint;
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
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.ArrayList;

import app.activity.LoginActivity;
import app.activity.mywork.ParterMessageActivity;
import app.entity.UserInfo;
import app.utils.ExtendOperationController;
import app.utils.UserUtil;
import app.views.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2015/10/8.
 */
public class MyJobFragment extends Fragment implements View.OnClickListener, ExtendOperationController.ExtendOperationListener {

    private View layout;
    private ArrayList<Fragment> fragments;
    private TextView tvNum;
    private ViewPager pager;
    private PagerSlidingTabStrip pagerSliding;
    private ExtendOperationController extendOperationController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_myjob, null);
        initUI();
        extendOperationController = ExtendOperationController.getInstance();
        extendOperationController.registerExtendOperationListener(this);
        return layout;
    }

    @Override
    public void onDestroy() {
        extendOperationController.unRegisterExtendOperationListener(this);
        super.onDestroy();
    }

    @Override
    public void onStart() {

        if (UserUtil.hasLogin(getActivity())) {
            layout.findViewById(R.id.frame_logined).setVisibility(View.VISIBLE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.GONE);
            pager.setAdapter(new MyAdapter(getChildFragmentManager()));
            final int pageMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                            .getDisplayMetrics());
            pager.setPageMargin(pageMargin);
            pagerSliding.setViewPager(pager);

        } else {
            layout.findViewById(R.id.frame_logined).setVisibility(View.GONE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.VISIBLE);
            TextView tvLogin = (TextView) layout.findViewById(R.id.tv_login);
            tvLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
            tvLogin.setOnClickListener(this);
        }
        super.onStart();
    }

    private void initUI() {
        CompletedFragment completedFragment = new CompletedFragment();
        ConstructionFragment constructionFragment = new ConstructionFragment();
        fragments = new ArrayList<Fragment>();
        fragments.add(constructionFragment);
        fragments.add(completedFragment);
        pager = (ViewPager) layout.findViewById(R.id.pager);
        pagerSliding = (PagerSlidingTabStrip) layout.findViewById(R.id.pagerSliding);

        //消息
        tvNum = (TextView) layout.findViewById(R.id.tv_message_num);

        layout.findViewById(R.id.frame_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到消息页面
                if (UserUtil.isLogin(getActivity())) {
                    startActivity(new Intent(getActivity(), ParterMessageActivity.class));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                LoginActivity.enterActivity(getActivity());
                break;
        }

    }

    @Override
    public void excuteExtendOperation(int operationKey, Object data) {

        if (operationKey == ExtendOperationController.OperationKey.EXIT_MYJOB) {
            onStart();
        }

    }

    class MyAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {"施工中", "已完成"};

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
