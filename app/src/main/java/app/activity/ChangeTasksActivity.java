package app.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.adapter.ChangeTaskAdapter;
import app.dialog.DialogTools;
import app.entity.Cinfo;
import app.entity.Cname;
import app.entity.Ctime;
import app.entity.HouseData;
import app.entity.Info;
import app.entity.JsonDataResult;
import app.entity.JsonResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.tools.StatusTools;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ChangeTasksActivity extends BaseActivity implements ChangeTaskAdapter.removeItem, View.OnClickListener, DialogTools.DialogOnClickChockedListens, ChangeTaskAdapter.addNameItem, ChangeTaskAdapter.billingTypeItem, ChangeTaskAdapter.removeNameItem {


    private HashMap<String,HouseData> startDataMap = new HashMap<>();
    private HashMap<String,HouseData> saveStartDataMap = new HashMap<>();
    private ListView list_task;
    private ChangeTaskAdapter adapter;
    private List<HouseData> info;
    private TextView et_workplace;
    private TextView tv_time_choose;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private ArrayList<Integer> netWorkType = new ArrayList<Integer>();
    private ArrayList<Integer> saveNetWorkType = new ArrayList<Integer>();
    private ArrayList<Integer> removeList = new ArrayList<Integer>();
    private ArrayList<Integer> surplusList = new ArrayList<Integer>();
    private ArrayList<Integer> save = new ArrayList<Integer>();
    List<Cname> name = new ArrayList<>();
    List<Ctime> time = new ArrayList<>();
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

         et_workplace = (TextView) findViewById(R.id.et_workplace);
         tv_time_choose = (TextView) findViewById(R.id.tv_time_choose);
        TextView tv_add_case = (TextView) findViewById(R.id.tv_add_case);
        list_task = (ListView) findViewById(R.id.list_task);
        tv_add_case.setOnClickListener(this);
        adapter = new ChangeTaskAdapter(ChangeTasksActivity.this);
        adapter.setAddNameListens(this);
        adapter.setRemoveListens(this);
        adapter.setBillingTypeListens(this);
        adapter.setRemoveNameListens(this);
        HttpRequest.getDetailTask(this, "753666", new ICallback<JsonResult>() {
            @Override
            public void onSucceed(JsonResult result) {
                JsonDataResult housedtail = result.getHousedtail();
                info = housedtail.getInfo();
                adapter.setData(info);
                list_task.setAdapter(adapter);
                et_workplace.setText(housedtail.getWorkplace());
                tv_time_choose.setText(housedtail.getAddtime());
                for (int i=0;i<info.size();i++){
                    String wtype = info.get(i).getWtype();
                    list.add(StatusTools.getWorkType(StatusTools.workType(wtype)));
                    startDataMap.put(wtype, info.get(i));
                    saveStartDataMap.put(wtype,info.get(i));
                }
                netWorkType.addAll(list);

            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(ChangeTasksActivity.this,error);
            }
        });
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_change_tasks;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("安排施工任务");
    }

    @Override
    public void removePosition(int position,String wtype) {
        info.remove(position);
        startDataMap.remove(wtype);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_add_case:
//                for (int i = 0; i < list.size(); i++) {
//                    if (!removeList.contains(list.get(i))) {
//                        surplusList.add(list.get(i));
//                    }
//                }
//                list.clear();
//                save.clear();
//                save.addAll(surplusList);
//                list.addAll(surplusList);
                DialogTools.chooseCase(this, list).show();
                DialogTools.setCheckedChoose(this);
                break;

        }
    }

    @Override
    public void onCheckedChoose(HashMap<Integer, String> hasMap) {
        if (hasMap != null) {
            removeList.clear();
            surplusList.clear();
            list.clear();
            saveNetWorkType.clear();
            for(int j=0;j<netWorkType.size();j++){
                int integer = netWorkType.get(j);
                if (!hasMap.containsKey(integer)) {
//                    info.remove(j);
                    startDataMap.remove(String.valueOf(integer));
                    if(saveStartDataMap.containsKey(""+integer)){
                        saveStartDataMap.remove(String.valueOf(integer));
                    }
                }else {
                    saveNetWorkType.add(integer);
                }
            }
            netWorkType.clear();
            for (int i = 1; i < 7; i++) {
                if (hasMap.containsKey(i)) {
                    netWorkType.add(i);
                }
            }
            list.addAll(netWorkType);
            info.clear();
            for (int k=0;k<list.size();k++){
                if(!saveNetWorkType.contains(list.get(k))){
                    HouseData data = new HouseData();
                    data.setNum("0");
                    data.setWtype("" + list.get(k));
                    data.setCharge("0");
                    data.setChargetype(null);
                    data.setName(name);
                    data.setTime(time);
//                    info.add(data);
                    startDataMap.put(String.valueOf(list.get(k)),data);
                }
            }
//            saveStartDataMap.get
            for (int m = 1; m < 7; m++) {

                if(saveStartDataMap.containsKey("" + m)){
                    startDataMap.put(""+m,saveStartDataMap.get(""+m));
                }

                if (startDataMap.containsKey(""+m)) {
                    info.add(startDataMap.get(""+m));
                }
            }
//            adapter.setData(info);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDismiss(HashMap<Integer, String> hasMap) {
        if (hasMap != null) {
            removeList.clear();
            surplusList.clear();
            list.clear();
            for (int i = 0; i < 7; i++) {
                if (hasMap.containsKey(i)) {
                    list.add(i);
                }

                if (i == 6) {
                    save.clear();
                    save.addAll(list);
                }
            }
        }
    }

    @Override
    public void addNamePosition(int position, String wtype) {
        List<Cname> name = new ArrayList<>();
        Cname cname = new Cname();
        if(saveStartDataMap.containsKey(wtype)){
            List<Cname> sname = new ArrayList<>();
            Cname scname = new Cname();
            scname.setInvite("0");
            scname.setName("通过平台找工匠");
            scname.setUid("");
            sname.add(scname);
        }else {

        }
        cname.setName("通过平台找工匠");
        cname.setUid("");
        name.add(cname);
        HouseData houseData = startDataMap.get(wtype);
        List<Cname> name1 = houseData.getName();
//        if(saveStartDataMap.containsKey(wtype)){
//            List<Cname> name1 = houseData.getName();
//            for (int i=0;i<name1.size();i++){
//                name.add(name1.get(i));
//            }
//        }
        String num = houseData.getNum();
        int k=Integer.valueOf(num).intValue()+1;
        houseData.setNum("" + String.valueOf(k));
        name.addAll(name1);
        houseData.setName(name);
//        startDataMap.put(wtype, houseData);
        MyLog.e("","info="+info.toString());
        info.clear();
        for (int i = 1; i < 7; i++) {
            if (startDataMap.containsKey(""+i)) {
                info.add(startDataMap.get(""+i));
            }
        }
        MyLog.e("", "info="+info.toString());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void billingTypePosition(int position, String wtype,String type) {
        if(saveStartDataMap.containsKey(wtype)){
            HouseData houseData = saveStartDataMap.get(wtype);
            houseData.setChargetype(type);
        }
        HouseData houseData = startDataMap.get(wtype);
        houseData.setChargetype(StatusTools.setChargeTypes(type));
//        startDataMap.put(wtype,houseData);
        info.clear();
        for (int i = 1; i < 7; i++) {
            if (startDataMap.containsKey(""+i)) {
                info.add(startDataMap.get(""+i));
            }
        }
        MyLog.e("", "info="+info.toString());
        adapter.notifyDataSetChanged();
    }


    public void release(View v){

    }

    @Override
    public void removeNamePosition(int position, String wtype,int id) {
        Toast.makeText(ChangeTasksActivity.this,""+id,1).show();
        List<Cname> names = new ArrayList<>();
//        if(saveStartDataMap.containsKey(wtype)){
//            HouseData houseDatas = saveStartDataMap.get(wtype);
//            List<Cname> savename = houseDatas.getName();
//            savename.remove(id);
////            names.addAll(names);
////            houseDatas.setName(names);
//        }
        HouseData houseData = startDataMap.get(wtype);
        List<Cname> name = houseData.getName();
        name.remove(id);
        houseData.setNum(""+name.size());
        names.addAll(name);
        houseData.setName(names);
        startDataMap.put(wtype,houseData);
        MyLog.e("", "info=" + info.toString());
        info.clear();
        for (int i = 1; i < 7; i++) {
            if (startDataMap.containsKey(""+i)) {
                info.add(startDataMap.get(""+i));
            }
        }
        MyLog.e("", "info="+info.toString());
        adapter.notifyDataSetChanged();
    }
}
