package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.miweikeij.app.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.activity.PublishDairyActivity;
import app.activity.mywork.ValueCraftActivity;
import app.activity.mywork.YSDailyActivity;
import app.activity.mywork.adapter.DailyAdapter;
import app.entity.DailyListResult;
import app.entity.DialyData;
import app.entity.Dialylist;
import app.entity.House;
import app.entity.HouseInfo;
import app.entity.HouseInfoResult;
import app.entity.HouseInfo_Bundle;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Config;
import app.utils.ExtendOperationController;
import app.utils.Uihelper;
import app.views.BottomSelectDialog;

/**
 * Created by Administrator on 2015/10/12.
 */
public class DecorationDiaryFragment extends BaseFrament implements View.OnClickListener ,ExtendOperationController.ExtendOperationListener {

    private View layout;
    private ListView listView;
    private Dialog bottomSelectDialog;
    private Dialylist dialylist;
    private Map<Integer,Integer> mSateHaseMap=new HashMap<>();
    private DailyAdapter dailyAdapter;
    private ExtendOperationController operationListen;
    private HouseInfo_Bundle houseInfo_bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       operationListen= ExtendOperationController.getInstance();
        operationListen.registerExtendOperationListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (operationListen!=null){
        operationListen.unRegisterExtendOperationListener(this);
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_decoration_diary, null);
        initUI();
        obtainData();
        return layout;
    }

    private void obtainData() {
        mWaitingDialog.show();
        int page = 1;
        HttpRequest.getDailyLogByHouseId(getActivity(), "753665", page, new ICallback<DailyListResult>() {
            @Override
            public void onSucceed(DailyListResult result) {
                mWaitingDialog.dismiss();
                Dialylist dialylist = result.getDialylist();
                if (dialylist != null) {

                    int totalPage = dialylist.getTotalpage();
                    List<DialyData> dialyData = dialylist.getList();
                    if (dialyData != null && dialyData.size() > 0) {
                        setData(dialyData);
                    }
                }

            }
            @Override
            public void onFail(String error) {
                mWaitingDialog.dismiss();
                Uihelper.showToast(getActivity(), error);

            }
        });

        listView.postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        },1000);

    }
    private void sortState(List<DialyData> list) {

        for (int i=list.size()-1;i>=0;i--){
            int state= list.get(i).getHouse_state();
            mSateHaseMap.put(state,i);
        }

        dailyAdapter.setSortHasemap(mSateHaseMap);

    }


    private void setData(List<DialyData> list) {

        dailyAdapter = new DailyAdapter(getActivity(), list, imageLoader, options);
        sortState(list);
        listView.setAdapter(dailyAdapter);

    }

    private void initUI() {

        listView = (ListView) layout.findViewById(R.id.listview);

        layout.findViewById(R.id.btn_dairy).setOnClickListener(this);

    }

//    @Override
//    public void onItemClick(View view, int postion) {
////        startActivity(new Intent(getActivity(), HouseActivity.class));
//        startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_dairy) {
            //写日记，弹框
            Config.init(getActivity());
            if (bottomSelectDialog==null){
                bottomSelectDialog = BottomSelectDialog.create(getActivity(), BottomSelectDialog.PopUpDialogPosition.bottom, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btn_write:
                                Intent intent=new Intent(getActivity(), PublishDairyActivity.class);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("houseInfo",houseInfo_bundle);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                bottomSelectDialog.dismiss();
                                break;
                            case R.id.btn_check:

                                Intent intent_check=new Intent(getActivity(), YSDailyActivity.class);
                                Bundle bundle_check=new Bundle();
                                bundle_check.putSerializable("houseInfo",houseInfo_bundle);
                                intent_check.putExtras(bundle_check);
                                startActivity(intent_check);
                                bottomSelectDialog.dismiss();
                                check();
                                break;
                            case R.id.btn_value:
                                startActivity(new Intent(getActivity(), ValueCraftActivity.class));
                                bottomSelectDialog.dismiss();
                                break;
                            case R.id.iv_downarrow:
                                bottomSelectDialog.dismiss();
                                break;
                        }

                    }


                });
            }
            bottomSelectDialog.show();

        }

    }

    private void check() {

//        HttpRequest.addYSDailyLog(getActivity(), UserInfo.getInstance().getId(),);
    }

    @Override
    public void excuteExtendOperation(int operationKey, Object data) {

        if (operationKey==ExtendOperationController.OperationKey.HouseInfo){
            HouseInfoResult housInfoResult= (HouseInfoResult) data;
            if (housInfoResult!=null){
               HouseInfo houseInfo=housInfoResult.getHouse();
                 houseInfo_bundle=new HouseInfo_Bundle();
                houseInfo_bundle.setId(houseInfo.getId());
                houseInfo_bundle.setOwnerId(houseInfo.getOwnerId());
                houseInfo_bundle.setStatus(houseInfo.getStatus());
                houseInfo_bundle.setYyStatus(houseInfo.getYyStatus());
            }
        }

    }
}
