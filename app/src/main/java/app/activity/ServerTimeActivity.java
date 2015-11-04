package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.entity.Meta;
import app.entity.Name;
import app.entity.Time;
import app.tools.MyLog;
import app.tools.TimeTools;
import app.tools.UIEventUpdate;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ServerTimeActivity extends BaseActivity implements View.OnClickListener {

    private  int time_day;
    private String case_type;
    private TextView tv_time1;
    private TextView tv_time2;
    private TextView tv_time3;
    private TextView tv_time4;
    private TextView tv_time5;
    private TextView tv_time6;
    private TextView tv_time7;
    private ImageView img_am1;
    private ImageView img_am2;
    private ImageView img_am3;
    private ImageView img_am4;
    private ImageView img_am5;
    private ImageView img_am6;
    private ImageView img_am7;
    private ImageView img_pm1;
    private ImageView img_pm2;
    private ImageView img_pm3;
    private ImageView img_pm4;
    private ImageView img_pm5;
    private ImageView img_pm6;
    private ImageView img_pm7;
    private int item1,item2,item3,item4,item5,item6,item7;
    private boolean isItem1l;
    private boolean isItem1r;
    private boolean isItem2l;
    private boolean isItem2r;
    private boolean isItem3l;
    private boolean isItem3r;
    private boolean isItem4l;
    private boolean isItem4r;
    private boolean isItem5l;
    private boolean isItem5r;
    private boolean isItem6l;
    private boolean isItem6r;
    private boolean isItem7l;
    private boolean isItem7r;

    private RelativeLayout rl_1_left;
    private RelativeLayout rl_2_left;
    private RelativeLayout rl_3_left;
    private RelativeLayout rl_4_left;
    private RelativeLayout rl_5_left;
    private RelativeLayout rl_6_left;
    private RelativeLayout rl_7_left;


    private RelativeLayout rl_1_right;
    private RelativeLayout rl_2_right;
    private RelativeLayout rl_3_right;
    private RelativeLayout rl_4_right;
    private RelativeLayout rl_5_right;
    private RelativeLayout rl_6_right;
    private RelativeLayout rl_7_right;





    private int flage;
    private int maxIsTrue;
    private boolean isStopUp;

    private int group;
    private int mark;
    private Date d1;
    private int remainder;
    private HashMap<Integer,List<List<String>>> hasPage = new HashMap<>();
//    private List<String> itemInfo = new ArrayList<>();


    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        Intent intent = getIntent();
        time_day = intent.getIntExtra("time_day", 0)-1;
        case_type = intent.getStringExtra("CASE_TYPE");

        if(time_day<8){
            group=1;
            List<List<String>> pageList = new ArrayList<>();
            for (int j=0;j<7;j++){
                List<String> itemInfo = new ArrayList<>();
                pageList.add(itemInfo);
            }
            hasPage.put(0,pageList);
        }else {
            group=  time_day/7;
            remainder=time_day%7;
            group++;

            for (int i=0;i<group;i++){
                List<List<String>> pageList = new ArrayList<>();
                for (int j=0;j<7;j++){
                    List<String> itemInfo = new ArrayList<>();
                    pageList.add(itemInfo);
                }
                hasPage.put(i,pageList);
            }
        }

        tv_time1 = (TextView) findViewById(R.id.tv_time1);
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        tv_time3 = (TextView) findViewById(R.id.tv_time3);
        tv_time4 = (TextView) findViewById(R.id.tv_time4);
        tv_time5 = (TextView) findViewById(R.id.tv_time5);
        tv_time6 = (TextView) findViewById(R.id.tv_time6);
        tv_time7 = (TextView) findViewById(R.id.tv_time7);
         img_am1 = (ImageView) findViewById(R.id.img_am1);
         img_am2 = (ImageView) findViewById(R.id.img_am2);
         img_am3 = (ImageView) findViewById(R.id.img_am3);
         img_am4 = (ImageView) findViewById(R.id.img_am4);
         img_am5 = (ImageView) findViewById(R.id.img_am5);
         img_am6 = (ImageView) findViewById(R.id.img_am6);
         img_am7 = (ImageView) findViewById(R.id.img_am7);

         img_pm1 = (ImageView) findViewById(R.id.img_pm1);
         img_pm2 = (ImageView) findViewById(R.id.img_pm2);
         img_pm3 = (ImageView) findViewById(R.id.img_pm3);
         img_pm4 = (ImageView) findViewById(R.id.img_pm4);
         img_pm5 = (ImageView) findViewById(R.id.img_pm5);
         img_pm6 = (ImageView) findViewById(R.id.img_pm6);
         img_pm7 = (ImageView) findViewById(R.id.img_pm7);

         rl_1_left = (RelativeLayout) findViewById(R.id.rl_1_left);
         rl_2_left = (RelativeLayout) findViewById(R.id.rl_2_left);
         rl_3_left = (RelativeLayout) findViewById(R.id.rl_3_left);
         rl_4_left = (RelativeLayout) findViewById(R.id.rl_4_left);
         rl_5_left = (RelativeLayout) findViewById(R.id.rl_5_left);
         rl_6_left = (RelativeLayout) findViewById(R.id.rl_6_left);
         rl_7_left = (RelativeLayout) findViewById(R.id.rl_7_left);

         rl_1_right = (RelativeLayout) findViewById(R.id.rl_1_right);
         rl_2_right = (RelativeLayout) findViewById(R.id.rl_2_right);
         rl_3_right = (RelativeLayout) findViewById(R.id.rl_3_right);
         rl_4_right = (RelativeLayout) findViewById(R.id.rl_4_right);
         rl_5_right = (RelativeLayout) findViewById(R.id.rl_5_right);
         rl_6_right = (RelativeLayout) findViewById(R.id.rl_6_right);
         rl_7_right = (RelativeLayout) findViewById(R.id.rl_7_right);

        rl_1_left.setOnClickListener(this);
        rl_2_left.setOnClickListener(this);
        rl_3_left.setOnClickListener(this);
        rl_4_left.setOnClickListener(this);
        rl_5_left.setOnClickListener(this);
        rl_6_left.setOnClickListener(this);
        rl_7_left.setOnClickListener(this);

        rl_1_right.setOnClickListener(this);
        rl_2_right.setOnClickListener(this);
        rl_3_right.setOnClickListener(this);
        rl_4_right.setOnClickListener(this);
        rl_5_right.setOnClickListener(this);
        rl_6_right.setOnClickListener(this);
        rl_7_right.setOnClickListener(this);


        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String    date    =    sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1=sdf.parse(date);
            if(time_day<8){
                if(time_day==1){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    rl_2_left.setEnabled(false);
                    rl_2_right.setEnabled(false);
                    rl_3_left.setEnabled(false);
                    rl_3_right.setEnabled(false);
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==2){
                    tv_time1.setText(TimeTools.getDayTime(d1, 1));
                    tv_time2.setText(TimeTools.getDayTime(d1, 2));
                    rl_3_left.setEnabled(false);
                    rl_3_right.setEnabled(false);
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==3){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==4){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==5){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==6){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(time_day==7){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                    tv_time7.setText(TimeTools.getDayTime(d1,7));
                }
            }else {
                tv_time1.setText(TimeTools.getDayTime(d1,1));
                tv_time2.setText(TimeTools.getDayTime(d1,2));
                tv_time3.setText(TimeTools.getDayTime(d1,3));
                tv_time4.setText(TimeTools.getDayTime(d1,4));
                tv_time5.setText(TimeTools.getDayTime(d1,5));
                tv_time6.setText(TimeTools.getDayTime(d1,6));
                tv_time7.setText(TimeTools.getDayTime(d1,7));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_server_time;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("服务时间");

    }



    public void up(View v){
        if(mark!=0){
            mark--;
            reset();
            getPageInfo();
            setCanOnCklic();
        }else {
            setCanOnCklic();
            Uihelper.showToast(this,"已经在第一页了");
        }

    }

    public void down(View v){
        if(mark<group-1&&group>1){
            maxIsTrue=0;
            savePageInfo();
            mark++;
            reset();
            if(mark<maxIsTrue){
                if(mark+1<maxIsTrue){
                    getPageInfo();
                }else {
                    getLestPageInfo();
                }
            }

            than7(mark);



        }else {
            if(group==1){
                Uihelper.showToast(this, "只有一页");
            }else {
                Uihelper.showToast(this, "已经最后一页了");
            }
//            savePageInfo();
        }
    }

    private void getLestPageInfo() {



        List<List<String>> lists = hasPage.get(mark);
        if(lists.get(0).get(0).length()>10){
            String s1 = lists.get(0).get(0).substring(lists.get(0).get(0).length() - 13, lists.get(0).get(0).length());
            tv_time1.setText(s1.substring(0,s1.length()-2));
            if("1".equals(s1.substring(s1.length()-2,s1.length()-1))){
                img_am1.setVisibility(View.VISIBLE);
            }else {
                img_am1.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s1.substring(s1.length()-1,s1.length()))){
                img_pm1.setVisibility(View.VISIBLE);
            }else {
                img_pm1.setVisibility(View.INVISIBLE);
            }
        }

        if(lists.get(1).get(0).length()>10){
            String s2 = lists.get(1).get(0).substring(lists.get(1).get(0).length() - 13, lists.get(1).get(0).length());
            tv_time2.setText(s2.substring(0,s2.length()-2));
            if("1".equals(s2.substring(s2.length()-2,s2.length()-1))){
                img_am2.setVisibility(View.VISIBLE);
            }else {
                img_am2.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s2.substring(s2.length()-1,s2.length()))){
                img_pm2.setVisibility(View.VISIBLE);
            }else {
                img_pm2.setVisibility(View.INVISIBLE);
            }
        }

        if(lists.get(2).get(0).length()>10){
            String s3 = lists.get(2).get(0).substring(lists.get(2).get(0).length() - 13, lists.get(2).get(0).length());
            tv_time3.setText(s3.substring(0,s3.length()-2));
            if("1".equals(s3.substring(s3.length()-2,s3.length()-1))){
                img_am3.setVisibility(View.VISIBLE);
            }else {
                img_am3.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s3.substring(s3.length()-1,s3.length()))){
                img_pm3.setVisibility(View.VISIBLE);
            }else {
                img_pm3.setVisibility(View.INVISIBLE);
            }
        }

        if(lists.get(3).get(0).length()>10){
            String s4 = lists.get(3).get(0).substring(lists.get(3).get(0).length() - 13, lists.get(3).get(0).length());
            tv_time4.setText(s4.substring(0,s4.length()-2));
            if("1".equals(s4.substring(s4.length()-2,s4.length()-1))){
                img_am4.setVisibility(View.VISIBLE);
            }else {
                img_am4.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s4.substring(s4.length()-1,s4.length()))){
                img_pm4.setVisibility(View.VISIBLE);
            }else {
                img_pm4.setVisibility(View.INVISIBLE);
            }
        }

        if( lists.get(4).get(0).length()>10){

            String s5 = lists.get(4).get(0).substring(lists.get(4).get(0).length() - 13, lists.get(4).get(0).length());
            tv_time5.setText(s5.substring(0,s5.length()-2));
            if("1".equals(s5.substring(s5.length()-2,s5.length()-1))){
                img_am5.setVisibility(View.VISIBLE);
            }else {
                img_am5.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s5.substring(s5.length()-1,s5.length()))){
                img_pm5.setVisibility(View.VISIBLE);
            }else {
                img_pm5.setVisibility(View.INVISIBLE);
            }
        }

        if(lists.get(5).get(0).length()>10){

            String s6 = lists.get(5).get(0).substring(lists.get(5).get(0).length() - 13, lists.get(5).get(0).length());
            tv_time6.setText(s6.substring(0,s6.length()-2));
            if("1".equals(s6.substring(s6.length()-2,s6.length()-1))){
                img_am6.setVisibility(View.VISIBLE);
            }else {
                img_am6.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s6.substring(s6.length()-1,s6.length()))){
                img_pm6.setVisibility(View.VISIBLE);
            }else {
                img_pm6.setVisibility(View.INVISIBLE);
            }
        }

        if(lists.get(6).get(0).length()>10){

            String s7 = lists.get(6).get(0).substring(lists.get(6).get(0).length() - 13, lists.get(6).get(0).length());
            tv_time7.setText(s7.substring(0,s7.length()-2));
            if("1".equals(s7.substring(s7.length()-2,s7.length()-1))){
                img_am7.setVisibility(View.VISIBLE);
            }else {
                img_am7.setVisibility(View.INVISIBLE);
            }

            if("1".equals(s7.substring(s7.length()-1,s7.length()))){
                img_pm7.setVisibility(View.VISIBLE);
            }else {
                img_pm7.setVisibility(View.INVISIBLE);
            }
        }



    }

    @Override
    public void onClick(View v) {
        List<List<String>> lists = hasPage.get(mark);
        flage = mark;
        switch (v.getId()){
            case R.id.rl_1_left:
                isItem1l=!isItem1l;
//                List<String> list1 = lists.get(0);
                if(isItem1l){
                    img_am1.setVisibility(View.VISIBLE);
                }else {
                    img_am1.setVisibility(View.INVISIBLE);
                }

                break;
            case R.id.rl_2_left:
                isItem2l=!isItem2l;
                if(isItem2l){
                    img_am2.setVisibility(View.VISIBLE);
                }else {
                    img_am2.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_3_left:
                isItem3l=!isItem3l;
                if(isItem3l){
                    img_am3.setVisibility(View.VISIBLE);
                }else {
                    img_am3.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_4_left:
                isItem4l=!isItem4l;
                if(isItem4l){
                    img_am4.setVisibility(View.VISIBLE);
                }else {
                    img_am4.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_5_left:
                isItem5l=!isItem5l;
                if(isItem5l){
                    img_am5.setVisibility(View.VISIBLE);
                }else {
                    img_am5.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_6_left:
                isItem6l=!isItem6l;
                if(isItem6l){
                    img_am6.setVisibility(View.VISIBLE);
                }else {
                    img_am6.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_7_left:
                isItem7l=!isItem7l;
                if(isItem7l){
                    img_am7.setVisibility(View.VISIBLE);
                }else {
                    img_am7.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_1_right:
                isItem1r=!isItem1r;
                if(isItem1r){
                    img_pm1.setVisibility(View.VISIBLE);
                }else {
                    img_pm1.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_2_right:
                isItem2r=!isItem2r;
                if(isItem2r){
                    img_pm2.setVisibility(View.VISIBLE);
                }else {
                    img_pm2.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_3_right:
                isItem3r=!isItem3r;
                if(isItem3r){
                    img_pm3.setVisibility(View.VISIBLE);
                }else {
                    img_pm3.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_4_right:
                isItem4r=!isItem4r;
                if(isItem4r){
                    img_pm4.setVisibility(View.VISIBLE);
                }else {
                    img_pm4.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_5_right:
                isItem5r=!isItem5r;
                if(isItem5r){
                    img_pm5.setVisibility(View.VISIBLE);
                }else {
                    img_pm5.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_6_right:
                isItem6r=!isItem6r;
                if(isItem6r){
                    img_pm6.setVisibility(View.VISIBLE);
                }else {
                    img_pm6.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.rl_7_right:
                isItem7r=!isItem7r;
                if(isItem7r){
                    img_pm7.setVisibility(View.VISIBLE);
                }else {
                    img_pm7.setVisibility(View.INVISIBLE);
                }
                break;
        }

        if(mark+1==group){
            saveLastPageInfo();
        }
    }

    private void getPageInfo(){
        List<List<String>> lists = hasPage.get(mark);
        String s1 = lists.get(0).get(0).substring(lists.get(0).get(0).length() - 13, lists.get(0).get(0).length());
        tv_time1.setText(s1.substring(0,s1.length()-2));
        if("1".equals(s1.substring(s1.length()-2,s1.length()-1))){
            img_am1.setVisibility(View.VISIBLE);
        }else {
            img_am1.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s1.substring(s1.length()-1,s1.length()))){
            img_pm1.setVisibility(View.VISIBLE);
        }else {
            img_pm1.setVisibility(View.INVISIBLE);
        }

        String s2 = lists.get(1).get(0).substring(lists.get(1).get(0).length() - 13, lists.get(1).get(0).length());
        tv_time2.setText(s2.substring(0,s2.length()-2));
        if("1".equals(s2.substring(s2.length()-2,s2.length()-1))){
            img_am2.setVisibility(View.VISIBLE);
        }else {
            img_am2.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s2.substring(s2.length()-1,s2.length()))){
            img_pm2.setVisibility(View.VISIBLE);
        }else {
            img_pm2.setVisibility(View.INVISIBLE);
        }

        String s3 = lists.get(2).get(0).substring(lists.get(2).get(0).length() - 13, lists.get(2).get(0).length());
        tv_time3.setText(s3.substring(0,s3.length()-2));
        if("1".equals(s3.substring(s3.length()-2,s3.length()-1))){
            img_am3.setVisibility(View.VISIBLE);
        }else {
            img_am3.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s3.substring(s3.length()-1,s3.length()))){
            img_pm3.setVisibility(View.VISIBLE);
        }else {
            img_pm3.setVisibility(View.INVISIBLE);
        }

        String s4 = lists.get(3).get(0).substring(lists.get(3).get(0).length() - 13, lists.get(3).get(0).length());
        tv_time4.setText(s4.substring(0,s4.length()-2));
        if("1".equals(s4.substring(s4.length()-2,s4.length()-1))){
            img_am4.setVisibility(View.VISIBLE);
        }else {
            img_am4.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s4.substring(s4.length()-1,s4.length()))){
            img_pm4.setVisibility(View.VISIBLE);
        }else {
            img_pm4.setVisibility(View.INVISIBLE);
        }

        String s5 = lists.get(4).get(0).substring(lists.get(4).get(0).length() - 13, lists.get(4).get(0).length());
        tv_time5.setText(s5.substring(0,s5.length()-2));
        if("1".equals(s5.substring(s5.length()-2,s5.length()-1))){
            img_am5.setVisibility(View.VISIBLE);
        }else {
            img_am5.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s5.substring(s5.length()-1,s5.length()))){
            img_pm5.setVisibility(View.VISIBLE);
        }else {
            img_pm5.setVisibility(View.INVISIBLE);
        }

        String s6 = lists.get(5).get(0).substring(lists.get(5).get(0).length() - 13, lists.get(5).get(0).length());
        tv_time6.setText(s6.substring(0,s6.length()-2));
        if("1".equals(s6.substring(s6.length()-2,s6.length()-1))){
            img_am6.setVisibility(View.VISIBLE);
        }else {
            img_am6.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s6.substring(s6.length()-1,s6.length()))){
            img_pm6.setVisibility(View.VISIBLE);
        }else {
            img_pm6.setVisibility(View.INVISIBLE);
        }

        String s7 = lists.get(6).get(0).substring(lists.get(6).get(0).length() - 13, lists.get(6).get(0).length());
        tv_time7.setText(s7.substring(0,s7.length()-2));
        if("1".equals(s7.substring(s7.length()-2,s7.length()-1))){
            img_am7.setVisibility(View.VISIBLE);
        }else {
            img_am7.setVisibility(View.INVISIBLE);
        }

        if("1".equals(s7.substring(s7.length()-1,s7.length()))){
            img_pm7.setVisibility(View.VISIBLE);
        }else {
            img_pm7.setVisibility(View.INVISIBLE);
        }
    }

    private void saveLastPageInfo(){
        List<List<String>> lists = hasPage.get(mark);


        String s1 = tv_time1.getText().toString();
        if(View.VISIBLE==img_am1.getVisibility()){
            s1=s1+1;
        }else {
            s1=s1+0;
        }
        if(View.VISIBLE==img_pm1.getVisibility()){
            s1=s1+1;
        }else {
            s1=s1+0;
        }
        List<String> l1 = lists.get(0);
        l1.clear();
        l1.add(s1);

        String s2 = tv_time2.getText().toString();
        if(View.VISIBLE==img_am2.getVisibility()){
            s2=s2+1;
        }else {
            s2=s2+0;
        }
        if(View.VISIBLE==img_pm2.getVisibility()){
            s2=s2+1;
        }else {
            s2=s2+0;
        }
        List<String> l2 = lists.get(1);
        l2.clear();
        l2.add(s2);

        String s3 = tv_time3.getText().toString();
        if(View.VISIBLE==img_am3.getVisibility()){
            s3=s3+1;
        }else {
            s3=s3+0;
        }
        if(View.VISIBLE==img_pm3.getVisibility()){
            s3=s3+1;
        }else {
            s3=s3+0;
        }
        List<String> l3 = lists.get(2);
        l3.clear();
        l3.add(s3);

        String s4 = tv_time4.getText().toString();
        if(View.VISIBLE==img_am4.getVisibility()){
            s4=s4+1;
        }else {
            s4=s4+0;
        }
        if(View.VISIBLE==img_pm4.getVisibility()){
            s4=s4+1;
        }else {
            s4=s4+0;
        }
        List<String> l4 = lists.get(3);
        l4.clear();
        l4.add(s4);

        String s5 = tv_time5.getText().toString();
        if(View.VISIBLE==img_am5.getVisibility()){
            s5=s5+1;
        }else {
            s5=s5+0;
        }
        if(View.VISIBLE==img_pm5.getVisibility()){
            s5=s5+1;
        }else {
            s5=s5+0;
        }
        List<String> l5 = lists.get(4);
        l5.clear();
        l5.add(s5);

        String s6 = tv_time6.getText().toString();
        if(View.VISIBLE==img_am6.getVisibility()){
            s6=s6+1;
        }else {
            s6=s6+0;
        }
        if(View.VISIBLE==img_pm6.getVisibility()){
            s6=s6+1;
        }else {
            s6=s6+0;
        }
        List<String> l6 = lists.get(5);
        l6.clear();
        l6.add(s6);

        String s7 = tv_time7.getText().toString();
        if(View.VISIBLE==img_am7.getVisibility()){
            s7=s7+1;
        }else {
            s7=s7+0;
        }
        if(View.VISIBLE==img_pm7.getVisibility()){
            s7=s7+1;
        }else {
            s7=s7+0;
        }
        List<String> l7 = lists.get(6);
        l7.clear();
        l7.add(s7);

        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);
        lists.add(l5);
        lists.add(l6);
        lists.add(l7);
        hasPage.put(mark,lists);

        for (int i=0;i<hasPage.size();i++){
            if(hasPage.get(i).get(0).size()>0){
//                maxIsTrue++;
            }
        }
    }

    private void savePageInfo(){
        List<List<String>> lists = hasPage.get(mark);


        String s1 = tv_time1.getText().toString();
        if(View.VISIBLE==img_am1.getVisibility()){
            s1=s1+1;
        }else {
            s1=s1+0;
        }
        if(View.VISIBLE==img_pm1.getVisibility()){
            s1=s1+1;
        }else {
            s1=s1+0;
        }
        List<String> l1 = lists.get(0);
        l1.clear();
        l1.add(s1);

        String s2 = tv_time2.getText().toString();
        if(View.VISIBLE==img_am2.getVisibility()){
            s2=s2+1;
        }else {
            s2=s2+0;
        }
        if(View.VISIBLE==img_pm2.getVisibility()){
            s2=s2+1;
        }else {
            s2=s2+0;
        }
        List<String> l2 = lists.get(1);
        l2.clear();
        l2.add(s2);

        String s3 = tv_time3.getText().toString();
        if(View.VISIBLE==img_am3.getVisibility()){
            s3=s3+1;
        }else {
            s3=s3+0;
        }
        if(View.VISIBLE==img_pm3.getVisibility()){
            s3=s3+1;
        }else {
            s3=s3+0;
        }
        List<String> l3 = lists.get(2);
        l3.clear();
        l3.add(s3);

        String s4 = tv_time4.getText().toString();
        if(View.VISIBLE==img_am4.getVisibility()){
            s4=s4+1;
        }else {
            s4=s4+0;
        }
        if(View.VISIBLE==img_pm4.getVisibility()){
            s4=s4+1;
        }else {
            s4=s4+0;
        }
        List<String> l4 = lists.get(3);
        l4.clear();
        l4.add(s4);

        String s5 = tv_time5.getText().toString();
        if(View.VISIBLE==img_am5.getVisibility()){
            s5=s5+1;
        }else {
            s5=s5+0;
        }
        if(View.VISIBLE==img_pm5.getVisibility()){
            s5=s5+1;
        }else {
            s5=s5+0;
        }
        List<String> l5 = lists.get(4);
        l5.clear();
        l5.add(s5);

        String s6 = tv_time6.getText().toString();
        if(View.VISIBLE==img_am6.getVisibility()){
            s6=s6+1;
        }else {
            s6=s6+0;
        }
        if(View.VISIBLE==img_pm6.getVisibility()){
            s6=s6+1;
        }else {
            s6=s6+0;
        }
        List<String> l6 = lists.get(5);
        l6.clear();
        l6.add(s6);

        String s7 = tv_time7.getText().toString();
        if(View.VISIBLE==img_am7.getVisibility()){
            s7=s7+1;
        }else {
            s7=s7+0;
        }
        if(View.VISIBLE==img_pm7.getVisibility()){
            s7=s7+1;
        }else {
            s7=s7+0;
        }
        List<String> l7 = lists.get(6);
        l7.clear();
        l7.add(s7);

        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);
        lists.add(l5);
        lists.add(l6);
        lists.add(l7);
        hasPage.put(mark,lists);

        for (int i=0;i<hasPage.size();i++){
            if(hasPage.get(i).get(0).size()>0){
                maxIsTrue++;
            }
        }
    }

    private void reset(){

          isItem1l=false;
          isItem1r=false;
          isItem2l=false;
          isItem2r=false;
          isItem3l=false;
          isItem3r=false;
          isItem4l=false;
          isItem4r=false;
          isItem5l=false;
          isItem5r=false;
          isItem6l=false;
          isItem6r=false;
          isItem7l=false;
          isItem7r=false;


        tv_time1.setText("");
        tv_time2.setText("");
        tv_time3.setText("");
        tv_time4.setText("");
        tv_time5.setText("");
        tv_time6.setText("");
        tv_time7.setText("");
        img_am1.setVisibility(View.INVISIBLE);
        img_am2.setVisibility(View.INVISIBLE);
        img_am3.setVisibility(View.INVISIBLE);
        img_am4.setVisibility(View.INVISIBLE);
        img_am5.setVisibility(View.INVISIBLE);
        img_am6.setVisibility(View.INVISIBLE);
        img_am7.setVisibility(View.INVISIBLE);
        img_pm1.setVisibility(View.INVISIBLE);
        img_pm2.setVisibility(View.INVISIBLE);
        img_pm3.setVisibility(View.INVISIBLE);
        img_pm4.setVisibility(View.INVISIBLE);
        img_pm5.setVisibility(View.INVISIBLE);
        img_pm6.setVisibility(View.INVISIBLE);
        img_pm7.setVisibility(View.INVISIBLE);
    }

    public void than7(int page){
        page++;
        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy年MM月dd日");
        String date = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        try {
            d1=sdf.parse(date);
            if(page!=group){
                tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                tv_time2.setText(TimeTools.getDayTime(d1,7*page-5));
                tv_time3.setText(TimeTools.getDayTime(d1,7*page-4));
                tv_time4.setText(TimeTools.getDayTime(d1,7*page-3));
                tv_time5.setText(TimeTools.getDayTime(d1,7*page-2));
                tv_time6.setText(TimeTools.getDayTime(d1,7*page-1));
                tv_time7.setText(TimeTools.getDayTime(d1,7*page));
            }else {
                if(remainder==1){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    rl_2_left.setEnabled(false);
                    rl_2_right.setEnabled(false);
                    rl_3_left.setEnabled(false);
                    rl_3_right.setEnabled(false);
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(remainder==2){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    tv_time2.setText(TimeTools.getDayTime(d1,7*page - 5));
                    rl_3_left.setEnabled(false);
                    rl_3_right.setEnabled(false);
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(remainder==3){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    tv_time2.setText(TimeTools.getDayTime(d1, 7 *page-5));
                    tv_time3.setText(TimeTools.getDayTime(d1,7*page-4));
                    rl_4_left.setEnabled(false);
                    rl_4_right.setEnabled(false);
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(remainder==4){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    tv_time2.setText(TimeTools.getDayTime(d1,7*page-5));
                    tv_time3.setText(TimeTools.getDayTime(d1, 7 * page-4));
                    tv_time4.setText(TimeTools.getDayTime(d1,7*page-3));
                    rl_5_left.setEnabled(false);
                    rl_5_right.setEnabled(false);
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(remainder==5){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    tv_time2.setText(TimeTools.getDayTime(d1,7*page-5));
                    tv_time3.setText(TimeTools.getDayTime(d1,7*page-4));
                    tv_time4.setText(TimeTools.getDayTime(d1, 7 * page-3));
                    tv_time5.setText(TimeTools.getDayTime(d1,7*page-2));
                    rl_6_left.setEnabled(false);
                    rl_6_right.setEnabled(false);
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
                if(remainder==6){
                    tv_time1.setText(TimeTools.getDayTime(d1,7*page-6));
                    tv_time2.setText(TimeTools.getDayTime(d1,7*page-5));
                    tv_time3.setText(TimeTools.getDayTime(d1,7*page-4));
                    tv_time4.setText(TimeTools.getDayTime(d1,7*page-3));
                    tv_time5.setText(TimeTools.getDayTime(d1,7*page-2));
                    tv_time6.setText(TimeTools.getDayTime(d1,7*page-1));
                    rl_7_left.setEnabled(false);
                    rl_7_right.setEnabled(false);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void lessThan7(){
        SimpleDateFormat sDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new java.util.Date());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1=sdf.parse(date);
                if(remainder==1){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                }
                if(remainder==2){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                }
                if(remainder==3){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                }
                if(remainder==4){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                }
                if(remainder==5){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                }
                if(remainder==6){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                }
                if(remainder==7){
                    tv_time1.setText(TimeTools.getDayTime(d1,1));
                    tv_time2.setText(TimeTools.getDayTime(d1,2));
                    tv_time3.setText(TimeTools.getDayTime(d1,3));
                    tv_time4.setText(TimeTools.getDayTime(d1,4));
                    tv_time5.setText(TimeTools.getDayTime(d1,5));
                    tv_time6.setText(TimeTools.getDayTime(d1,6));
                    tv_time7.setText(TimeTools.getDayTime(d1,7));
                }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setCanOnCklic(){
        rl_1_left.setEnabled(true);
        rl_1_right.setEnabled(true);
        rl_2_left.setEnabled(true);
        rl_2_right.setEnabled(true);
        rl_3_left.setEnabled(true);
        rl_3_right.setEnabled(true);
        rl_4_left.setEnabled(true);
        rl_4_right.setEnabled(true);
        rl_5_left.setEnabled(true);
        rl_5_right.setEnabled(true);
        rl_6_left.setEnabled(true);
        rl_6_right.setEnabled(true);
        rl_7_left.setEnabled(true);
        rl_7_right.setEnabled(true);
    }



    public void addComplete(View v){
        List<Time> listTime = new ArrayList<Time>();
        for (int i=0;i<group;i++){
            List<List<String>> lists = hasPage.get(i);
            for(int j=0;j<7;j++){
                List<String> msgLsit = lists.get(j);
                if(msgLsit.size()!=0){
                    if(msgLsit.get(0).length()>10){//这边可以判断是否需要显示没选中的日期
                        Time time = new Time();
                        String msg = msgLsit.get(0);
                        String dateToLong = TimeTools.dateToLong(msg.substring(0, msg.length() - 2));
                        time.setDatatime(dateToLong);
                        time.setAm(msg.substring(msg.length() - 2, msg.length() - 1));
                        time.setPm(msg.substring(msg.length() - 1, msg.length()));
                        listTime.add(time);
                    }
                }
            }
        }


        UIEventUpdate.getInstance().customSendData(2,listTime,case_type);

        finish();







    }




}
