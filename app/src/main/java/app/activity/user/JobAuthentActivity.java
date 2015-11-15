package app.activity.user;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.miwei.jzj_system.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.activity.AddMembersActivity;
import app.activity.BaseActivity;
import app.entity.Gson_GroupInfo;
import app.entity.Meta;
import app.entity.Phone;
import app.entity.PhoneList;
import app.net.HttpRequest;
import app.net.ICallback;
import app.net.Param;
import app.net.Urls;
import app.utils.ICallbackUri;
import app.utils.ImageViewUtil;
import app.utils.JsonUtil;
import app.utils.Uihelper;
import app.utils.UserUtil;
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
    private String SD_CARD_TEMP_DIR1 = "tmpPhoto1.jpg";
    private String SD_CARD_TEMP_DIR2 = "tmpPhoto2.jpg";
    private ImageView ivPosition;
    private ImageView ivNegative;
    private byte[] mContent;
    Bitmap myBitmap;
    private CheckBox checkClear;
    private CheckBox checkHalf;
    private CheckBox checkAll;

    private HashMap<Integer, String> phoneMap = new HashMap<Integer, String>();
    private ArrayList<String> listPhone = new ArrayList<String>();
    private HashMap<Integer, View> viewMap = new HashMap<Integer, View>();
    private LinearLayout line_add_memebers;
    private int count;
    private static final int PHOTO_REQUEST_CAREMA1 = 1;// 拍照
    private static final int PHOTO_REQUEST_CAREMA2 = 2;// 拍照
    private static final int PHOTO_REQUEST_CUT = 4;// 结果
    private File tempFile1;
    private File tempFile2;
    private String picPath1;
    private String picPath2;
    private boolean isPosition;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private File file_negetive;
    private File file_position;
    private HttpUtils http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        http=new HttpUtils();
        super.onCreate(savedInstanceState);
    }

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

        checkAll = (CheckBox) findViewById(R.id.checkbox_all);
        checkHalf = (CheckBox) findViewById(R.id.checkbox_half);
        checkClear = (CheckBox) findViewById(R.id.checkbox_clear);
        findViewById(R.id.btn_summit).setOnClickListener(this);
        line_add_memebers = (LinearLayout) findViewById(R.id.line_add_memebers);

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
                Intent intent = new Intent(mActivity, AddMembersActivity.class);
                intent.putExtra("isJobAuthent", true);
                Bundle bundle_path = new Bundle();
                bundle_path.putSerializable("listPhone", listPhone);
                startActivityForResult(intent, 0);
                break;
            case R.id.iv_position:
                fromCamera(1);
                break;
            case R.id.iv_nagetive:
                fromCamera(2);
                break;
            case R.id.btn_summit:
                summit();
                break;
            default:
                break;
        }
    }

    private void fromCamera(int type) {
        // TODO Auto-generated method stub
        // 激活相机
        Intent intent_camera = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        Uri uri = null;
        if (hasSdcard()) {
            if (1 == type) {
                // 从文件中创建uri
                tempFile1 = new File(Environment.getExternalStorageDirectory(),
                        SD_CARD_TEMP_DIR1);
                uri = Uri.fromFile(tempFile1);
            } else {
                tempFile2 = new File(Environment.getExternalStorageDirectory(),
                        SD_CARD_TEMP_DIR2);
                uri = Uri.fromFile(tempFile2);
            }
            if (uri != null) {
                intent_camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        if (1 == type) {
            startActivityForResult(intent_camera, PHOTO_REQUEST_CAREMA1);
        }else {
            startActivityForResult(intent_camera, PHOTO_REQUEST_CAREMA2);
        }
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {

            return false;
        }
    }

    private void summit() {
        String name = edit_name.getText().toString();
        String authent = edit_authent.getText().toString();
        String groupname = edit_groupname.getText().toString();
        String introduce = edit_intraduce.getText().toString();
        String groupExpert = edit_style.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(authent) && !TextUtils.isEmpty(groupname) && !TextUtils.isEmpty(introduce) && !TextUtils.isEmpty(groupExpert)) {

            if (checkAll.isChecked() || checkClear.isChecked() || checkHalf.isChecked()) {

                httpSummit(name, authent, groupname, groupExpert, introduce);

            } else {
                Uihelper.showToast(mActivity, "请填写所有内容");
            }

        } else {
            Uihelper.showToast(mActivity, "请填写所有内容");
        }
    }

    private void httpSummit(String name, String authent, String groupname, String groupExpert, String introduce) {
        String json = null;
        Gson gson = new Gson();
        //拼接电话号码
        if (listPhone.size() != 0) {
            List<Phone> list = new ArrayList<Phone>();
            for (int i = 0; i < listPhone.size(); i++) {
                Phone phones = new Phone();
                phones.setPhone(listPhone.get(i));
                list.add(phones);
            }
            JSONArray accountArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                String phone = gson.toJson(list.get(i));
                JSONObject accountObject;
                try {
                    accountObject = new JSONObject(phone);
                    accountArray.put(i, accountObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Uihelper.trace("accountArray============", accountArray.toString());
            json =  accountArray.toString();

        }
        StringBuilder style = new StringBuilder();
        if (checkAll.isChecked()) {
            style.append("全包");
        }
        if (checkHalf.isChecked()) {
            style.append(",半包");
        }
        if (checkClear.isChecked()) {
            style.append(",清包");
        }
        Gson_GroupInfo groupInfo = new Gson_GroupInfo(groupname, style.toString(), groupExpert, introduce, json);
        //利用gson对象生成json字符串
        String groupInfo_String = gson.toJson(groupInfo);
        Uihelper.trace("====groupInfo_String====" + groupInfo_String);

        String uploadHost = Urls.workHeadAudit;
        RequestParams params = new RequestParams();
        params.addBodyParameter("craftsId", UserUtil.getUserId(mActivity));
        params.addBodyParameter("realname", name);
        params.addBodyParameter("cworknum", authent);
        params.addBodyParameter("groupInfo", groupInfo_String);
        if (file_position != null) {
            params.addBodyParameter("cardImg", file_position);
        } if (file_negetive != null) {
            params.addBodyParameter("cradBImg", file_negetive);
        }
        uploadMethod(params, uploadHost);
    }
    public void uploadMethod(final RequestParams params, final String uploadHost) {
        showWaitingDialog();
        http.send(com.lidroid.xutils.http.client.HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (isUploading) {
                } else {
                }
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "提交成功");
                finish();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                disMissWaitingDialog();
                Uihelper.showToast(mActivity, "提交失败");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 3) { //添加成员

            String PhoneList = data.getStringExtra("listPhone");
            if (!TextUtils.isEmpty(PhoneList)) {
                Uihelper.trace("====listPhone====" + listPhone);
                PhoneList phoneList = JsonUtil.parseObject(PhoneList, PhoneList.class);
                if (phoneList != null && phoneList.getPhones().size() > 0) {
                    List<Phone> phones = phoneList.getPhones();
                    for (int i = 0; i < phones.size(); i++) {
                        String phone = phones.get(i).getPhone();
                        addMembers(phone);
                    }
                }
            }

        }else if(requestCode ==PHOTO_REQUEST_CUT ){

            // 从剪切图片返回的数据
            if (data != null) {
                if (isPosition){
                    bitmap1 = data.getParcelableExtra("data");
                    writebitMaptoUri(isPosition,bitmap1);
                    ivPosition.setImageBitmap(bitmap1);
                }else {
                    bitmap2 = data.getParcelableExtra("data");
                    writebitMaptoUri(isPosition, bitmap2);
                    ivNegative.setImageBitmap(bitmap2);
                }
            }
            try {
                // 将临时文件删除
                if (isPosition){
                    tempFile1.delete();
                }else {
                    tempFile2.delete();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { //身份证正反面照

            // 从相机返回的数据
            if (hasSdcard()) {
                if(requestCode==PHOTO_REQUEST_CAREMA1){
                    isPosition = true;
                    picPath1 = Uri.fromFile(tempFile1).toString();
                    crop(Uri.fromFile(tempFile1));

                }else if (requestCode==PHOTO_REQUEST_CAREMA2){
                    isPosition=false;
                    picPath2 = Uri.fromFile(tempFile2).toString();
                    crop(Uri.fromFile(tempFile2));
                }

            } else {
                Toast.makeText(mActivity, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void writebitMaptoUri(final boolean isPosition,Bitmap bitmap) {

        ImageViewUtil.writeBitmap(mActivity, new ICallbackUri<Uri>() {
            @Override
            public void onSucceedUri(Uri uri) {
                try {
                    if (isPosition){

                         file_position = new File(new URI(uri.toString()));
                    }else {
                        file_negetive = new File(new URI(uri.toString()));
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailUri(String error) {
                Uihelper.showToast(mActivity, error);
            }
        }, bitmap);
    }
    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，7：4
        intent.putExtra("aspectX", 7);
        intent.putExtra("aspectY", 4);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 210);
        intent.putExtra("outputY", 120);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    private void addMembers(String phone) {
        listPhone.add(phone);
        phoneMap.clear();
        count++;
        line_add_memebers.removeAllViews();
        for (int i = 0; i < count; i++) {
            View layout = getLayoutInflater().inflate(R.layout.item_add_members, null);
            RelativeLayout rl_deleate = (RelativeLayout) layout.findViewById(R.id.rl_deleate);
            TextView tv_memeber_phone = (TextView) layout.findViewById(R.id.tv_memeber_phone);
            tv_memeber_phone.setText(listPhone.get(i));
            viewMap.put(i, layout);
            rl_deleate.setId(i);
            phoneMap.put(i, listPhone.get(i));
            rl_deleate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count--;
                    int id = v.getId();
                    String phone = phoneMap.get(id);
                    line_add_memebers.removeView(viewMap.get(id));

                    for (int i = 0; i < listPhone.size(); i++) {
                        if (phone.equals(listPhone.get(i))) {
                            listPhone.remove(i);
                        }
                    }

                }
            });
            line_add_memebers.addView(layout);
        }
    }
}
