package app.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.miweikeij.app.R;
/**
 * Created by Administrator on 2015/10/12.
 */
public class DialogTools {

    public static DialogOnClickListens listens;
    public static interface DialogOnClickListens{
        void onDialogClick();
    }

    public static void setDialogOnClick(DialogOnClickListens l){
        listens = l;
    }
    private static Dialog dialog;
    public static  Dialog timeShow(Activity activity){
        LayoutInflater inflater = LayoutInflater.from(activity);
        View layout = inflater.inflate(R.layout.dialog_time_show, null);
        RelativeLayout layout_time_show = (RelativeLayout)layout.findViewById(R.id.layout_time_show);

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
}
