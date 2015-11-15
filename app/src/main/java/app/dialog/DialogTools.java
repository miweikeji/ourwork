package app.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miwei.jzj_system.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.entity.Data;
import app.tools.TimeTools;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/12.
 */
public class DialogTools {

    public static DialogOnClickListens listens;
    public static interface DialogOnClickListens{
        void onDialogClick();
    }
    public static DialogCountTypeListens type;
    public static interface DialogCountTypeListens{
        void onTypeChoose(String type,int i);
    }

    public static DialogOnClickChockedListens listen;
    public static interface DialogOnClickChockedListens{
        void onCheckedChoose(HashMap<Integer,String> hasMap);
        void onDismiss(HashMap<Integer,String> hasMap);
    }
    public static void setDialogOnClick(DialogOnClickListens l){
        listens = l;
    }
    public static void setCheckedChoose(DialogOnClickChockedListens l){
        listen = l;
    }
    public static void setTypeChoose(DialogCountTypeListens l){
        type = l;
    }
    private static Dialog dialog;
    public static  Dialog timeShow(Activity activity, List<Data> data){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_time_show, null);
        RelativeLayout layout_time_show = (RelativeLayout)layout.findViewById(R.id.layout_time_show);
        LinearLayout add_schedule_item = (LinearLayout) layout.findViewById(R.id.add_schedule_item);
        add_schedule_item.removeAllViews();
        if(data!=null){
            for (int i=0;i<data.size();i++){
                Data msg = data.get(i);
                View inflate = activity.getLayoutInflater().inflate(R.layout.item_schedule, null);
                TextView tv_time = (TextView) inflate.findViewById(R.id.tv_time);
                ImageView img_am = (ImageView) inflate.findViewById(R.id.img_am);
                ImageView img_pm = (ImageView) inflate.findViewById(R.id.img_pm);
//                tv_time.setText(TimeTools.longToDate(msg.getDatatime()));
                tv_time.setText(Uihelper.longToDateStr(Double.valueOf(msg.getDatatime())));
                String am = msg.getAm();
                String pm = msg.getPm();
                if("0".equals(am)){
                    img_am.setVisibility(View.INVISIBLE);
                }else {
                    img_am.setVisibility(View.VISIBLE);
                }
                if("0".equals(pm)){
                    img_pm.setVisibility(View.INVISIBLE);
                }else {
                    img_pm.setVisibility(View.VISIBLE);
                }

                add_schedule_item.addView(inflate);
            }
        }

        dialog = new Dialog(activity, R.style.FullScreenDialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.alpha = 0.8f;

        window.setAttributes(lp);

        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layout_time_show, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return dialog;
    }

