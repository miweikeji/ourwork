package app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.activity.LoginActivity;
import app.activity.user.AboutUsActivity;
import app.activity.user.FeekBackActivity;
import app.activity.user.IntegralActivity;
import app.activity.user.JobAuthentActivity;
import app.activity.user.ProtectMoneyActivity;
import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/10/2.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView tvJobage;
    private TextView tvAge;
    private TextView tvArea;
    private TextView tvJob;
    private TextView tvName;
    private TextView tvPosition;
    private ImageView ivUserImage;
    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mine, null);
        findView(layout);
        return layout;
    }

    @Override
    public void onStart() {

        if (UserUtil.hasLogin(getActivity())){
            layout.findViewById(R.id.frame_logined).setVisibility(View.VISIBLE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.GONE);
        }else {
            layout.findViewById(R.id.frame_logined).setVisibility(View.GONE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.VISIBLE);
        }
        super.onStart();
    }

    private void findView(View layout) {


        tvAge = (TextView) layout.findViewById(R.id.tv_me_age);
        tvArea = (TextView) layout.findViewById(R.id.tv_me_area);
        tvJob = (TextView) layout.findViewById(R.id.tv_me_job);
        tvJobage = (TextView) layout.findViewById(R.id.tv_me_jobage);
        tvName = (TextView) layout.findViewById(R.id.tv_me_name);
        tvPosition = (TextView) layout.findViewById(R.id.tv_me_position);

        ivUserImage = (ImageView) layout.findViewById(R.id.iv_me_userimage);
        layout.findViewById(R.id.btn_exit).setOnClickListener(this);

        layout.findViewById(R.id.frame_me_about).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_authencation).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_integral).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_protect).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_suggestion).setOnClickListener(this);

        layout.findViewById(R.id.tv_sign).setOnClickListener(this);
        layout.findViewById(R.id.tv_login).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //关于
            case R.id.frame_me_about:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            //工人认证
            case R.id.frame_me_authencation:
                startActivity(new Intent(getActivity(), JobAuthentActivity.class));
                break;
            //我的积分
            case R.id.frame_me_integral:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            //保证金
            case R.id.frame_me_protect:
                startActivity(new Intent(getActivity(), ProtectMoneyActivity.class));
                break;
            //签到
            case R.id.tv_sign:

                break;
            //意见反馈
            case R.id.frame_me_suggestion:
                startActivity(new Intent(getActivity(), FeekBackActivity.class));

                break;
                //退出
            case R.id.btn_exit:

                UserUtil.clearUserInfo(getActivity());
                onStart();

                break;
            case R.id.tv_login:

                LoginActivity.enterActivity(getActivity());

                break;
            default:
                break;
        }

    }
}
