package app.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Layout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import app.adapter.ChooseCaseAdapter;
import app.dialog.DialogTools;
import app.entity.Info;
import app.entity.JsonData;
import app.entity.Meta;
import app.entity.Name;
import app.entity.Time;
import app.tools.MyLog;
import app.tools.TimeTools;
import app.tools.UIEventUpdate;
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ConstructionTasksActivity extends BaseActivity implements
        View.OnClickListener, DialogTools.DialogOnClickChockedListens,
        DialogTools.DialogCountTypeListens, UIEventUpdate.UIEventUpdateListener, UIEventUpdate.DataListener {
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private ArrayList<Integer> removeList = new ArrayList<Integer>();
    private ArrayList<Integer> surplusList = new ArrayList<Integer>();
    private ArrayList<Integer> save = new ArrayList<Integer>();
    private LinearLayout add_choose_case;
    private String[] strCase = new String[]{"水电工：　　", "泥水工：　　", "木工：　　　",
            "油漆工：　　", "门窗安装工：", "敲打搬运工："};
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private String name1, name2, name3, name4, name5, name6;
    private HashMap<Integer, Integer> hasCount = new HashMap<Integer, Integer>();
    private HashMap<String, String> hasID = new HashMap<String, String>();
    private ArrayList<String> caseName1 = new ArrayList<String>();
    private ArrayList<String> caseName2 = new ArrayList<String>();
    private ArrayList<String> caseName3 = new ArrayList<String>();
    private ArrayList<String> caseName4 = new ArrayList<String>();
    private ArrayList<String> caseName5 = new ArrayList<String>();
    private ArrayList<String> caseName6 = new ArrayList<String>();

    private ArrayList<View> view1 = new ArrayList<View>();

    private HashMap<Integer,View> has1 = new HashMap<Integer,View>();
    private HashMap<Integer,View> has2 = new HashMap<Integer,View>();
    private HashMap<Integer,View> has3 = new HashMap<Integer,View>();
    private HashMap<Integer,View> has4 = new HashMap<Integer,View>();
    private HashMap<Integer,View> has5 = new HashMap<Integer,View>();
    private HashMap<Integer,View> has6 = new HashMap<Integer,View>();

    private HashMap<Integer,TextView> tvhas1 = new HashMap<Integer,TextView>();
    private HashMap<Integer,TextView> tvhas2 = new HashMap<Integer,TextView>();
    private HashMap<Integer,TextView> tvhas3 = new HashMap<Integer,TextView>();
    private HashMap<Integer,TextView> tvhas4 = new HashMap<Integer,TextView>();
    private HashMap<Integer,TextView> tvhas5 = new HashMap<Integer,TextView>();
    private HashMap<Integer,TextView> tvhas6 = new HashMap<Integer,TextView>();

    private HashMap<String,String> removeDI1 =new HashMap<String,String>();
    private HashMap<String,String> removeDI2 =new HashMap<String,String>();
    private HashMap<String,String> removeDI3 =new HashMap<String,String>();
    private HashMap<String,String> removeDI4 =new HashMap<String,String>();
    private HashMap<String,String> removeDI5 =new HashMap<String,String>();
    private HashMap<String,String> removeDI6 =new HashMap<String,String>();

    private HashMap<Integer, String> hasType = new HashMap<Integer, String>();

    private List<Name> listName = new ArrayList<Name>();

    private String hint = "通过平台找工匠";
    private TextView tv_time_choose;
    private EditText et_workplace;

    private JsonData jsonData;
    private List<Info> info;
    @Override
    public void obtainData() {
//        jsonData = (JsonData) getIntent().getSerializableExtra("NewDecorationActivity");
//        info = jsonData.getInfo();
    }

    @Override
    public void initUI() {

        et_workplace = (EditText) findViewById(R.id.et_workplace);
        add_choose_case = (LinearLayout) findViewById(R.id.add_choose_case);
        TextView tv_add_case = (TextView) findViewById(R.id.tv_add_case);
        tv_time_choose = (TextView) findViewById(R.id.tv_time_choose);
        RelativeLayout rl_time_chose = (RelativeLayout) findViewById(R.id.rl_time_chose);
        rl_time_chose.setOnClickListener(this);
        tv_add_case.setOnClickListener(this);
        UIEventUpdate.getInstance().register(this);
        UIEventUpdate.getInstance().registerData(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIEventUpdate.getInstance().unregister(this);
        UIEventUpdate.getInstance().unregisterData(this);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_construction_tasks;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("安排施工任务");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {//surplusList
            case R.id.rl_time_chose:
                ShowBirthdayTime();
            break;
            case R.id.tv_add_case:
                for (int i = 0; i < list.size(); i++) {
                    if (!removeList.contains(list.get(i))) {
                        surplusList.add(list.get(i));
                    }
                }
                list.clear();
                save.clear();
                save.addAll(surplusList);
                list.addAll(surplusList);
                DialogTools.chooseCase(this, list).show();
                DialogTools.setCheckedChoose(this);
                break;
            case 101:

                if(hint_time!=null){
                    Intent intent1 = new Intent(this, ServerTimeActivity.class);
                    intent1.putExtra("CASE_TYPE", "水电工");
                    intent1.putExtra("time_day",getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent1);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 102:
                if(hint_time!=null){
                    Intent intent2 = new Intent(this, ServerTimeActivity.class);
                    intent2.putExtra("CASE_TYPE", "泥水工");
                    intent2.putExtra("time_day", getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent2);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 103:
                if(hint_time!=null){
                    Intent intent3 = new Intent(this, ServerTimeActivity.class);
                    intent3.putExtra("CASE_TYPE", "木工");
                    intent3.putExtra("time_day", getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent3);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 104:
                if(hint_time!=null){
                    Intent intent4 = new Intent(this, ServerTimeActivity.class);
                    intent4.putExtra("CASE_TYPE", "油漆工");
                    intent4.putExtra("time_day", getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent4);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 105:
                if(hint_time!=null){
                    Intent intent5 = new Intent(this, ServerTimeActivity.class);
                    intent5.putExtra("CASE_TYPE", "门窗安装工");
                    intent5.putExtra("time_day", getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent5);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 106:
                if(hint_time!=null){
                    Intent intent6 = new Intent(this, ServerTimeActivity.class);
                    intent6.putExtra("CASE_TYPE", "敲打搬运工");
                    intent6.putExtra("time_day", getDay(hint_time));
                    if(getDay(hint_time)>1){
                        startActivity(intent6);
                    }else {
                        Uihelper.showToast(this,"请先选择验收时间要大于当前时间");
                    }
                }else {
                    Uihelper.showToast(this,"请先选择验收时间");
                }
                break;
            case 111:
                DialogTools.billingType(this, 1).show();
                DialogTools.setTypeChoose(this);
                break;
            case 112:
                DialogTools.billingType(this, 2).show();
                DialogTools.setTypeChoose(this);
                break;
            case 113:
                DialogTools.billingType(this, 3).show();
                DialogTools.setTypeChoose(this);
                break;
            case 114:
                DialogTools.billingType(this, 4).show();
                DialogTools.setTypeChoose(this);
                break;
            case 115:
                DialogTools.billingType(this, 5).show();
                DialogTools.setTypeChoose(this);
                break;
            case 116:
                DialogTools.billingType(this, 6).show();
                DialogTools.setTypeChoose(this);
                break;
            case 121://邀请
//                Intent intent1 = new Intent(this, SearchActivity.class);
//                intent1.putExtra("CASE_TYPE", "水电工");
//                intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent1);
                break;
            case 122:
//                Intent intent2 = new Intent(this, SearchActivity.class);
//                intent2.putExtra("CASE_TYPE", "泥水工");
//                intent2.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent2);
                break;
            case 123:
//                Intent intent3 = new Intent(this, SearchActivity.class);
//                intent3.putExtra("CASE_TYPE", "木工");
//                intent3.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent3);
                break;
            case 124:
//                Intent intent4 = new Intent(this, SearchActivity.class);
//                intent4.putExtra("CASE_TYPE", "油漆工");
//                intent4.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent4);
                break;
            case 125:
//                Intent intent5 = new Intent(this, SearchActivity.class);
//                intent5.putExtra("CASE_TYPE", "门窗安装工");
//                intent5.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent5);
                break;
            case 126:
//                Intent intent6 = new Intent(this, SearchActivity.class);
//                intent6.putExtra("CASE_TYPE", "敲打搬运工");
//                intent6.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
//                startActivity(intent6);
                break;
            case 131://rl_add_case
//                if(name1!=null){
                name1 = null;
                count1++;
//                    name[1].setText(hint);
                if (add_item[1] != null) {
                    add_item[1].removeAllViews();
                }
                caseName1.add(hint);
                view1.clear();
                has1.clear();
                tvhas1.clear();
                for (int i = 0; i < count1; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    view1.add(layout);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tvhas1.put(10001+i,tv_name);
                    tv_case_type.setText(strCase[0]);
                    rl_add_case.setId(1001 + i);
                    has1.put(i + 1, layout);
                    rl_invitation.setId(10001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "水电工");
                            intent1.putExtra("VIEW_ID",view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count1--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("100", "");
                            View view = has1.get(Integer.valueOf(replace).intValue());
                            add_item[1].removeView(view);
                            String reid = strID.replace("100", "1000");
                            String caseid = removeDI1.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }
                        }
                    });
                    if (!caseName1.get(i).equals(hint)) {
                        tv_name.setText(caseName1.get(i));
                    }

                    add_item[1].addView(layout);
                }
//                }else {
//                    Uihelper.showToast(this,"请先在平台邀请工匠");
//                }
                break;
            case 132:
//                if(name2!=null){
                name2 = null;
                count2++;
//                    name[2].setText(hint);
                if (add_item[2] != null) {
                    add_item[2].removeAllViews();
                }
                caseName2.add(hint);
                has2.clear();
                tvhas2.clear();
                for (int i = 0; i < count2; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);

                    tv_case_type.setText(strCase[1]);
                    rl_add_case.setId(1101 + i);
                    has2.put(i + 1, layout);
                    tvhas2.put(11001+i,tv_name);
                    rl_invitation.setId(11001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "泥水工");
                            intent1.putExtra("VIEW_ID", view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count2--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("110", "");
                            View view = has2.get(Integer.valueOf(replace).intValue());
                            add_item[2].removeView(view);
                            String reid = strID.replace("110", "1100");
                            String caseid = removeDI2.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }

                        }
                    });
                    if (!caseName2.get(i).equals(hint)) {
                        tv_name.setText(caseName2.get(i));
                    }
                    add_item[2].addView(layout);
                }
//                }else{
//                    Uihelper.showToast(this,"请先在平台邀请工匠");
//                }
                break;
            case 133:
//                if(name3!=null){
                name3 = null;
                count3++;
//                    name[3].setText(hint);
                if (add_item[3] != null) {
                    add_item[3].removeAllViews();
                }
                caseName3.add(hint);
                has3.clear();
                tvhas3.clear();
                for (int i = 0; i < count3; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tv_case_type.setText(strCase[2]);
                    rl_add_case.setId(1201 + i);
                    has3.put(i + 1, layout);
                    tvhas3.put(12001 + i, tv_name);
                    rl_invitation.setId(12001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "木工");
                            intent1.putExtra("VIEW_ID", view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count3--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("120", "");
                            View view = has3.get(Integer.valueOf(replace).intValue());
                            add_item[3].removeView(view);
                            String reid = strID.replace("120", "1200");
                            String caseid = removeDI3.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }

                        }
                    });
                    if (!caseName3.get(i).equals(hint)) {
                        tv_name.setText(caseName3.get(i));
                    }
                    add_item[3].addView(layout);
                }
