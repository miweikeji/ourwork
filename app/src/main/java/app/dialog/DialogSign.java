package app.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.tools.ScreenUtil;
import app.utils.Uihelper;


public abstract class DialogSign extends Dialog {

    private TextView titleText;
    private TextView tv_state1;
    private TextView tv_state2;
    private TextView tv_state3;
    private TextView tv_state4;
    private TextView tv_state5;
    private TextView tv_state6;
    private TextView tv_state7;

    private Context mContext;

    private View line1;
    private View line2;
    private View line3;
    private View line4;
    private View line5;
    private View line6;
    private View line7;

    /**
     * @param context 内容 * @param position_Text 取消按钮的内容 如：取消，或是其他操作等
     *                确定按钮的内容 如：去认证，确定等
     * @author Tuliangtan
     */
    public DialogSign(Context context) {
        super(context, R.style.Dialog);
        this.setContentView(R.layout.dialog_sign);
        this.mContext = context;
        initViewCode();
    }

    public abstract void positionBtnClick(String s);

//    public abstract void negativeBtnClick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViewCode() {

        View layout = findViewById(R.id.linear_sign);

        titleText = (TextView) findViewById(R.id.dialog_title);
        tv_state1 = (TextView) findViewById(R.id.state_1);
        tv_state2 = (TextView) findViewById(R.id.state_2);
        tv_state3 = (TextView) findViewById(R.id.state_3);
        tv_state4 = (TextView) findViewById(R.id.state_4);
        tv_state5 = (TextView) findViewById(R.id.state_5);
        tv_state6 = (TextView) findViewById(R.id.state_6);
        tv_state7 = (TextView) findViewById(R.id.state_7);

        int srcreenWidth = ScreenUtil.instance(mContext).getScreenWidth();

        int size = (srcreenWidth - Uihelper.dip2px(mContext, 114)) / 7;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size,size
        );
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size);

        layout.setLayoutParams(layoutParams1);

        tv_state1.setLayoutParams(layoutParams);
        tv_state2.setLayoutParams(layoutParams);
        tv_state3.setLayoutParams(layoutParams);
        tv_state4.setLayoutParams(layoutParams);
        tv_state5.setLayoutParams(layoutParams);
        tv_state6.setLayoutParams(layoutParams);
        tv_state7.setLayoutParams(layoutParams);

        tv_state1.setBackgroundResource(R.drawable.btn_circlepress);
        tv_state2.setBackgroundResource(R.drawable.btn_circlepress);

        tv_state1.setTextColor(mContext.getResources().getColor(R.color.white));
        tv_state2.setTextColor(mContext.getResources().getColor(R.color.white));

        line1 = findViewById(R.id.line_1);
        line2 = findViewById(R.id.line_2);
        line3 = findViewById(R.id.line_3);
        line4 = findViewById(R.id.line_4);
        line5 = findViewById(R.id.line_5);
        line6 = findViewById(R.id.line_6);
        line7 = findViewById(R.id.line_7);


    }

    public void setTitle(String title) {
        if (titleText != null) {
            titleText.setText(title);
        }
    }

}
