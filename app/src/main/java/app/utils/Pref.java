package app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {


	/** Preferences的存储文件名称 */
	final static String PREFS_NAME = "miweikeji_preferences";


	public static final String USERID = "useId";
	public static final String USERPSW = "usePsw";
	public static final String USERPHONE = "usePhone";
	public static final String USERPROFESSION= "useProfession";

	// 首次开启
	public static final String FIRST_LOAD = "FirstLoad";

	public static final String VISITOR = "0000";

	public static final String NICKNAME = "nickname";

	public static final String USERICON = "usericon";

	public static final String TELEPHONE = "telephone";

	// 是否退出应用
	public static final String EXIT = "exit";


	private static SharedPreferences getSettings(final Context contex) {
		SharedPreferences mSharedPreferences = contex.getSharedPreferences(PREFS_NAME, 0);
		return mSharedPreferences;
	}

	public static String getString(final String key, final Context context, final String defaultValue) {
		return getSettings(context).getString(key, defaultValue);
	}

	public static boolean getBoolean(final String key, final Context context, final boolean defaultValue) {
		return getSettings(context).getBoolean(key, defaultValue);
	}

	public static int getInt(final String key, final Context context, final int defaultValue) {
		return getSettings(context).getInt(key, defaultValue);
	}

	public static long getLong(final String key, final Context context, final long defaultValue) {
		return getSettings(context).getLong(key, defaultValue);
	}

	public static boolean saveString(final String key, final String value, final Context context) {
		final SharedPreferences.Editor settingsEditor = getSettings(context).edit();
		settingsEditor.putString(key, value);
		return settingsEditor.commit();
	}

	public static boolean saveBoolean(final String key, final boolean value, final Context context) {
		final SharedPreferences.Editor settingsEditor = getSettings(context).edit();
		settingsEditor.putBoolean(key, value);
		return settingsEditor.commit();
	}

	public static boolean saveInt(final String key, final int value, final Context context) {
		final SharedPreferences.Editor settingsEditor = getSettings(context).edit();
		settingsEditor.putInt(key, value);
		return settingsEditor.commit();
	}

	public static boolean saveLong(final String key, final long value, final Context context) {
		final SharedPreferences.Editor settingsEditor = getSettings(context).edit();
		settingsEditor.putLong(key, value);
		return settingsEditor.commit();
	}
}
