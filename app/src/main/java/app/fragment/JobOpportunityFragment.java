package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;

import app.tools.ScreenUtil;

/**
 * Created by Administrator on 2015/10/8.
 */
public class JobOpportunityFragment extends Fragment{

    private View layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_job_opportunity,null);
        initUI();
        return layout;
    }

    private void initUI() {
        int px = ScreenUtil.instance(getActivity()).dip2px(8);
        int width = ScreenUtil.instance(getActivity()).getScreenWidth();
        RelativeLayout rl_hight_one=(RelativeLayout)layout.findViewById(R.id.rl_hight_one);
        ViewGroup.LayoutParams params_one =rl_hight_one.getLayoutParams();
        params_one.height = (width-px)/2;
        rl_hight_one.setLayoutParams(params_one);
        RelativeLayout rl_hight_two=(RelativeLayout)layout.findViewById(R.id.rl_hight_two);
        ViewGroup.LayoutParams params_two =rl_hight_two.getLayoutParams();
        params_two.height = (width-px)/2;
        rl_hight_two.setLayoutParams(params_two);
    }


}