//                }
                break;
            case 134:
//                if(name4!=null){
                name4 = null;
                count4++;
//                    name[4].setText(hint);
                if (add_item[4] != null) {
                    add_item[4].removeAllViews();
                }
                caseName4.add(hint);
                has4.clear();
                tvhas4.clear();
                for (int i = 0; i < count4; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tv_case_type.setText(strCase[3]);
                    rl_add_case.setId(1301 + i);
                    has4.put(i + 1, layout);
                    tvhas4.put(13001 + i, tv_name);
                    rl_invitation.setId(13001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "油漆工");
                            intent1.putExtra("VIEW_ID", view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count4--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("130", "");
                            View view = has4.get(Integer.valueOf(replace).intValue());
                            add_item[4].removeView(view);

                            String reid = strID.replace("130", "1300");
                            String caseid = removeDI4.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }

                        }
                    });
                    if (!caseName4.get(i).equals(hint)) {
                        tv_name.setText(caseName4.get(i));
                    }
                    add_item[4].addView(layout);
                }
//                }else {
//                    Uihelper.showToast(this, "请先在平台邀请工匠");
//                }
                break;
            case 135:
//                if(name5!=null){
                name5 = null;
                count5++;
//                    name[5].setText(hint);
                if (add_item[5] != null) {
                    add_item[5].removeAllViews();
                }
                caseName5.add(hint);
                has5.clear();
                tvhas5.clear();
                for (int i = 0; i < count5; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tv_case_type.setText(strCase[4]);
                    rl_add_case.setId(1401 + i);
                    has5.put(i + 1, layout);
                    tvhas5.put(14001 + i, tv_name);
                    rl_invitation.setId(14001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "门窗安装工");
                            intent1.putExtra("VIEW_ID", view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count5--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("140", "");
                            View view = has5.get(Integer.valueOf(replace).intValue());
                            add_item[5].removeView(view);

                            String reid = strID.replace("140", "1400");
                            String caseid = removeDI5.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }

                        }
                    });
                    if (!caseName5.get(i).equals(hint)) {
                        tv_name.setText(caseName5.get(i));
                    }
                    add_item[5].addView(layout);
                }
