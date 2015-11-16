package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miwei.jzj_system.R;

import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/10/10.
 */
public class JobContentsFragment extends Fragment implements
        JobOpportunityFragment.JobOpportunityFragmentDelegate, LoginJobFragment.LoginJobFragmentDelegate {

    private JobOpportunityFragment opportunityFragment;
    private LoginJobFragment loginJobFragment;
    private String profession;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_job_contents,null);
        initUI();

        return layout;
    }



    private void initUI() {
        opportunityFragment = new JobOpportunityFragment();
        loginJobFragment = new LoginJobFragment();
        android.support.v4.app.FragmentManager manager = getChildFragmentManager();
        if(profession!=null&&!"".equals(profession)){
            manager.beginTransaction().add(R.id.childContents,loginJobFragment).hide(loginJobFragment).add(R.id.childContents,opportunityFragment)
                    .hide(opportunityFragment).show(loginJobFragment).commit();
        }else {
            manager.beginTransaction().add(R.id.childContents,opportunityFragment).hide(opportunityFragment).add(R.id.childContents,loginJobFragment)
                    .hide(loginJobFragment).show(opportunityFragment).commit();
        }
        opportunityFragment.setJobOpportunityFragmentDelegate(this);
        loginJobFragment.setLoginJobFragmentDelegate(this);
    }

    @Override
    public void getCraftsmanType(String type) {

        if (UserUtil.isLogin(getActivity())) {
            getChildFragmentManager().beginTransaction().hide(opportunityFragment).show(loginJobFragment).commit();
            loginJobFragment.setCraftsmanType(type);
        }

    }

    @Override
    public void toJobOpportunityFragment() {
        getChildFragmentManager().beginTransaction().hide(loginJobFragment).show(opportunityFragment).commit();
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
