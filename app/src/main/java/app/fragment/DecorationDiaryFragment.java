package app.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.activity.PublishDairyActivity;
import app.activity.mywork.ValueCraftActivity;
import app.activity.mywork.YSDailyActivity;
import app.activity.mywork.adapter.DailyAdapter;
import app.activity.mywork.adapter.MessageAdapter;
import app.adapter.HintAdapter;
import app.entity.DailyListResult;
import app.entity.DialyData;
import app.entity.Dialylist;
import app.entity.House;
import app.entity.HouseInfo;
import app.entity.HouseInfoResult;
import app.entity.HouseInfo_Bundle;
import app.entity.MessageItem;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Footools;
import app.utils.Config;
import app.utils.ExtendOperationController;
import app.utils.Uihelper;
import app.views.BottomSelectDialog;

/**
 * Created by Administrator on 2015/10/12.
 */
public class DecorationDiaryFragment extends BaseFrament implements View.OnClickListener ,ExtendOperationController.ExtendOperationListener, AbsListView.OnScrollListener {

    private View layout;
    private Dialog bottomSelectDialog;
    private Map<Integer,Integer> mSateHaseMap=new HashMap<>();
    private DailyAdapter dailyAdapter;
    private ExtendOperationController operationListen;
    private HouseInfo_Bundle houseInfo_bundle;

    private int page;
    private int p=1;
    private boolean isOver;
    private boolean isFirstLoaded;
    private View inflate;
    private PullToRefreshListView pull_list;
    List<DialyData> items=new ArrayList<>();

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
        if(!isFirstLoaded){
            mWaitingDialog.show();
        }
        HttpRequest.getDailyLogByHouseId(getActivity(), "753665", p, new ICallback<DailyListResult>() {
            @Override
            public void onSucceed(DailyListResult result) {
                isFirstLoaded=true;
                pull_list.onRefreshComplete();
                mWaitingDialog.dismiss();
                Dialylist dialylist = result.getDialylist();
                page=dialylist.getTotalpage();
                List<DialyData>   list= dialylist.getList();
                if (list==null){
                    return;
                }
                if(page==0){
                    isOver = false;
                    HintAdapter hintAdapter = new HintAdapter(getActivity());
                    pull_list.getRefreshableView().setDividerHeight(0);
                    pull_list.setAdapter(hintAdapter);
                }else {
                    items.addAll(list);
                    if(p<=page){
                        if(p<=page-1){
                            isOver = true;
                        }
                    }else {
                        isOver = false;
                        Footools.removeFoot(pull_list, getActivity(), inflate);
                    }
                    if(p==1){
                        dailyAdapter = new DailyAdapter(getActivity(), items, imageLoader, options);
                        sortState(items);
                        pull_list.setAdapter(dailyAdapter);
                    }else {
                        sortState(items);
                        dailyAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFail(String error) {
                mWaitingDialog.dismiss();
                Uihelper.showToast(getActivity(), error);

            }
        });

    }
    private void sortState(List<DialyData> list) {

        for (int i=list.size()-1;i>=0;i--){
            int state= list.get(i).getHouse_state();
            mSateHaseMap.put(state,i);
        }

        dailyAdapter.setSortHasemap(mSateHaseMap);

    }

    private void initUI() {

        pull_list= (PullToRefreshListView)layout.findViewById(R.id.listView);
        pull_list.setOnScrollListener(this);
        pull_list.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        inflate =  getActivity().getLayoutInflater().inflate(R.layout.footview, null);

        pull_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                p=1;
                items.clear();
                if (dailyAdapter!=null){

                    dailyAdapter.notifyDataSetChanged();
                }
                obtainData();
            }
        });

        layout.findViewById(R.id.btn_dairy).setOnClickListener(this);

    }


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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(visibleItemCount+firstVisibleItem>=totalItemCount- Config.NUMBER&&isOver){
            p++;
            if(page>1&&p!=page){
                Footools.addFoot(pull_list, getActivity(), inflate);
            }
            isOver=false;
            obtainData();
        }
    }
}
