package app.activity.user;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miweikeij.app.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import app.activity.AddMembersActivity;
import app.activity.BaseActivity;
import app.activity.GroupMembersActivity;
import app.entity.Meta;
import app.net.HttpRequest;
import app.net.ICallback;
import app.utils.Uihelper;
import app.views.NavigationBar;

/**
 * 工头认证
 * Created by tlt on 2015/10/12.
 */

public class JobAuthentActivity extends BaseActivity implements View.OnClickListener {
    private EditText edit_name;
    private EditText edit_groupname;
    private EditText edit_style;
    private EditText edit_intraduce;
    private EditText edit_authent;
    private TextView tv_addMember;
    private String SD_CARD_TEMP_DIR1 = Environment.getExternalStorageDirectory() + File.separator + "tmpPhoto1.jpg";
    private String SD_CARD_TEMP_DIR2 = Environment.getExternalStorageDirectory() + File.separator + "tmpPhoto2.jpg";
    private ImageView ivPosition;
    private ImageView ivNegative;
    private byte[] mContent;
    Bitmap myBitmap;
    private CheckBox checkClear;
    private CheckBox checkHalf;
    private CheckBox checkAll;

    @Override
    public void obtainData() {

    }

    @Override
    public void initUI() {
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_authent = (EditText) findViewById(R.id.edit_authent);
        edit_groupname = (EditText) findViewById(R.id.edit_groupname);
        edit_style = (EditText) findViewById(R.id.edit_style);
        edit_intraduce = (EditText) findViewById(R.id.edit_introduce);

        tv_addMember = (TextView) findViewById(R.id.tv_addMember);
        tv_addMember.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tv_addMember.setOnClickListener(this);

        findViewById(R.id.iv_position).setOnClickListener(this);
        findViewById(R.id.iv_nagetive).setOnClickListener(this);
        ivPosition = (ImageView) findViewById(R.id.iv_me_position);
        ivNegative = (ImageView) findViewById(R.id.iv_me_nagetive);

        checkAll=(CheckBox)findViewById(R.id.checkbox_all);
        checkHalf=(CheckBox)findViewById(R.id.checkbox_half);
        checkClear=(CheckBox)findViewById(R.id.checkbox_clear);
        findViewById(R.id.btn_summit).setOnClickListener(this);


    }


    @Override
    public int onCreateMyView() {
        return R.layout.activity_jobauthent;
    }

    @Override
    public void initTitle(NavigationBar mBar) {
        mBar.setContexts(this);
        mBar.setTitle("工头认证");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addMember:

                startActivity(new Intent(mActivity, AddMembersActivity.class));

                break;

            case R.id.iv_position:

                //身份证正面照
                Intent takePictureFromCameraIntent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                takePictureFromCameraIntent.putExtra(
                        android.provider.MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(SD_CARD_TEMP_DIR1)));
                startActivityForResult(takePictureFromCameraIntent, 1);

                break;
            case R.id.iv_nagetive:
                //身份证反面照
                Intent takePictureFromCameraIntent2 = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                takePictureFromCameraIntent2.putExtra(
                        android.provider.MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(SD_CARD_TEMP_DIR2)));
                startActivityForResult(takePictureFromCameraIntent2, 2);

                break;

            case R.id.btn_summit:
                  summit();
                break;
            default:
                break;
        }
    }

    private void summit() {
        String name=edit_name.getText().toString();
        String authent=edit_authent.getText().toString();
        String groupname=edit_groupname.getText().toString();
        String introduce=edit_intraduce.getText().toString();
        String style=edit_style.getText().toString();

        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(authent)&&!TextUtils.isEmpty(groupname)&&!TextUtils.isEmpty(introduce)&&!TextUtils.isEmpty(style)){

            if (checkAll.isChecked()||checkClear.isChecked()||checkHalf.isChecked()){

                httpSummit(name,authent,groupname,style,introduce);

            }else {
                Uihelper.showToast(mActivity,"请填写所有内容");
            }

        }else {
            Uihelper.showToast(mActivity,"请填写所有内容");
        }
    }

    private void httpSummit(String name, String authent, String groupname,String  style,String introduce) {

        StringBuilder  groupInfo=new StringBuilder();
        groupInfo.append("{\"groupName\":%20\"").append(groupname).append("\",\"groupStyle\":%20\"");
        if (checkAll.isChecked()){
            groupInfo.append("全包");
        }
        if (checkHalf.isChecked()){
            groupInfo.append(",半包");
        }
        if (checkClear.isChecked()){
            groupInfo.append(",清包");
        }
        groupInfo.append("\",\"groupExpert\":%20\"").append(style);
        groupInfo.append("\",\"groupDescription\":%20\"").append(introduce).append("\"}");

                showWaitingDialog();
        HttpRequest.workHeadAudit(mActivity, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,"认证审核中");

            }

            @Override
            public void onFail(String error) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity,error);


            }
        },name,authent,groupInfo.toString(),"","");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        ContentResolver resolver = getContentResolver();
        File f=null;
        try {
            if (requestCode==1){

                f= new File(SD_CARD_TEMP_DIR1);
            }else {
                f= new File(SD_CARD_TEMP_DIR2);
            }
            try {
                Uri capturedImage = Uri
                        .parse(android.provider.MediaStore.Images.Media
                                .insertImage(getContentResolver(), f
                                        .getAbsolutePath(), null, null));

                Toast toast = Toast.makeText(getApplicationContext(),
                        capturedImage.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                //
                // 将图片内容解析成字节数组
                mContent = readStream(resolver.openInputStream(Uri
                        .parse(capturedImage.toString())));
                // 将字节数组转换为ImageView可调用的Bitmap对象
                myBitmap = getPicFromBytes(mContent, null);
                // //把得到的图片绑定在控件上显示
                if (requestCode==1){

                    ivPosition.setImageBitmap(myBitmap);
                }else {
                    ivNegative.setImageBitmap(myBitmap);
                }

                //

                //

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    //
    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    //
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    //
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
