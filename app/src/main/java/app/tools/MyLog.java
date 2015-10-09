package app.tools;

import android.util.Log;

public class MyLog {

	private static boolean isLog = true;
//	private static boolean isLog = false;
	public static void e(String logTag, String logMessage){
		if(logTag != null && logMessage != null){
			if(isLog){
				Log.e(logTag, logMessage);
			}
		}
	}
	

	public static void d(String logTag, String logMessage){
		if(logTag != null && logMessage != null){
			if(isLog){
				Log.e(logTag, logMessage);
			}
		}
	}
	
	public static void a(String logTag, String logMessage){
		if(logTag != null && logMessage != null){
			if(isLog){
				Log.e(logTag, logMessage);
			}
		}
	}
	public static void b(String logTag, String logMessage){
		if(logTag != null && logMessage != null){
			if(isLog){
				Log.e(logTag, logMessage);
			}
		}
	}
}
