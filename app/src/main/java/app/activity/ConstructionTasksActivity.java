package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.ArrayList;
import java.util.HashMap;

import app.adapter.ChooseCaseAdapter;
import app.dialog.DialogTools;
import app.tools.MyLog;
import app.tools.UIEventUpdate;
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ConstructionTasksActivity extends BaseActivity implements
        View.OnClickListener, DialogTools.DialogOnClickChockedListens,
        DialogTools.DialogCountTypeListens, UIEventUpdate.UIEventUpdateListener {
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
    private String name1,name2,name3,name4,name5,name6;
    private HashMap<Integer, Integer> hasCount = new HashMap<Integer, Integer>();
    private HashMap<String,String> hasID = new HashMap<String,String>();
    private ArrayList<String> caseName1=new ArrayList<String>();
    private ArrayList<String> caseName2=new ArrayList<String>();
    private ArrayList<String> caseName3=new ArrayList<String>();
    private ArrayList<String> caseName4=new ArrayList<String>();
    private ArrayList<String> caseName5=new ArrayList<String>();
    private ArrayList<String> caseName6=new ArrayList<String>();
    private String hint = "通过平台找工匠";
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        add_choose_case = (LinearLayout) findViewById(R.id.add_choose_case);
        TextView tv_add_case= (TextView) findViewById(R.id.tv_add_case);
        tv_add_case.setOnClickListener(this);
        UIEventUpdate.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIEventUpdate.getInstance().unregister(this);
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
            case R.id.tv_add_case:
                for (int i=0;i<list.size();i++){
                    if(!removeList.contains(list.get(i))){
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
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
            case 105:
                break;
            case 106:
                break;
            case 111:
                DialogTools.billingType(this,1).show();
                DialogTools.setTypeChoose(this);
                break;
            case 112:
                DialogTools.billingType(this,2).show();
                DialogTools.setTypeChoose(this);
                break;
            case 113:
                DialogTools.billingType(this,3).show();
                DialogTools.setTypeChoose(this);
                break;
            case 114:
                DialogTools.billingType(this,4).show();
                DialogTools.setTypeChoose(this);
                break;
            case 115:
                DialogTools.billingType(this,5).show();
                DialogTools.setTypeChoose(this);
                break;
            case 116:
                DialogTools.billingType(this,6).show();
                DialogTools.setTypeChoose(this);
                break;
            case 121://邀请
                Intent intent1 = new Intent(this,SearchActivity.class);
                intent1.putExtra("CASE_TYPE","水电工");
                intent1.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent1);
                break;
            case 122:
                Intent intent2 = new Intent(this,SearchActivity.class);
                intent2.putExtra("CASE_TYPE","泥水工");
                intent2.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent2);
                break;
            case 123:
                Intent intent3 = new Intent(this,SearchActivity.class);
                intent3.putExtra("CASE_TYPE","木工");
                intent3.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent3);
                break;
            case 124:
                Intent intent4 = new Intent(this,SearchActivity.class);
                intent4.putExtra("CASE_TYPE","油漆工");
                intent4.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent4);
                break;
            case 125:
                Intent intent5 = new Intent(this,SearchActivity.class);
                intent5.putExtra("CASE_TYPE","门窗安装工");
                intent5.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent5);
                break;
            case 126:
                Intent intent6 = new Intent(this,SearchActivity.class);
                intent6.putExtra("CASE_TYPE","敲打搬运工");
                intent6.putExtra("FROM_ConstructionTasksActivity","ConstructionTasksActivity");
                startActivity(intent6);
                break;
            case 131://rl_add_case
                if(name1!=null){
                    name1=null;
                    count1++;
                    name[1].setText(hint);
                    add_item[1].removeAllViews();
                    for (int i = 0; i < caseName1.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[0]);
                        tv_name.setText(caseName1.get(i));
                        add_item[1].addView(layout);
                    }
                }else {
                    Uihelper.showToast(this,"请先在平台邀请工匠");
                }
                break;
            case 132:
                if(name2!=null){
                    name2=null;
                    count2++;
                    name[2].setText(hint);
                    add_item[2].removeAllViews();
                    for (int i = 0; i < caseName2.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[1]);
                        tv_name.setText(caseName2.get(i));
                        add_item[2].addView(layout);
                    }
                }else{
                    Uihelper.showToast(this,"请先在平台邀请工匠");
                }
                break;
            case 133:
                if(name3!=null){
                    name3=null;
                    count3++;
                    name[3].setText(hint);
                    add_item[3].removeAllViews();
                    for (int i = 0; i < caseName3.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[2]);
                        tv_name.setText(caseName3.get(i));
                        add_item[3].addView(layout);
                    }
                }
                break;
            case 134:
                if(name4!=null){
                    name4=null;
                    count4++;
                    name[4].setText(hint);
                    add_item[4].removeAllViews();
                    for (int i = 0; i < caseName4.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[3]);
                        tv_name.setText(caseName4.get(i));
                        add_item[4].addView(layout);
                    }
                }else {
                    Uihelper.showToast(this, "请先在平台邀请工匠");
                }
                break;
            case 135:
                if(name5!=null){
                    name5=null;
                    count5++;
                    name[5].setText(hint);
                    add_item[5].removeAllViews();
                    for (int i = 0; i < caseName5.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[4]);
                        tv_name.setText(caseName5.get(i));
                        add_item[5].addView(layout);
                    }
                }else{
                    Uihelper.showToast(this, "请先在平台邀请工匠");
                }
                break;
            case 136:
                if(name6!=null){
                    name6=null;
                    count6++;
                    name[6].setText(hint);
                    add_item[6].removeAllViews();
                    for (int i = 0; i < caseName6.size(); i++) {
                        View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                        RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                        TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                        tv_case_type.setText(strCase[5]);
                        tv_name.setText(caseName6.get(i));
                        add_item[6].addView(layout);
                    }
                }else {
                    Uihelper.showToast(this, "请先在平台邀请工匠");
                }
                break;
            case 141:
                add_choose_case.removeView(layout[1]);
                removeList.add(1);
                break;
            case 142:
                add_choose_case.removeView(layout[2]);
                removeList.add(2);
                break;
            case 143:
                add_choose_case.removeView(layout[3]);
                removeList.add(3);
                break;
            case 144:
                add_choose_case.removeView(layout[4]);
                removeList.add(4);
                break;
            case 145:
                add_choose_case.removeView(layout[5]);
                removeList.add(5);
                break;
            case 146:
                add_choose_case.removeView(layout[6]);
                removeList.add(6);
                break;

        }
    }

    private ChooseCaseAdapter[] adapter;
    private MyListView[] list_choose;
    private LinearLayout[] add_item;
    private View[] layout = new View[7];
    private boolean isFirst;
    private TextView[] biling_type;
    private TextView[] name;

    @Override
    public void onCheckedChoose(HashMap<Integer, String> hasMap) {
        if (hasMap != null) {
            removeList.clear();
            surplusList.clear();
            isFirst = true;
            list.clear();
            EditText[] price = new EditText[7];
            name = new TextView[7];
            biling_type = new TextView[7];
            list_choose = new MyListView[7];
            add_item = new LinearLayout[7];
            adapter = new ChooseCaseAdapter[7];
            for (int i = 1; i < 7; i++) {
                if (hasMap.containsKey(i)) {
                    list.add(i);
                    if (!isFirst) {
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
                        tv_case_title.setText(strCase[i - 1].replace("："," "));
                        biling_type[i] = (TextView) layout[i].findViewById(R.id.tv_biling_type);
                        add_item[i] = (LinearLayout) layout[i].findViewById(R.id.add_item);
                        tv_case_type.setText(strCase[i - 1]);
                        rl_to_time.setId(100 + i);
                        rl_biling_type.setId(110 + i);
                        rl_invitation.setId(120 + i);
                        rl_add_case.setId(130 + i);
                        tv_deleate_case.setId(140+i);
                        rl_to_time.setOnClickListener(this);
                        rl_biling_type.setOnClickListener(this);
                        rl_invitation.setOnClickListener(this);
                        rl_add_case.setOnClickListener(this);

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
                            tv_case_title.setText(strCase[i - 1].replace("："," "));
                            tv_case_type.setText(strCase[i - 1]);
                            rl_to_time.setId(100 + i);
                            rl_biling_type.setId(110 + i);
                            rl_invitation.setId(120 + i);
                            rl_add_case.setId(130 + i);
                            tv_deleate_case.setId(140+i);
                            rl_to_time.setOnClickListener(this);
                            rl_biling_type.setOnClickListener(this);
                            rl_invitation.setOnClickListener(this);
                            rl_add_case.setOnClickListener(this);
                            tv_deleate_case.setOnClickListener(this);
                            add_choose_case.addView(layout[i]);
                        }

                    }

                }else {
                    if(save.contains(i)){
                        add_choose_case.removeView(layout[i]);
                    }
                }
                if(i==6){
                    save.clear();
                    save.addAll(list);
                }
            }

        }


    }

    @Override
    public void onTypeChoose(String type,int i) {
        biling_type[i].setText(type);
    }

    @Override
    public void setUIUpdate(int positionKey, Object object) {
       switch (positionKey){
           case UIEventUpdate.PositionKey.INVITATION_CASE:
               String backData = (String) object;
               MyLog.e("","backData="+backData);
               String[] split = backData.split("#");
               if(!hasID.containsKey(split[1])){
                   hasID.put(split[1], split[0]);
                   if("水电工".equals(split[0])){
                       name1=split[2];
                       name[1].setText(split[2]);
                       caseName1.add(split[2]);
                   }else if("泥水工".equals(split[0])){
                       name2=split[2];
                       caseName2.add(split[2]);
                       name[2].setText(split[2]);
                   }else if("木工".equals(split[0])){
                       name3=split[2];
                       caseName3.add(split[2]);
                       name[3].setText(split[2]);
                   }else if("油漆工".equals(split[0])){
                       name4=split[2];
                       caseName4.add(split[2]);
                       name[4].setText(split[2]);
                   }else  if("门窗安装工".equals(split[0])){
                       name5=split[2];
                       caseName5.add(split[2]);
                       name[5].setText(split[2]);
                   }else  if("敲打搬运工".equals(split[0])){
                       name6=split[2];
                       caseName6.add(split[2]);
                       name[6].setText(split[2]);
                   }
               }else {
                   Uihelper.showToast(ConstructionTasksActivity.this,"您已经邀请过了");
               }
               MyLog.e("","backData="+backData);
               break;
       }
    }
}
