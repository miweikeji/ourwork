package app.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.miweikeij.app.R;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;


import app.activity.BaseActivity;
import app.dialog.DialogCharge;
import app.dialog.DialogRefund;
import app.entity.Meta;
import app.entity.MyProtect;
import app.entity.UserInfo;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * Created by tlt on 2015/10/11.
 */
public class ProtectMoneyActivity extends BaseActivity {
    private DialogCharge dialogCharge;
    private DialogRefund dialogRefund;
    private TextView tvMoney;

    @Override
    public void obtainData() {

        showWaitingDialog();
        HttpRequest.getMyProtect(mActivity, new ICallback<MyProtect>() {
            @Override
            public void onSucceed(MyProtect result) {
              disMissWaitingDialog();
              String myProtect=result.getProtect();
                if (!TextUtils.isEmpty(myProtect))
                tvMoney.setText("￥"+myProtect);
            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);

            }
        });


    }

    @Override
    public void initUI() {
        tvMoney=(TextView)findViewById(R.id.tv_money);


    }

    @Override
    public int onCreateMyView() {
        return R.layout.activity_protectmoney;
    }

    //退款
    public void btn_refund(View v) {

        if (dialogRefund == null) {
            dialogRefund = new DialogRefund(mActivity) {
                @Override
                public void positionBtnClick(String s) {
                    showWaitingDialog();
                    HttpRequest.backMoney(mActivity, s, "", new ICallback<Meta>() {
                        @Override
                        public void onSucceed(Meta result) {
                            disMissWaitingDialog();
                            Uihelper.showToast(mActivity,"退款受理成功");
                            finish();
                        }
                        @Override
                        public void onFail(String error) {
                            disMissWaitingDialog();
                            Uihelper.showToast(mActivity, error);

                        }
                    });

                }
            };
        }
        dialogRefund.show();

    }

    //充值
    public void btn_charge(View v) {


        if (dialogCharge == null) {
            dialogCharge = new DialogCharge(mActivity) {
                @Override
                public void positionBtnClick(String money) {

                    getTn(money);
                }
            };
        }
        dialogCharge.show();

    }

    private void getTn(String money) {
        showWaitingDialog();
        HttpRequest.gettn(mActivity, UserInfo.getInstance().getId(), money, "", "1",UserInfo.getInstance().getId(),  new ICallback<String>() {
            @Override
            public void onSucceed(String tn) {
                disMissWaitingDialog();
                String serverMode = "01";
                if(tn!=null&&!"".equals(tn)){
                    UPPayAssistEx.startPayByJAR(ProtectMoneyActivity.this, PayActivity.class, null, null,
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
            Uihelper.showToast(ProtectMoneyActivity.this,"支付成功");
        }else if( str.equalsIgnoreCase("fail") ){
            Uihelper.showToast(ProtectMoneyActivity.this,"支付失败");
        }else if( str.equalsIgnoreCase("cancel") ){
            Uihelper.showToast(ProtectMoneyActivity.this,"支付取消");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("保证金");
        mBar.setRightTitle("记录");
        mBar.setRightOnClick(new NavigationBar.RightOnClick() {
            @Override
            public void setRightOnClick() {
                startActivity(new Intent(mActivity, ProtectRecordActivity.class));
            }
        });

    }
}
