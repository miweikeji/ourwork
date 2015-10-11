package app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.miweikeij.app.R;

/**
 * Created by Administrator on 2015/10/10.
 */
public class LoginJobFragment extends Fragment implements View.OnClickListener {

    private View layout;
    LoginJobFragmentDelegate delegate;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                delegate.toJobOpportunityFragment();
                break;
        }
    }

    public interface LoginJobFragmentDelegate {
        public void toJobOpportunityFragment();
    }
    public void setLoginJobFragmentDelegate(LoginJobFragmentDelegate delegate){
        this.delegate = delegate;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_login_job,null);
        initUI();
        return layout;
    }

    private void initUI() {
       Button button =(Button) layout.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void setCraftsmanType(String type) {
    }
}
