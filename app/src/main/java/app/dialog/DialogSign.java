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

import java.util.ArrayList;
import java.util.List;

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
    private int signTime;
    private boolean isSign;
    private boolean isHasCase;
    private List<TextView> mlistTextView = new ArrayList<TextView>();
    private List<View> mListViewLine = new ArrayList<View>();

    /**
     * @param context 内容 * @param position_Text 取消按钮的内容 如：取消，或是其他操作等
     *                确定按钮的内容 如：去认证，确定等
     * @author Tuliangtan
     */
    public DialogSign(Context context, int signTime, boolean isSign, boolean isHasCase) {
        super(context, R.style.Dialog);
        this.setContentView(R.layout.dialog_sign);
        this.mContext = context;
        this.signTime = signTime;
        this.isSign = isSign;
        this.isHasCase = isHasCase;


        initViewCode();
    }

    public abstract void sign();

    public abstract void toCase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViewCode() {

        View layout = findViewById(R.id.linear_sign);
        Button btnSign= (Button) findViewById(R.id.btn_sign);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
                dismiss();
            }
        });

        if (isSign) {
            btnSign.setBackgroundResource(R.color.grey_2);
            btnSign.setEnabled(false);
            btnSign.setText("已签到");
        }

        LinearLayout frameCase = (LinearLayout) findViewById(R.id.frame_case);

        if (isHasCase) {
            frameCase.setVisibility(View.VISIBLE);
            findViewById(R.id.btn_haseCase).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toCase();
                    dismiss();
                }
            });
        }else {
            frameCase.setVisibility(View.GONE);
        }

        titleText = (TextView) findViewById(R.id.dialog_title);
        tv_state1 = (TextView) findViewById(R.id.state_1);
        tv_state2 = (TextView) findViewById(R.id.state_2);
        tv_state3 = (TextView) findViewById(R.id.state_3);
        tv_state4 = (TextView) findViewById(R.id.state_4);
        tv_state5 = (TextView) findViewById(R.id.state_5);
        tv_state6 = (TextView) findViewById(R.id.state_6);
        tv_state7 = (TextView) findViewById(R.id.state_7);
        mlistTextView.add(tv_state1);
        mlistTextView.add(tv_state2);
        mlistTextView.add(tv_state3);
        mlistTextView.add(tv_state4);
        mlistTextView.add(tv_state5);
        mlistTextView.add(tv_state6);
        mlistTextView.add(tv_state7);

        int srcreenWidth = ScreenUtil.instance(mContext).getScreenWidth();

        int size = (srcreenWidth - Uihelper.dip2px(mContext, 114)) / 7;

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(size, size
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

        line1 = findViewById(R.id.line_1);
        line2 = findViewById(R.id.line_2);
        line3 = findViewById(R.id.line_3);
        line4 = findViewById(R.id.line_4);
        line5 = findViewById(R.id.line_5);
        line6 = findViewById(R.id.line_6);
        line7 = findViewById(R.id.line_7);

        mListViewLine.add(line1);
        mListViewLine.add(line2);
        mListViewLine.add(line3);
        mListViewLine.add(line4);
        mListViewLine.add(line5);
        mListViewLine.add(line6);
        mListViewLine.add(line7);
        if (signTime == 0) {
            return;
        }
        for (; signTime > 0; signTime--) {
            mlistTextView.get(signTime - 1).setBackgroundResource(R.drawable.btn_circlepress);
            mlistTextView.get(signTime - 1).setTextColor(mContext.getResources().getColor(R.color.white));
            mListViewLine.get(signTime - 1).setBackgroundResource(R.color.base_orange);
        }

    }

    public void setTitle(String title) {
        if (titleText != null) {
            titleText.setText(title);
        }
    }

}
