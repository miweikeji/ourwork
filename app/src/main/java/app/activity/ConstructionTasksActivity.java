package app.activity;

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
import app.utils.Uihelper;
import app.views.MyListView;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/25.
 */
public class ConstructionTasksActivity extends BaseActivity implements View.OnClickListener, DialogTools.DialogOnClickChockedListens, DialogTools.DialogCountTypeListens {
    private ArrayList<Integer> list = new ArrayList<Integer>();
    private ArrayList<Integer> save = new ArrayList<Integer>();
    private LinearLayout add_choose_case;
    private String[] strCase = new String[]{"水电工：　　", "泥水工：　　", "木工：　　　", "油漆工：　　", "门窗安装工：", "敲打搬运工："};
    private int count1;
    private int count2;
    private int count3;
    private int count4;
    private int count5;
    private int count6;
    private HashMap<Integer, Integer> hasCount = new HashMap<Integer, Integer>();

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        add_choose_case = (LinearLayout) findViewById(R.id.add_choose_case);
        RelativeLayout rl_choose_type = (RelativeLayout) findViewById(R.id.rl_choose_type);
        rl_choose_type.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.rl_choose_type:
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
            case 121:
                break;
            case 122:
                break;
            case 123:
                break;
            case 124:
                break;
            case 125:
                break;
            case 126:
                break;
            case 131://rl_add_case
                count1++;
                add_item[1].removeAllViews();
                for (int i = 0; i < count1; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    add_item[1].addView(layout);
                }
                break;
            case 132:
                count2++;
                add_item[2].removeAllViews();
                for (int i = 0; i < count2; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    add_item[2].addView(layout);
                }
                break;
            case 133:
                count3++;
                add_item[3].removeAllViews();
                for (int i = 0; i < count3; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    add_item[3].addView(layout);
                }
                break;
            case 134:
                count4++;
                add_item[4].removeAllViews();
                for (int i = 0; i < count4; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    add_item[4].addView(layout);
                }
                break;
            case 135:
                count5++;
                add_item[5].removeAllViews();
                for (int i = 0; i < count5; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    add_item[5].addView(layout);
                }
                break;
            case 136:
                count6++;
                add_item[6].removeAllViews();
                for (int i = 0; i < count6; i++) {
                    View layout = ConstructionTasksActivity.this.getLayoutInflater().inflate(R.layout.item_list_choose_case, null);
                    RelativeLayout rl_add_case = (RelativeLayout) layout.findViewById(R.id.rl_add_case);
                    RelativeLayout rl_invitation = (RelativeLayout) layout.findViewById(R.id.rl_invitation);
                    TextView tv_case_type = (TextView) layout.findViewById(R.id.tv_case_type);
                    TextView tv_name = (TextView) layout.findViewById(R.id.tv_name);
                    tv_case_type.setText(strCase[5]);
                    add_item[6].addView(layout);
                }
                break;

        }
    }

    private ChooseCaseAdapter[] adapter;
    private MyListView[] list_choose;
    private LinearLayout[] add_item;
    private View[] layout = new View[7];
    private boolean isFirst;
    private TextView[] biling_type;

    @Override
    public void onCheckedChoose(HashMap<Integer, String> hasMap) {
        if (hasMap != null) {
            isFirst = true;
            list.clear();
            EditText[] price = new EditText[7];
            EditText[] name = new EditText[7];
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
                        name[i] = (EditText) layout[i].findViewById(R.id.et_name);
                        RelativeLayout rl_biling_type = (RelativeLayout) layout[i].findViewById(R.id.rl_biling_type);
                        RelativeLayout rl_add_case = (RelativeLayout) layout[i].findViewById(R.id.rl_add_case);
                        RelativeLayout rl_invitation = (RelativeLayout) layout[i].findViewById(R.id.rl_invitation);
                        TextView tv_case_type = (TextView) layout[i].findViewById(R.id.tv_case_type);
                        biling_type[i] = (TextView) layout[i].findViewById(R.id.tv_biling_type);
                        add_item[i] = (LinearLayout) layout[i].findViewById(R.id.add_item);
                        tv_case_type.setText(strCase[i - 1]);
                        rl_to_time.setId(100 + i);
                        rl_biling_type.setId(110 + i);
                        rl_invitation.setId(120 + i);
                        rl_add_case.setId(130 + i);
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
                            name[i] = (EditText) layout[i].findViewById(R.id.et_name);
                            RelativeLayout rl_biling_type = (RelativeLayout) layout[i].findViewById(R.id.rl_biling_type);
                            RelativeLayout rl_add_case = (RelativeLayout) layout[i].findViewById(R.id.rl_add_case);
                            RelativeLayout rl_invitation = (RelativeLayout) layout[i].findViewById(R.id.rl_invitation);
                            TextView tv_case_type = (TextView) layout[i].findViewById(R.id.tv_case_type);
                            biling_type[i] = (TextView) layout[i].findViewById(R.id.tv_biling_type);
                            add_item[i] = (LinearLayout) layout[i].findViewById(R.id.add_item);
                            tv_case_type.setText(strCase[i - 1]);
                            rl_to_time.setId(100 + i);
                            rl_biling_type.setId(110 + i);
                            rl_invitation.setId(120 + i);
                            rl_add_case.setId(130 + i);
                            rl_to_time.setOnClickListener(this);
                            rl_biling_type.setOnClickListener(this);
                            rl_invitation.setOnClickListener(this);
                            rl_add_case.setOnClickListener(this);

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
}
