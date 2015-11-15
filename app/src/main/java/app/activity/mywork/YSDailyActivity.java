package app.activity.mywork;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.miwei.jzj_system.R;

import java.util.Calendar;

import app.activity.BaseActivity;
import app.entity.HouseInfo;
import app.entity.HouseInfo_Bundle;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.popupwindow.WorkTypePopup;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/11.
 */
public class YSDailyActivity extends BaseActivity implements View.OnClickListener, WorkTypePopup.OnPopupWindowClickListener {
    private RelativeLayout rl_work_yycase;
    private TextView tv_work_yycase;
    private TextView tv_time;
    private RelativeLayout rl_time_choose;
    String[] items = {"水电隐蔽验收", "防水工程验收 ", "木作隐蔽验收", "中期验收 ", "竣工验收", "量房", "婉拒", "已量房"};
    private String house_state;
    private DatePickerDialog datePicker;
    private String summitTime;
    private HouseInfo_Bundle houseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        houseInfo = (HouseInfo_Bundle) intent.getSerializableExtra("houseInfo");

        super.onCreate(savedInstanceState);
    }

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        rl_work_yycase = (RelativeLayout) findViewById(R.id.rl_work_yycase);
        tv_work_yycase = (TextView) findViewById(R.id.tv_work_yycase);

        rl_time_choose = (RelativeLayout) findViewById(R.id.rl_time_choose);
        tv_time = (TextView) findViewById(R.id.tv_time);
        Button btn_complete = (Button) findViewById(R.id.btn_complete);

        rl_work_yycase.setOnClickListener(this);
        rl_time_choose.setOnClickListener(this);
        btn_complete.setOnClickListener(this);

    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_yydairy;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("预约验收");
    }

    private void ShowBirthdayTime() {
        // TODO Auto-generated method stub
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        if (datePicker == null) {
            datePicker = new DatePickerDialog(
                    mActivity, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    summitTime = year + "-" + (monthOfYear + 1) + "-"
                            + dayOfMonth;
                    String time = year + "年" + (monthOfYear + 1) + "月"
                            + dayOfMonth + "日";
                    tv_time.setText(time);
                }
            }, year, month, date);
        }
        datePicker.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_time_choose:
                ShowBirthdayTime();
                break;
            case R.id.rl_work_yycase:
                WorkTypePopup popup = new WorkTypePopup(this, items);
                popup.showAsDropDown(v);
                popup.setOnPopupWindowClickListener(this);
                break;
            case R.id.btn_complete:

                if (TextUtils.isEmpty(house_state)) {
                    Uihelper.showToast(mActivity, "请选择验收项目");
                    return;
                }
                if (TextUtils.isEmpty(summitTime)) {
                    Uihelper.showToast(mActivity, "请选择验收时间");
                    return;
                }
                if (houseInfo == null) {
                    return;
                }
                summit();
                break;
            default:
                break;
        }
    }

    private void summit() {
        showWaitingDialog();
        HttpRequest.addYSDailyLog(mActivity, "100", "", houseInfo.getStatus(), houseInfo.getId(), houseInfo.getOwnerId()
                , summitTime, house_state, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "成功发送预约验收");
                finish();

            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);
            }
        });
    }

    @Override
    public void onPopupWindowItemClick(int position) {
        house_state = (position + 1) + "";
        tv_work_yycase.setText(items[position]);
    }
}
