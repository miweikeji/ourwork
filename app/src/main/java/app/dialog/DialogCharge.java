package app.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miweikeij.app.R;

import app.utils.Uihelper;


public abstract class DialogCharge extends Dialog {

    private final Context mContext;
    private TextView titleText;
    private EditText editMoney;

    /**
     * @param context 内容 * @param position_Text 取消按钮的内容 如：取消，或是其他操作等
     *                确定按钮的内容 如：去认证，确定等
     * @author Tuliangtan
     */
    public DialogCharge(Context context) {
        super(context, R.style.Dialog);
        this.mContext=context;
        this.setContentView(R.layout.dialog_charge);
        initViewCode();
    }

    public abstract void positionBtnClick(String s);

//    public abstract void negativeBtnClick();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViewCode() {
        titleText = (TextView) findViewById(R.id.dialog_title);
        editMoney = (EditText) findViewById(R.id.edit_money);

        Button btNegative = (Button) findViewById(R.id.btn_cancel);
        btNegative.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
//                negativeBtnClick();
                dismiss();
            }
        });

        Button btn_sure = (Button) findViewById(R.id.btn_sure);
        btn_sure.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                String money = editMoney.getText().toString();
                if (TextUtils.isEmpty(money)) {
                    Uihelper.showToast((Activity) mContext, "请输入充值金额");
                    return;
                }
                positionBtnClick(money);
                editMoney.setText("");
            }
        });
    }

    public void setTitle(String title) {
        if (titleText != null) {
            titleText.setText(title);
        }
    }

}
