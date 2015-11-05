package app.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.miweikeij.app.R;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import app.activity.BasicInfoActivity;
import app.activity.LoginActivity;
import app.activity.MyWorkDetailsActivity;
import app.activity.user.AboutUsActivity;
import app.activity.user.FeekBackActivity;
import app.activity.user.IntegralActivity;
import app.activity.user.JobAuthentActivity;
import app.activity.user.ProtectMoneyActivity;
import app.activity.user.UserInfoActivity;
import app.dialog.DialogSign;
import app.dialog.UserHeadPopup;
import app.entity.Crafts;
import app.entity.CraftsResult;
import app.entity.Meta;
import app.entity.SingIn;
import app.entity.SingInResult;
import app.entity.UserInfo;
import app.entity.UserInfoResult;
import app.net.HttpRequest;
import app.net.ICallback;
import app.tools.Base64TOString;
import app.utils.Uihelper;
import app.utils.UserUtil;

/**
 * Created by Administrator on 2015/10/2.
 */
public class MineFragment extends Fragment implements View.OnClickListener, UserHeadPopup.UserHeadPopupDelegate {

    private TextView tvJobage;
    private TextView tvAge;
    private TextView tvArea;
    private TextView tvJob;
    private TextView tvName;
    private TextView tvPosition;
    private ImageView ivUserImage;
    private View layout;
    private DialogSign dialogSign;
    private ImageLoader imageLoader;
    public static DisplayImageOptions options;
    private int signTime;
    private boolean isSign;
    private boolean hasCase;


