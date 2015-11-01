package app.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

import app.views.ProgressDialogView;

/**
 * Created by tlt on 2015/11/1.
 */
public class BaseFrament extends Fragment {

    public Dialog mWaitingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWaitingDialog = ProgressDialogView.create(getActivity());
    }
}
