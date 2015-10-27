package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.List;

import app.activity.MyWorkDetailsActivity;
import app.activity.PublishDairyActivity;
import app.activity.mywork.ValueCraftActivity;
import app.adapter.MyWorkAdapter;
import app.entity.MyWork;
import app.utils.Config;
import app.views.BottomSelectDialog;

/**
 * Created by Administrator on 2015/10/12.
 */
public class DecorationDiaryFragment extends Fragment implements View.OnClickListener{

    private View layout;
    private RecyclerView recyclerView;
    private Dialog bottomSelectDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_decoration_diary,null);
        initUI();
        return layout;
    }

    private void initUI() {

        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview);
                                      layout.findViewById(R.id.btn_dairy).setOnClickListener(this);

    }

//    @Override
//    public void onItemClick(View view, int postion) {
////        startActivity(new Intent(getActivity(), HouseActivity.class));
//        startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));
//    }

    @Override
    public void onClick(View v) {
          if (v.getId()==R.id.btn_dairy){
              //写日记，弹框
            Config.init(getActivity());
             bottomSelectDialog=BottomSelectDialog.create(getActivity(), BottomSelectDialog.PopUpDialogPosition.bottom, new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      switch (v.getId()){
                          case R.id.btn_write:
                              startActivity(new Intent(getActivity(), PublishDairyActivity.class));
                              bottomSelectDialog.dismiss();
                              break;
                          case R.id.btn_check:
                              bottomSelectDialog.dismiss();
                              break;
                          case R.id.btn_value:
                              startActivity(new Intent(getActivity(), ValueCraftActivity.class));
                              bottomSelectDialog.dismiss();
                              break;
                      }

                  }
              });
              bottomSelectDialog.show();

          }

    }
}
