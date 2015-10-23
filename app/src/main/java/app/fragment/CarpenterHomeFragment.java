package app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.miweikeij.app.R;

import app.activity.AllWorksActivity;
import app.activity.CraftsmanGroupActivity;
import app.activity.FindFartnerActivity;
import app.activity.MyFriendsActivity;
import app.activity.WorkArrangementActivity;
import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/10/8.
 */
public class CarpenterHomeFragment extends Fragment implements View.OnClickListener {

    private View layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_carpenter_home,null);
        initUI();
        return layout;
    }

    private void initUI() {
        RelativeLayout rl_carftsman_group=(RelativeLayout)layout.findViewById(R.id.rl_carftsman_group);
        RelativeLayout rl_find_craftsman=(RelativeLayout)layout.findViewById(R.id.rl_find_craftsman);
        RelativeLayout rl_my_friends=(RelativeLayout)layout.findViewById(R.id.rl_my_friends);
        RelativeLayout rl_work_arrangement=(RelativeLayout)layout.findViewById(R.id.rl_work_arrangement);
        RelativeLayout rl_all_craftsman=(RelativeLayout) layout.findViewById(R.id.rl_all_craftsman);
        rl_work_arrangement.setOnClickListener(this);
        rl_carftsman_group.setOnClickListener(this);
        rl_my_friends.setOnClickListener(this);
        rl_find_craftsman.setOnClickListener(this);
        rl_all_craftsman.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_carftsman_group:
                startActivity(new Intent(getActivity(), CraftsmanGroupActivity.class));
                break;
            case R.id.rl_find_craftsman:
                startActivity(new Intent(getActivity(), FindFartnerActivity.class));
                break;
            case R.id.rl_my_friends:
//                if (UserUtil.isLogin(getActivity())){
                    startActivity(new Intent(getActivity(), MyFriendsActivity.class));
//                }
                break;
            case R.id.rl_work_arrangement:
                startActivity(new Intent(getActivity(), WorkArrangementActivity.class));
                break;
            case R.id.rl_all_craftsman:
                startActivity(new Intent(getActivity(), AllWorksActivity.class));
                break;
        }
    }
}