//                }else{
//                    Uihelper.showToast(this, "请先在平台邀请工匠");
//                }
                break;
            case 136:
//                if(name6!=null){
                name6 = null;
                count6++;
//                    name[6].setText(hint);
                if (add_item[6] != null) {
                    add_item[6].removeAllViews();
                }
                caseName6.add(hint);
                has6.clear();
                tvhas6.clear();
                for (int i = 0; i < count6; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tv_case_type.setText(strCase[5]);
                    rl_add_case.setId(1501 + i);
                    has6.put(i + 1, layout);
                    tvhas6.put(15001 + i, tv_name);
                    rl_invitation.setId(15001 + i);
                    rl_invitation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent1 = new Intent(ConstructionTasksActivity.this, SearchActivity.class);
                            intent1.putExtra("CASE_TYPE", "敲打搬运工");
                            intent1.putExtra("VIEW_ID", view.getId());
                            intent1.putExtra("FROM_ConstructionTasksActivity", "ConstructionTasksActivity");
                            startActivity(intent1);
                        }
                    });
                    rl_add_case.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count6--;
                            int id = v.getId();
                            String strID = String.valueOf(id);
                            String replace = strID.replace("150", "");
                            View view = has6.get(Integer.valueOf(replace).intValue());
                            add_item[6].removeView(view);

                            String reid = strID.replace("150", "1500");
                            String caseid = removeDI6.get(reid);
                            if(hasID.containsKey(caseid)){
                                hasID.remove(caseid);
                            }

                        }
                    });
                    if (!caseName6.get(i).equals(hint)) {
                        tv_name.setText(caseName6.get(i));
                    }
                    add_item[6].addView(layout);
                }
