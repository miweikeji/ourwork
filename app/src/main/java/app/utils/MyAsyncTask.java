package app.utils;

import java.util.Arrays;
import java.util.List;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

public abstract class MyAsyncTask<Result> extends AsyncTask<Void, Void, Result> {

	private Activity activity;
	protected ICallbackUri<Result> callback;
	protected boolean isSucceed = true;
	protected String error;
	private Context context;
	boolean IsSerive = false;
	public static final List<Integer> NOT_ERROR_CODE = Arrays.asList(1000001,
			1000101, 1000107, 111);// 错误代码的例外，当出现包含在里面的错误代码时，不执行onerror。1000001手机号码已被注册，1000101手机号码未注册，1000107
									// 手机号码尚未绑定其他账号,111用户不存在

	public MyAsyncTask(Activity activity, ICallbackUri<Result> callback) {
		this.activity = activity;
		this.callback = callback;
	}

	public MyAsyncTask(Context context, ICallbackUri<Result> callback,
			boolean IsSerive) {
		this.context = context;
		this.callback = callback;
		this.IsSerive = IsSerive;
	}

	public abstract Result handler();

	@Override
	protected Result doInBackground(Void... params) {
		try {
			return handler();
		} catch (Exception e) {
			isSucceed = false;
		}
		return null;
	}

	public void doError() {
	}

	public void doSuccessAfter() {
	}

	@Override
	protected void onPostExecute(Result result) {
		if (IsSerive) {
			if (isSucceed) {

				doSuccessAfter();
				if (context != null) {
					callback.onSucceedUri(result);
				}
			} else {
				doError();
				if (activity == null
						|| (activity != null && !activity.isFinishing())) {
					callback.onFailUri(error);
				}
			}
		} else {
			if (isSucceed) {

				doSuccessAfter();
				if (activity == null
						|| (activity != null && !activity.isFinishing())) {
					callback.onSucceedUri(result);
				}
			} else {
				doError();
				if (activity == null
						|| (activity != null && !activity.isFinishing())) {
					callback.onFailUri(error);
				}
			}
		}

	}


	/**
	 * 使AsyncTask里的5个线程同时执行，防止个别接口或者网络问题出现的线程阻塞
	 * 
	 * @author tuliangtan
	 * @createDate 2015-4-8
	 */
	@SuppressLint("NewApi")
	public void execute() {
		if (Build.VERSION.SDK_INT > 12) {
			executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
	}
}
