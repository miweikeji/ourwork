package app.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import java.util.List;

import app.dialog.DialogTools;
import app.entity.HousesByLyf;
import app.entity.Journal;
import app.entity.JournalResult;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/10/18.
 */
public class ReservationDetailsActivity extends BaseActivity implements
        NavigationBar.RightOnClick, View.OnClickListener, DialogTools.DialogOnClickListens {


    private HousesByLyf lyf;
    private RelativeLayout rl_msg_hint;
    private TextView tv_creat_time;
    private TextView tv_status_;
    private TextView tv_content;
    private TextView tv_time;
    private String name;
    private String from;
    private Button btn_hint;
    private LinearLayout linear_is_show;
    private String houseId;
    private String craftsId;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        from = intent.getStringExtra("FromeActvity");
        lyf = (HousesByLyf) intent.getSerializableExtra("ReservationDetailsActivity");
        name = lyf.getCrafts_name();
         houseId = lyf.getHouse_id();
        craftsId = lyf.getCrafts_id();

        RelativeLayout rl_house = (RelativeLayout) findViewById(R.id.rl_house);
        rl_msg_hint = (RelativeLayout) findViewById(R.id.rl_msg_hint);
        rl_house.setOnClickListener(this);
        Button btn_refuse = (Button)findViewById(R.id.btn_refuse);
        Button btn_sure = (Button)findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(this);
        btn_refuse.setOnClickListener(this);
        btn_hint = (Button) findViewById(R.id.btn_hint);
        linear_is_show = (LinearLayout) findViewById(R.id.linear_is_show);

        tv_creat_time = (TextView)findViewById(R.id.tv_creat_time);
         tv_status_ = (TextView)findViewById(R.id.tv_status_);
         tv_content = (TextView)findViewById(R.id.tv_content);
         tv_time = (TextView) findViewById(R.id.tv_time);

        TextView tv_name= (TextView)findViewById(R.id.tv_name);
        TextView tv_status= (TextView)findViewById(R.id.tv_status);
        TextView tv_type= (TextView)findViewById(R.id.tv_type);
        TextView tv_area= (TextView)findViewById(R.id.tv_area);
        TextView tv_style= (TextView)findViewById(R.id.tv_style);
        TextView tv_mode= (TextView)findViewById(R.id.tv_mode);
        TextView tv_total_price= (TextView)findViewById(R.id.tv_total_price);
        tv_name.setText(lyf.getHouse_name());
        tv_type.setText(" "+lyf.getHouse_type()+" |");
        tv_area.setText(" "+lyf.getHouse_area()+"平 |");
        tv_style.setText(" "+lyf.getHouse_style()+" |");
        tv_mode.setText(" "+lyf.getHouse_craft_mode());
        tv_total_price.setText("￥"+lyf.getHouse_total_price());
        tv_status.setText(lyf.getStats_des());
//        tv_time.setText(lyf.getLftime());
//        tv_creat_time.setText(lyf.getAddtime());
        String type = lyf.getType();
        String state = lyf.getState();
        if("1".equals(state)||"2".equals(state)){

        }else if("3".equals(state)){
            tv_status_.setText("量房");
        }else if("4".equals(state)){
            tv_status_.setText("量房");
        }else if("5".equals(state)){
            tv_status_.setText("婉拒");
//            tv_content.setText();
            btn_sure.setVisibility(View.GONE);
        }
        if("ReservationHistoryActivity".equals(from)){
            if("3".equals(lyf.getState())||"4".equals(lyf.getState())) {
                rl_msg_hint.setVisibility(View.VISIBLE);
                linear_is_show.setVisibility(View.INVISIBLE);
                getJournalAccept();
            }else if("5".equals(lyf.getState())){
                rl_msg_hint.setVisibility(View.VISIBLE);
                getJournalRefuse();
                btn_hint.setVisibility(View.VISIBLE);
                linear_is_show.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_reservation_details;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("预约详情");
        mBar.setRightImg(R.mipmap.phone);
        mBar.setRightOnClick(this);
    }

    @Override
    public void setRightOnClick() {
        if(lyf!=null){
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + lyf.getOwner_phone())));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_refuse:
                DialogTools.refuseShow(this).show();
                DialogTools.setDialogOnClick(this);
                break;
            case R.id.btn_sure:
                accept();
                break;
            case R.id.rl_house:
                startActivity(new Intent(this,HouseActivity.class));
                break;
        }
    }

    private void accept() {

        String houseId = "753665";
        String ownerId = "458763";
        String craftsId = "100";
        String craftsName = "思明";
        String yyTime = "2015-10-22";
        HttpRequest.acceptAppointmentLyf(ReservationDetailsActivity.this, houseId,
                ownerId, craftsId, craftsName, yyTime, new ICallback<Meta>() {
                    @Override
                    public void onSucceed(Meta result) {
                        Uihelper.showToast(ReservationDetailsActivity.this, result.getMsg());
                        rl_msg_hint.setVisibility(View.VISIBLE);
                        getJournalAccept();
                    }

                    @Override
                    public void onFail(String error) {
                        Uihelper.showToast(ReservationDetailsActivity.this, error);
                    }
                });

    }

    private void getJournalAccept() {
        HttpRequest.getYYDialyLog(ReservationDetailsActivity.this, houseId,
                craftsId, new ICallback<JournalResult>() {
                    @Override
                    public void onSucceed(JournalResult result) {
                        Uihelper.showToast(ReservationDetailsActivity.this, result.getMsg());
                        List<Journal> dialy = result.getDialy();
                        Journal journal = dialy.get(0);
                        tv_creat_time.setText(journal.getAddtime());

                        tv_status_.setText(journal.getTitle());
                        if (!"ReservationHistoryActivity".equals(from)) {
                            if (journal.getTitle_state().equals("6")) {
                                tv_content.setText("已经为您的" + lyf.getHouse_type() + "房子免费预约带班班长（" + name + "）上门量房");
                                tv_time.setText("预约量房完成时间为：" + journal.getLftime());
                            } else if (journal.getTitle_state().equals("8")) {
                                tv_content.setText("您的" + lyf.getHouse_type() + "房子已经有带班班长（" + name + "）完成上门量房");
                                tv_time.setText("量房完成时间为：" + journal.getLftime());
                            }
                        } else {
                            if (journal.getTitle_state().equals("3")) {
                                tv_content.setText("已经为您的" + lyf.getHouse_type() + "房子免费预约带班班长（" + name + "）上门量房");
                                tv_time.setText("预约量房完成时间为：" + journal.getLftime());
                            } else if (journal.getTitle_state().equals("4")) {
                                tv_content.setText("您的" + lyf.getHouse_type() + "房子已经有带班班长（" + name + "）完成上门量房");
                                tv_time.setText("量房完成时间为：" + journal.getLftime());
                            }
                        }

                    }

                    @Override
                    public void onFail(String error) {
                        Uihelper.showToast(ReservationDetailsActivity.this, error);
                    }
                });


    }

    @Override
    public void onDialogClick() {
        //确定婉拒
        refuse();
    }

    private void refuse() {
        String houseId = "753666";
        String ownerId = "425988";
        String craftsId = "100";
        String craftsName = "思明";
        String yyTime = "2015-10-22";
        HttpRequest.refuseAppointmentLyf(ReservationDetailsActivity.this, houseId,
                ownerId, craftsId, craftsName, yyTime, new ICallback<Meta>() {
                    @Override
                    public void onSucceed(Meta result) {
                        Uihelper.showToast(ReservationDetailsActivity.this, result.getMsg());
                        rl_msg_hint.setVisibility(View.VISIBLE);
                        getJournalRefuse();
                    }

                    @Override
                    public void onFail(String error) {
                        Uihelper.showToast(ReservationDetailsActivity.this, error);
                    }
                });
    }

    private void getJournalRefuse() {
        HttpRequest.getRefuseYYDialyLog(ReservationDetailsActivity.this, houseId,
                craftsId, new ICallback<JournalResult>() {
                    @Override
                    public void onSucceed(JournalResult result) {
                        Uihelper.showToast(ReservationDetailsActivity.this, result.getMsg());
                        List<Journal> dialy = result.getDialy();
                        Journal journal = dialy.get(0);
                        tv_creat_time.setText(journal.getAddtime());
                        tv_status_.setText(journal.getTitle());
                        tv_content.setText("带班班长"+name+"由于目前手头上的项目比较多，无法安排施工，十分抱歉婉拒您的预约。");
                        tv_time.setVisibility(View.GONE);
                        btn_hint.setVisibility(View.VISIBLE);
                        linear_is_show.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFail(String error) {
                        Uihelper.showToast(ReservationDetailsActivity.this, error);
                    }
                });

    }
}