//                }else {
//                    Uihelper.showToast(this, "请先在平台邀请工匠");
//                }
                break;
            case 141:
                add_choose_case.removeView(layout[1]);
                removeList.add(1);
                has1.clear();
                break;
            case 142:
                add_choose_case.removeView(layout[2]);
                removeList.add(2);
                has2.clear();
                break;
            case 143:
                add_choose_case.removeView(layout[3]);
                removeList.add(3);
                has3.clear();
                break;
            case 144:
                add_choose_case.removeView(layout[4]);
                removeList.add(4);
                has4.clear();
                break;
            case 145:
                add_choose_case.removeView(layout[5]);
                removeList.add(5);
                has5.clear();
                break;
            case 146:
                add_choose_case.removeView(layout[6]);
                removeList.add(6);
                has6.clear();
                break;

        }
    }



    private ChooseCaseAdapter[] adapter;
    private MyListView[] list_choose;
    private LinearLayout[] add_item = new LinearLayout[7];
    private View[] layout = new View[7];
    private boolean isFirst;
    private TextView[] biling_type;
    private TextView[] name;

    @Override
    public void onCheckedChoose(HashMap<Integer, String> hasMap) {
        if (hasMap != null) {
            removeList.clear();
            surplusList.clear();
            hasType.clear();
            hasType = (HashMap<Integer, String>) hasMap.clone();
            list.clear();
            EditText[] price = new EditText[7];
            name = new TextView[7];
            biling_type = new TextView[7];
            list_choose = new MyListView[7];

            adapter = new ChooseCaseAdapter[7];
            for (int i = 1; i < 7; i++) {
                if (hasMap.containsKey(i)) {
                    list.add(i);
                    if (!isFirst) {
                        isFirst = true;
                        layout[i] = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_choose_case, null);
                        RelativeLayout rl_to_time = (RelativeLayout) layout[i].findViewById(R.id.rl_to_time);
                        price[i] = (EditText) layout[i].findViewById(R.id.et_price);
                        name[i] = (TextView) layout[i].findViewById(R.id.et_name);
                        RelativeLayout rl_biling_type = (RelativeLayout) layout[i].findViewById(R.id.rl_biling_type);

                        RelativeLayout rl_add_case = (RelativeLayout) layout[i].findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout[i].findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout[i].findViewById(R.id.tv_case_type);
                        TextView tv_case_title = (TextView) layout[i].findViewById(R.id.tv_case_title);
                        TextView tv_deleate_case = (TextView) layout[i].findViewById(R.id.tv_deleate_case);
                        tv_case_title.setText(strCase[i - 1].replace("：", " "));
                        biling_type[i] = (TextView) layout[i].findViewById(R.id.tv_biling_type);
                        add_item[i] = (LinearLayout) layout[i].findViewById(R.id.add_item);
                        tv_case_type.setText(strCase[i - 1]);
                        rl_to_time.setId(100 + i);
                        rl_biling_type.setId(110 + i);
                        rl_invitation.setId(120 + i);
                        rl_add_case.setId(130 + i);
                        tv_deleate_case.setId(140 + i);
                        rl_to_time.setOnClickListener(this);
                        rl_biling_type.setOnClickListener(this);
                        rl_invitation.setOnClickListener(this);
                        rl_add_case.setOnClickListener(this);
                        tv_deleate_case.setOnClickListener(this);

                        add_choose_case.addView(layout[i]);
                    } else {
                        if (!save.contains(i)) {
                            layout[i] = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_choose_case, null);
                            RelativeLayout rl_to_time = (RelativeLayout) layout[i].findViewById(R.id.rl_to_time);
                            price[i] = (EditText) layout[i].findViewById(R.id.et_price);
                            name[i] = (TextView) layout[i].findViewById(R.id.et_name);
                            RelativeLayout rl_biling_type = (RelativeLayout) layout[i].findViewById(R.id.rl_biling_type);
                            RelativeLayout rl_add_case = (RelativeLayout) layout[i].findViewById(R.id.rl_add_case);
                            RelativeLayout rl_invitation = (RelativeLayout) layout[i].findViewById(R.id.rl_invitation);
                            TextView tv_case_type = (TextView) layout[i].findViewById(R.id.tv_case_type);
                            TextView tv_deleate_case = (TextView) layout[i].findViewById(R.id.tv_deleate_case);
                            biling_type[i] = (TextView) layout[i].findViewById(R.id.tv_biling_type);
                            add_item[i] = (LinearLayout) layout[i].findViewById(R.id.add_item);
                            TextView tv_case_title = (TextView) layout[i].findViewById(R.id.tv_case_title);
                            tv_case_title.setText(strCase[i - 1].replace("：", " "));
                            tv_case_type.setText(strCase[i - 1]);
                            rl_to_time.setId(100 + i);
                            rl_biling_type.setId(110 + i);
                            rl_invitation.setId(120 + i);
                            rl_add_case.setId(130 + i);
                            tv_deleate_case.setId(140 + i);
                            rl_to_time.setOnClickListener(this);
                            rl_biling_type.setOnClickListener(this);
                            rl_invitation.setOnClickListener(this);
                            rl_add_case.setOnClickListener(this);
                            tv_deleate_case.setOnClickListener(this);
                            add_choose_case.addView(layout[i]);
                        }

                    }

                } else {
                    if (save.contains(i)) {
                        add_choose_case.removeView(layout[i]);
                    }
                }
                if (i == 6) {
                    save.clear();
                    save.addAll(list);
                }
            }

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
    public void onTypeChoose(String type, int i) {
        biling_type[i].setText(type);
    }

    @Override
    public void setUIUpdate(int positionKey, Object object) {
        switch (positionKey) {
            case UIEventUpdate.PositionKey.INVITATION_CASE:
                String backData = (String) object;
                MyLog.e("", "backData=" + backData);
                String[] split = backData.split("#");
                if (!hasID.containsKey(split[1])) {
                    hasID.put(split[1], split[0]);
                    if ("水电工".equals(split[0])) {
                        name1 = split[2];
//                        name[1].setText(split[2]);
                        caseName1.add(split[2]);
                        count1++;
                        if(tvhas1.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas1.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI1.put(split[3],split[1]);
                        }
                    } else if ("泥水工".equals(split[0])) {
                        name2 = split[2];
                        caseName2.add(split[2]);
//                        name[2].setText(split[2]);
                        if(tvhas2.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas2.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI2.put(split[3], split[1]);
                        }
                        count2++;
                    } else if ("木工".equals(split[0])) {
                        name3 = split[2];
                        caseName3.add(split[2]);
//                        name[3].setText(split[2]);
                        if(tvhas3.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas3.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI3.put(split[3], split[1]);
                        }
                        count3++;
                    } else if ("油漆工".equals(split[0])) {
                        name4 = split[2];
                        caseName4.add(split[2]);
//                        name[4].setText(split[2]);
                        if(tvhas4.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas4.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI4.put(split[3], split[1]);
                        }
                        count4++;
                    } else if ("门窗安装工".equals(split[0])) {
                        name5 = split[2];
                        caseName5.add(split[2]);
                        name[5].setText(split[2]);
                        if(tvhas5.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas5.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI5.put(split[3], split[1]);
                        }
                        count5++;
                    } else if ("敲打搬运工".equals(split[0])) {
                        name6 = split[2];
                        caseName6.add(split[2]);
//                        name[6].setText(split[2]);
                        if(tvhas6.containsKey(Integer.valueOf(split[3]).intValue())){
                            TextView textView = tvhas6.get(Integer.valueOf(split[3]).intValue());
                            textView.setText(split[2]);
                            removeDI6.put(split[3], split[1]);
                        }
                        count6++;
                    }
                } else {
                    Uihelper.showToast(ConstructionTasksActivity.this, "您已经邀请过了");
                }
                MyLog.e("", "backData=" + backData);
                break;

        }
    }


    private int getDay(String hint_time){
        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1=sdf.parse(date);
            Date d2=sdf.parse(hint_time);
            return TimeTools.daysBetween(d1, d2);
//                    Uihelper.showToast(this,""+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String hint_time;

    private void ShowBirthdayTime() {
        // TODO Auto-generated method stub
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);

        DatePickerDialog datePicker = new DatePickerDialog(
                ConstructionTasksActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                String time = year + "年" + (monthOfYear + 1) + "月"
                        + dayOfMonth+"日";
                tv_time_choose.setText(time);
                hint_time=year + "-" + (monthOfYear + 1) + "-"
                        + dayOfMonth;
//                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date d1=sdf.parse("2012-09-08 10:10:10");
//                Date d2=sdf.parse("2012-09-15 00:00:00");
            }
        }, year, month, date);
        datePicker.show();
    }

    private ArrayList<String> wtype = new ArrayList<>();
    public void release(View view){
        List<Info> list = new ArrayList<Info>();
        for (int i=1;i<7;i++){
            if(hasType.containsKey(i)){
                wtype.add(hasType.get(i));
            }
        }

        for (int j=0;j<wtype.size();j++){
            Info info = new Info();
        }
    }

    private List<Time> listShui = new ArrayList<>();
    private List<Time> listNi = new ArrayList<>();
    private List<Time> listMu = new ArrayList<>();
    private List<Time> listYou = new ArrayList<>();
    private List<Time> listMen = new ArrayList<>();
    private List<Time> listQiao = new ArrayList<>();

    @Override
    public void setSendDate(int positionKey, List<Time> list, String type) {
        switch (positionKey){
            case UIEventUpdate.PositionKey.INVITATION_SERVERTIME:
                if("水电工".equals(type)){
                    listShui.clear();
                    listShui.addAll(list);
                }else if("泥水工".equals(type)){
                    listNi.clear();
                    listNi.addAll(list);
                }else if("木工".equals(type)){
                    listMu.clear();
                    listMu.addAll(list);
                }else if("油漆工".equals(type)){
                    listYou.clear();
                    listYou.addAll(list);
                }else if("门窗安装工".equals(type)){
                    listMen.clear();
                    listMen.addAll(list);
                }else if("敲打搬运工".equals(type)){
                    listQiao.clear();
                    listQiao.addAll(list);
                }
                break;
        }
    }
}
