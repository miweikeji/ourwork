package app.fragment;

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
import app.activity.mywork.adapter.CraftValueAdapter;
import app.adapter.MyWorkAdapter;
import app.entity.MyWork;

/** 工匠评价
 * Created by Administrator on 2015/10/12.
 */
public class MouthFragment extends Fragment implements CraftValueAdapter.MyItemClickListener{

    private View layout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mouth, null);
        initUI();
        return layout;
    }private void initUI() {

        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //设置间隔
        List<MyWork> dataList = new ArrayList<>();
        int i;
        for (i = 0; i < 10; i++) {
            MyWork myWork = new MyWork();
            dataList.add(myWork);
        }

        CraftValueAdapter craftValueAdapter = new CraftValueAdapter(dataList);
        craftValueAdapter.setOnItemClickListener(this);
//        adapterPacket.setPosition(position);
        recyclerView.setAdapter(craftValueAdapter);
    }

    @Override
    public void onItemClick(View view, int postion) {
        startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));
    }

}
