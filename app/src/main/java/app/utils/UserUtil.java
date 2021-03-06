package app.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import app.activity.LoginActivity;


public class UserUtil {

    private static Context context;

    public static boolean isLogin(Activity context) {

        if (!hasLogin(context)) {
            LoginActivity.enterActivity(context);
            return false;
        } else {
            return true;
        }
    }

    public static boolean hasLogin(Context context) {
        String userId = getUserId(context);
        if (TextUtils.isEmpty(userId)) {
            return false;
        } else {
            return true;
        }
    }
    public static String getUserId(Context context) {
        return Pref.getString(Pref.USERID, context, "");
    }

    public static String getUserPsw(Context context){
        return Pref.getString(Pref.USERPSW, context, "");
    }

    public static String getUserPhone(Context context){
        return Pref.getString(Pref.USERPHONE, context, "");
    }

    public static String getUserProfession(Context context){
        return Pref.getString(Pref.USERPROFESSION, context, "");
    }

    public static void clearUserInfo(Context context) {
        Pref.saveString(Pref.USERID, "", context);
        Pref.saveString(Pref.USERPHONE,"",context);
        Pref.saveString(Pref.USERPSW,"",context);
        Pref.saveString(Pref.USERPROFESSION,"",context);
    }

    public static Boolean getIsFitst(Context context){
        return Pref.getBoolean(Pref.IS_FITST_USER_APP,context,false);
    }

    public static void saveUserId(Context context, String userId) {
        Pref.saveString(Pref.USERID, userId, context);
    }

    public static void saveUserPsw(Context context,String psw){
        String md5_psw= MD5Util.getMD5String(psw);
        Pref.saveString(Pref.USERPSW,md5_psw,context);
    }

    public static void saveUserPhone(Context context,String phone){
        Pref.saveString(Pref.USERPHONE,phone,context);
    }

    public static void saveUserProfession(Context context,String profession){
        Pref.saveString(Pref.USERPROFESSION,profession,context);
    }

    public static void saveIsFirst(Context context,Boolean isFirst){
        Pref.saveBoolean(Pref.IS_FITST_USER_APP,isFirst,context);
    }

}