    private File tempFile;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final int PCINFOALTER_SUCCESS = 4;
    private static final int PCINFOALTER_FAILED = 5;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    public static String bitmap2Byte;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_mine, null);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).displayer(new RoundedBitmapDisplayer(0)).build();
        findView(layout);
        obtainSign();
        obtainData();
        return layout;
    }

    private void obtainSign() {

        HttpRequest.getSignIn(getActivity(), new ICallback<SingInResult>() {
            @Override
            public void onSucceed(SingInResult result) {
                SingIn singIn = result.getSingIn();
                if (singIn != null) {
                    signTime = singIn.getSignNum();
                    if (singIn.getIs_sign() == 1) {
                        isSign = true;
                    }
                    hasCase = true;
                }


            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(), error);

            }
        });
    }


    @Override
    public void onStart() {

        if (UserUtil.hasLogin(getActivity())){
            layout.findViewById(R.id.frame_logined).setVisibility(View.VISIBLE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.GONE);
            tvAge.setText(UserInfo.getInstance().getAge());
            tvArea.setText(UserInfo.getInstance().getAddress());
            tvJob.setText(UserInfo.getInstance().getProfession());
            tvJobage.setText(UserInfo.getInstance().getCworkold()+"年工龄");
            tvName.setText(UserInfo.getInstance().getBusername());
            imageLoader.displayImage(UserInfo.getInstance().getCard_bimg(), ivUserImage, options);
        }else {
            layout.findViewById(R.id.frame_logined).setVisibility(View.GONE);
            layout.findViewById(R.id.frame_noLogin).setVisibility(View.VISIBLE);
        }
        super.onStart();
    }

    private void findView(View layout) {


        tvAge = (TextView) layout.findViewById(R.id.tv_me_age);
        tvArea = (TextView) layout.findViewById(R.id.tv_me_area);
        tvJob = (TextView) layout.findViewById(R.id.tv_me_job);
        tvJobage = (TextView) layout.findViewById(R.id.tv_me_jobage);
        tvName = (TextView) layout.findViewById(R.id.tv_me_name);
        tvPosition = (TextView) layout.findViewById(R.id.tv_me_position);

        ivUserImage = (ImageView) layout.findViewById(R.id.iv_me_userimage);
        layout.findViewById(R.id.btn_exit).setOnClickListener(this);

        layout.findViewById(R.id.frame_me_about).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_authencation).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_integral).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_protect).setOnClickListener(this);
        layout.findViewById(R.id.frame_me_suggestion).setOnClickListener(this);
        layout.findViewById(R.id.frame_userinfo).setOnClickListener(this);

        layout.findViewById(R.id.tv_sign).setOnClickListener(this);
        TextView tvLogin=(TextView)layout.findViewById(R.id.tv_login);
        tvLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        tvLogin.setOnClickListener(this);
        ivUserImage.setOnClickListener(this);


    }
    private void setData(Crafts crafts) {

        tvAge.setText(crafts.getAge());
        tvArea.setText(crafts.getAddress());
        tvJob.setText(crafts.getProfession());
        tvJobage.setText(crafts.getCworkold()+"年工龄");
        tvName.setText(crafts.getBusername());
        imageLoader.displayImage(crafts.getCard_bimg(), ivUserImage, options);



    }
    private void obtainData() {

        HttpRequest.myInfo(getActivity(), new ICallback<CraftsResult>() {
            @Override
            public void onSucceed(CraftsResult result) {
                Crafts crafts = result.getCrafts();
                if (crafts != null) {
                    setData(crafts);
                }
            }

            @Override
            public void onFail(String error) {
                Uihelper.showToast(getActivity(), error);
            }
        });

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //关于
            case R.id.frame_me_about:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break; //关于
            case R.id.frame_userinfo:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            //工人认证
            case R.id.frame_me_authencation:
                startActivity(new Intent(getActivity(), JobAuthentActivity.class));
                break;
            //我的积分
            case R.id.frame_me_integral:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            //保证金
            case R.id.frame_me_protect:
                startActivity(new Intent(getActivity(), ProtectMoneyActivity.class));
                break;
            //签到
            case R.id.tv_sign:
                if (dialogSign==null){
                    dialogSign=new DialogSign(getActivity(),signTime,isSign,hasCase) {

                        @Override
                        public void sign() {
                            dialogSign.dismiss();
                            HttpRequest.signIn(getActivity(), new ICallback<Meta>() {
                                @Override
                                public void onSucceed(Meta result) {

                                    Uihelper.showToast(getActivity(), "签到成功");
                                    obtainSign();
                                }

                                @Override
                                public void onFail(String error) {

                                    Uihelper.showToast(getActivity(), error);

                                }
                            });

                        }

                        @Override
                        public void toCase() {
                            dialogSign.dismiss();
                            startActivity(new Intent(getActivity(), MyWorkDetailsActivity.class));

                        }

                    };
                }

                dialogSign.show();
                break;
            //意见反馈
            case R.id.frame_me_suggestion:
                startActivity(new Intent(getActivity(), FeekBackActivity.class));

                break;
                //退出
            case R.id.btn_exit:

                UserUtil.clearUserInfo(getActivity());
                UserInfo.getInstance().clearUserInfo();
                onStart();

                break;
            case R.id.tv_login:
                LoginActivity.enterActivity(getActivity());

                break;
            case R.id.iv_me_userimage:
                changeHead();
                break;
            default:
                break;
        }

    }

    private void changeHead() {
        UserHeadPopup mUserHeadPopup = new UserHeadPopup(getActivity()).setDelegate(this);
        mUserHeadPopup.setAnimationStyle(R.style.anim_popupWindow);
        mUserHeadPopup.showAtLocation(
                layout.findViewById(R.id.bithtday),
                Gravity.BOTTOM, 0, 0);

    }





    private void fromCamera() {
        // TODO Auto-generated method stub
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    PHOTO_FILE_NAME);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    private void fromAlbum() {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {

            return false;
        }
    }
    String encode;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(getActivity(), "未找到存储卡，无法存储照片！", 0)
                        .show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                ivUserImage.setImageBitmap(bitmap);
                bitmap2Byte = Base64TOString.Bitmap2Byte(bitmap);

                try {
                    encode = URLEncoder.encode(bitmap2Byte, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                netWorkData(encode);
                // getSharedPreferences("bitmap2Byte",0).edit().putString("bitmap2Byte",
                // bitmap2Byte).commit();
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void netWorkData(String bitmap2Byte) {

        HttpRequest.myImgEdit(getActivity(), UserInfo.getInstance().getId(), bitmap2Byte, new ICallback<Meta>() {
            @Override
            public void onSucceed(Meta result) {
                Uihelper.showToast(getActivity(),"上传头像成功");
            }

            @Override
            public void onFail(String error) {
                    Uihelper.showToast(getActivity(),error);

            }
        });
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    @Override
    public void getUserHeadPopupSuccess(int type) {
        if(type==0){
            fromAlbum();
        }else if(type==1){
            fromCamera();
        }
    }
}
