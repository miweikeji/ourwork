package app.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miwei.jzj_system.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import app.entity.Time;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.MyLog;
import app.tools.StatusTools;
import app.tools.TimeTools;
import app.tools.UIEventUpdate;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ChangeTasksActivity extends BaseActivity implements
        ChangeTaskAdapter.removeItem, View.OnClickListener, DialogTools.DialogOnClickChockedListens,
        ChangeTaskAdapter.addNameItem, ChangeTaskAdapter.billingTypeItem,
        ChangeTaskAdapter.removeNameItem, ChangeTaskAdapter.addCaseNmae,
        UIEventUpdate.UIEventUpdateListener, ChangeTaskAdapter.changeTime,
        UIEventUpdate.DataListener, UIEventUpdate.DataCtimListener, ChangeTaskAdapter.moneyItem {


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
    private String hint_time;
    private  String startTime;

    private  JsonDataResult housedtail;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        String hourseID = getIntent().getStringExtra("hourseID");
        UIEventUpdate.getInstance().register(this);
        UIEventUpdate.getInstance().registerData(this);
        UIEventUpdate.getInstance().ctimregister(this);
         et_workplace = (TextView) findViewById(R.id.et_workplace);
         tv_time_choose = (TextView) findViewById(R.id.tv_time_choose);
        TextView tv_add_case = (TextView) findViewById(R.id.tv_add_case);
        RelativeLayout rl_time_chose = (RelativeLayout) findViewById(R.id.rl_time_chose);
        list_task = (ListView) findViewById(R.id.list_task);
        tv_add_case.setOnClickListener(this);
//        rl_time_chose.setOnClickListener(this);
        adapter = new ChangeTaskAdapter(ChangeTasksActivity.this);
        adapter.setAddNameListens(this);
        adapter.setRemoveListens(this);
        adapter.setBillingTypeListens(this);
        adapter.setRemoveNameListens(this);
        adapter.setAddCaseNmaeListens(this);
        adapter.setChangeTimeListens(this);
        adapter.setmoneyItem(this);
        HttpRequest.getDetailTask(this, hourseID, new ICallback<JsonResult>() {
            @Override
            public void onSucceed(JsonResult result) {
                housedtail = result.getHousedtail();
                info = housedtail.getInfo();
                adapter.setData(info);
                list_task.setAdapter(adapter);
                et_workplace.setText(housedtail.getWorkplace());
                tv_time_choose.setText(TimeTools.longToDateStr(Double.valueOf(housedtail.getChecktime())));
                startTime =  TimeTools.longToDateStr(Double.valueOf(housedtail.getAddtime()));
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
    protected void onDestroy() {
        super.onDestroy();
        UIEventUpdate.getInstance().unregister(this);
        UIEventUpdate.getInstance().unregisterData(this);
        UIEventUpdate.getInstance().ctimUnregister(this);
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
            case R.id.rl_time_chose:
                ShowBirthdayTime();
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

    private int getDay(String hint_time) {



        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(date);
            Date d2 = sdf.parse(hint_time);
            return TimeTools.daysBetween(d1, d2);
//                    Uihelper.showToast(this,""+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void ShowBirthdayTime() {
        // TODO Auto-generated method stub
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);

        DatePickerDialog datePicker = new DatePickerDialog(
                ChangeTasksActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                String time = year + "年" + (monthOfYear + 1) + "月"
                        + dayOfMonth + "日";
                tv_time_choose.setText(time);
                hint_time = year + "-" + (monthOfYear + 1) + "-"
                        + dayOfMonth;
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date d1=sdf.parse("2012-09-08 10:10:10");
//                Date d2=sdf.parse("2012-09-15 00:00:00");
            }
        }, year, month, date);
        datePicker.show();
    }




    @Override
    public void removeNamePosition(int position, String wtype,int id) {

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

    @Override
    public void addCaseNmaePosition(int position, String wtype,int id) {
        Intent intent = new Intent(ChangeTasksActivity.this, SearchActivity.class);
        intent.putExtra("CASE_TYPE", StatusTools.workType(wtype));
        intent.putExtra("VIEW_ID", id);
        intent.putExtra("FROM_ConstructionTasksActivity", "ChangeTasksActivity");
        startActivity(intent);

    }

    @Override
    public void setUIUpdate(int positionKey, Object object) {

        switch (positionKey){
            case UIEventUpdate.PositionKey.INVIATION_CHANGEtASKS:
            String backData = (String) object;
            MyLog.e("", "backData=" + backData);
            String[] split = backData.split("#");
                List<Cname> names = new ArrayList<>();
                HouseData houseData = startDataMap.get(""+StatusTools.getWorkType(split[0]));

                List<Cname> name = houseData.getName();
                Cname cname = name.get(Integer.valueOf(split[3]));
                cname.setName(split[2]);
                cname.setUid(split[1]);
                if(saveStartDataMap.containsKey(""+StatusTools.getWorkType(split[0]))){
                    cname.setInvite(cname.getInvite());
                }
                names.addAll(name);
                houseData.setName(names);
                startDataMap.put(split[0], houseData);
                info.clear();
                for (int i = 1; i < 7; i++) {
                    if (startDataMap.containsKey(""+i)) {
                        info.add(startDataMap.get(""+i));
                    }
                }
                MyLog.e("", "info=" + info.toString());
                adapter.notifyDataSetChanged();
            break;
        }
    }

    @Override
    public void changeTimePosition(int position, String wtype) {
        Double start = Double.valueOf(TimeTools.dateToLong(startTime));
        if(hint_time!=null){
            Double hint_t = Double.valueOf(TimeTools.dateToLong(hint_time));
            if(getDay(hint_time)>1){
                Uihelper.showToast(this, "请先选择验收时间要大于当前时间");
                return;
            }
        }

        if(saveStartDataMap.containsKey(wtype)){
            HouseData data = saveStartDataMap.get(wtype);
            List<Ctime> time = data.getTime();
            Intent intent = new Intent(this, ServerTimesActivity.class);
            intent.putExtra("ChangeTasksActivity",(Serializable)time);
            intent.putExtra("CASE_TYPE", StatusTools.workType(wtype));
            startActivity(intent);

        }else {
            Intent intent4 = new Intent(this, ServerTimeActivity.class);
            intent4.putExtra("CASE_TYPE", StatusTools.workType(wtype));
            intent4.putExtra("time_day", getDay(TimeTools.longToDateStrs(Double.valueOf(housedtail.getChecktime()))));
                startActivity(intent4);
        }

    }
    private List<Time> listShui = new ArrayList<>();
    private List<Time> listNi = new ArrayList<>();
    private List<Time> listMu = new ArrayList<>();
    private List<Time> listYou = new ArrayList<>();
    private List<Time> listMen = new ArrayList<>();
    private List<Time> listQiao = new ArrayList<>();

    private List<Ctime> clistShui = new ArrayList<>();
    private List<Ctime> clistNi = new ArrayList<>();
    private List<Ctime> clistMu = new ArrayList<>();
    private List<Ctime> clistYou = new ArrayList<>();
    private List<Ctime> clistMen = new ArrayList<>();
    private List<Ctime> clistQiao = new ArrayList<>();
    @Override
    public void setSendDate(int positionKey, List<Time> list, String type) {
        switch (positionKey) {
            case UIEventUpdate.PositionKey.INVITATION_SERVERTIME:
                boolean b = saveStartDataMap.containsKey("" + StatusTools.getWorkType(type));
                HouseData houseData = saveStartDataMap.get("" + StatusTools.getWorkType(type));
                HouseData houseData_s = startDataMap.get("" + StatusTools.getWorkType(type));
                if ("水电工".equals(type)) {
                    clistShui.clear();
                    listShui.clear();
                    listShui.addAll(list);
                    for (int i=0;i<listShui.size();i++){
                        Time time = listShui.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistShui.add(ctime);
                    }
//                    houseData.setTime(clistShui);
                    houseData_s.setTime(clistShui);
                } else if ("泥水工".equals(type)) {
                    listNi.clear();
                    clistNi.clear();
                    listNi.addAll(list);
                    for (int i=0;i<listNi.size();i++){
                        Time time = listNi.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistNi.add(ctime);
                    }
//                    houseData.setTime(clistNi);
                    houseData_s.setTime(clistNi);
                } else if ("木工".equals(type)) {
                    listMu.clear();
                    clistMu.clear();
                    listMu.addAll(list);
                    for (int i=0;i<listMu.size();i++){
                        Time time = listMu.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistMu.add(ctime);
                    }
//                    houseData.setTime(clistMu);
                    houseData_s.setTime(clistMu);
                } else if ("油漆工".equals(type)) {
                    listYou.clear();
                    clistYou.clear();
                    listYou.addAll(list);
                    for (int i=0;i<listYou.size();i++){
                        Time time = listYou.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistYou.add(ctime);
                    }
//                    houseData.setTime(clistYou);
                    houseData_s.setTime(clistYou);
                } else if ("门窗安装工".equals(type)) {
                    listMen.clear();
                    clistMen.clear();
                    listMen.addAll(list);
                    for (int i=0;i<listMen.size();i++){
                        Time time = listMen.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistMen.add(ctime);
                    }
//                    houseData.setTime(clistMen);
                    houseData_s.setTime(clistMen);
                } else if ("敲打搬运工".equals(type)) {
                    listQiao.clear();
                    clistQiao.clear();
                    listQiao.addAll(list);
                    for (int i=0;i<listQiao.size();i++){
                        Time time = listQiao.get(i);
                        Ctime ctime = new Ctime();
                        ctime.setAm(time.getAm());
                        ctime.setPm(time.getPm());
                        ctime.setDatatime(time.getDatatime());
                        clistQiao.add(ctime);
                    }
//                    houseData.setTime(clistQiao);
                    houseData_s.setTime(clistQiao);
                }
//                saveStartDataMap.put(type,houseData);
                startDataMap.put("" + StatusTools.getWorkType(type),houseData_s);
                saveStartDataMap.put("" + StatusTools.getWorkType(type),startDataMap.get("" + StatusTools.getWorkType(type)));
                MyLog.e("", "saveStartDataMap" + saveStartDataMap.toString());
                break;
        }
    }

    @Override
    public void setSendCtimDate(int positionKey, List<Ctime> list, String type) {
        switch (positionKey) {
            case UIEventUpdate.PositionKey.INVIATION_SERVERCTIME:
                HouseData houseData = saveStartDataMap.get("" + StatusTools.getWorkType(type));
                HouseData houseData_s = startDataMap.get("" + StatusTools.getWorkType(type));
                if ("水电工".equals(type)) {
                    clistShui.clear();
                    clistShui.addAll(list);
                    houseData.setTime(clistShui);
                    houseData_s.setTime(clistShui);

                } else if ("泥水工".equals(type)) {
                    clistNi.clear();
                    clistNi.addAll(list);
                    houseData.setTime(clistNi);
                    houseData_s.setTime(clistNi);
                } else if ("木工".equals(type)) {
                    clistMu.clear();
                    clistMu.addAll(list);
                    houseData.setTime(clistMu);
                    houseData_s.setTime(clistMu);
                } else if ("油漆工".equals(type)) {
                    clistYou.clear();
                    clistYou.addAll(list);
                    houseData.setTime(clistYou);
                    houseData_s.setTime(clistYou);
                } else if ("门窗安装工".equals(type)) {
                    clistMen.clear();
                    clistMen.addAll(list);
                    houseData.setTime(clistMen);
                    houseData_s.setTime(clistMen);
                } else if ("敲打搬运工".equals(type)) {
                    clistQiao.clear();
                    clistQiao.addAll(list);
                    houseData.setTime(clistQiao);
                    houseData_s.setTime(clistQiao);
                }
                saveStartDataMap.put("" + StatusTools.getWorkType(type),houseData);
                startDataMap.put("" + StatusTools.getWorkType(type),houseData_s);
                MyLog.e("", "saveStartDataMap" + saveStartDataMap.toString());
                break;
        }
    }


    public void releases(View view){
Toast.makeText(this,"是否进来",1).show();
        MyLog.e("","saveStartDataMap"+saveStartDataMap.toString());
        MyLog.e("","saveStartDataMap"+saveStartDataMap.toString());
    }

    @Override
    public void moneyItemPosition(int position, String money,String wtype) {
        HouseData houseData = startDataMap.get(wtype);
        houseData.setCharge(money);
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
