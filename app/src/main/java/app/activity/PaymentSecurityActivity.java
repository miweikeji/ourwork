package app.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.miwei.jzj_system.R;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by Administrator on 2015/11/16.
 */
public class PaymentSecurityActivity extends BaseActivity {

    private EditText et_pay_money;
    private String money;
    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {

        String workplace = getIntent().getStringExtra("WORKPLACE");
        TextView et_workplace = (TextView) findViewById(R.id.et_workplace);
        et_workplace.setText(workplace);
        et_pay_money = (EditText) findViewById(R.id.et_pay_money);
    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_payment_security;
    }

    @Override
    public void initTitle(NavigationBar mBar) {

        mBar.setContexts(this);
        mBar.setTitle("支付保障金");

    }


    public void payMoney(View v){
        money = et_pay_money.getText().toString().trim();
        if(money!=null&&!"".equals(money)){
            getTn(money);
        }else {
            Uihelper.showToast(this,"请输入保障金金额");
        }
    }

    private void getTn(String money) {
        showWaitingDialog();
        HttpRequest.gettn(mActivity, UserInfo.getInstance().getId(), money, "", "1", UserInfo.getInstance().getId(), new ICallback<String>() {
            @Override
            public void onSucceed(String tn) {
                disMissWaitingDialog();
                String serverMode = "01";
                if (tn != null && !"".equals(tn)) {
                    UPPayAssistEx.startPayByJAR(PaymentSecurityActivity.this, PayActivity.class, null, null,
                            tn, serverMode);
                }
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, error);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if( data == null ){
            return;
        }

        String str =  data.getExtras().getString("pay_result");
        if( str.equalsIgnoreCase("success") ){
            Uihelper.showToast(PaymentSecurityActivity.this,"支付成功");
            UserInfo.getInstance().setProtect(money);
            finish();
        }else if( str.equalsIgnoreCase("fail") ){
            Uihelper.showToast(PaymentSecurityActivity.this,"支付失败");
        }else if( str.equalsIgnoreCase("cancel") ){
            Uihelper.showToast(PaymentSecurityActivity.this,"支付取消");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