    public static  Dialog refuseShow(Activity activity){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_refuse, null);
        RelativeLayout layout_refuse_show = (RelativeLayout)layout.findViewById(R.id.layout_refuse_show);
        Button sure = (Button)layout.findViewById(R.id.btn_sure);
        Button cancel = (Button)layout.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listens.onDialogClick();
                dialog.dismiss();
            }
        });
        dialog = new Dialog(activity, R.style.FullScreenDialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.alpha = 0.8f;

        window.setAttributes(lp);

        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layout_refuse_show, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return dialog;
    }

    public static  Dialog chooseCase(Activity activity,ArrayList<Integer> list){
       final CheckBox[] checkBox=new  CheckBox[6];
       final HashMap<Integer,String> hasMap = new HashMap<Integer,String>();
        hasMap.clear();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_choose_case, null);
        RelativeLayout layout_refuse_show = (RelativeLayout)layout.findViewById(R.id.layout_choose_case);
        Button sure = (Button)layout.findViewById(R.id.btn_sure);
        Button cancel = (Button)layout.findViewById(R.id.btn_cancel);

         checkBox[0] = (CheckBox) layout.findViewById(R.id.checkBox1);
         checkBox[1] = (CheckBox) layout.findViewById(R.id.checkBox2);
         checkBox[2] = (CheckBox) layout.findViewById(R.id.checkBox3);
         checkBox[3] = (CheckBox) layout.findViewById(R.id.checkBox4);
         checkBox[4]= (CheckBox) layout.findViewById(R.id.checkBox5);
         checkBox[5] = (CheckBox) layout.findViewById(R.id.checkBox6);

        if(list!=null){
            for (int i = 1;i<7;i++){
                if(list.contains(i)){
                    checkBox[i-1].setChecked(true);
                }
            }
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox[0].isChecked()){
                    hasMap.put(1,"水电工");
                }else {
                    if(hasMap.containsKey(1)){
                        hasMap.remove(1);
                    }
                }
                if(checkBox[1].isChecked()){
                    hasMap.put(2,"泥水工");
                }else {
                    if(hasMap.containsKey(2)){
                        hasMap.remove(2);
                    }
                }
                if(checkBox[2].isChecked()){
                    hasMap.put(3,"木工");
                }else {
                    if(hasMap.containsKey(3)){
                        hasMap.remove(3);
                    }
                }
                if(checkBox[3].isChecked()){
                    hasMap.put(4,"油漆工");
                }else {
                    if(hasMap.containsKey(4)){
                        hasMap.remove(4);
                    }
                }
                if(checkBox[4].isChecked()){
                    hasMap.put(5,"门窗安装工");
                }else {
                    if(hasMap.containsKey(5)){
                        hasMap.remove(5);
                    }
                }
                if(checkBox[5].isChecked()){
                    hasMap.put(6,"敲打搬运工");
                }else {
                    if(hasMap.containsKey(6)){
                        hasMap.remove(6);
                    }
                }
                listen.onDismiss(hasMap);
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox[0].isChecked()){
                    hasMap.put(1,"水电工");
                }else {
                    if(hasMap.containsKey(1)){
                        hasMap.remove(1);
                    }
                }
                if(checkBox[1].isChecked()){
                        hasMap.put(2,"泥水工");
                }else {
                    if(hasMap.containsKey(2)){
                        hasMap.remove(2);
                    }
                }
                if(checkBox[2].isChecked()){
                    hasMap.put(3,"木工");
                }else {
                    if(hasMap.containsKey(3)){
                        hasMap.remove(3);
                    }
                }
                if(checkBox[3].isChecked()){
                    hasMap.put(4,"油漆工");
                }else {
                    if(hasMap.containsKey(4)){
                        hasMap.remove(4);
                    }
                }
                if(checkBox[4].isChecked()){
                    hasMap.put(5,"门窗安装工");
                }else {
                    if(hasMap.containsKey(5)){
                        hasMap.remove(5);
                    }
                }
                if(checkBox[5].isChecked()){
                    hasMap.put(6,"敲打搬运工");
                }else {
                    if(hasMap.containsKey(6)){
                        hasMap.remove(6);
                    }
                }

                listen.onCheckedChoose(hasMap);
                dialog.dismiss();
            }
        });
        dialog = new Dialog(activity, R.style.FullScreenDialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.alpha = 0.8f;

        window.setAttributes(lp);

        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layout_refuse_show, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return dialog;
    }

    public static  Dialog billingType(Activity activity,final int i){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_biling_type, null);
        RelativeLayout layout_refuse_show = (RelativeLayout)layout.findViewById(R.id.layout_biling);
        RelativeLayout rl_count = (RelativeLayout)layout.findViewById(R.id.rl_count);
        RelativeLayout rl_ping = (RelativeLayout)layout.findViewById(R.id.rl_ping);
        RelativeLayout rl_jia = (RelativeLayout)layout.findViewById(R.id.rl_jia);
        final ImageView ima_count = (ImageView)layout.findViewById(R.id.ima_count);
        final ImageView ima_ping = (ImageView)layout.findViewById(R.id.ima_ping);
        final ImageView ima_jia = (ImageView)layout.findViewById(R.id.ima_jia);
        rl_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.onTypeChoose("按次计算",i);
                ima_count.setVisibility(View.VISIBLE);
                ima_ping.setVisibility(View.INVISIBLE);
                ima_jia.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });
        rl_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.onTypeChoose("按平计算",i);
                ima_count.setVisibility(View.INVISIBLE);
                ima_ping.setVisibility(View.VISIBLE);
                ima_count.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });
        rl_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type.onTypeChoose("按承包价",i);
                ima_count.setVisibility(View.INVISIBLE);
                ima_ping.setVisibility(View.INVISIBLE);
                ima_jia.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog = new Dialog(activity, R.style.FullScreenDialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.alpha = 0.8f;

        window.setAttributes(lp);

        dialog.setCancelable(true);// 不可以用“返回键”取消
        dialog.setContentView(layout_refuse_show, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return dialog;
    }

}
