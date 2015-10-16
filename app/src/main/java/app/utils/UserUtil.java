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
    };

    public static void clearUserInfo(Context context) {

        Pref.saveString(Pref.USERID, "", context);
    }

    public static void saveUserId(Context context, String userId) {
        Pref.saveString(Pref.USERID, userId, context);
    }

}
