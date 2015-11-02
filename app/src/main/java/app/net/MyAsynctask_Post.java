package app.net;

import java.util.Map;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import app.utils.MobileOS;

/**
 * Description:
 * 
 * @author Jackie
 */

public class MyAsynctask_Post extends MultiVersionAsyncTask<Void, Void, String> {

	public static final String APP_KEY = "&key=sjY^dfs$jd8Ooe5hId4sh";
	private Context mContext;
	private String parameter;
	protected ICallbackString callback;
	private String url;
	private static final String NETWORK_ERROR = "您当前网络不可用";
	public static final String SERVER_ERROR = "服务端网络不通，请重试";

	public MyAsynctask_Post(Context context, String url, ICallbackString callback,
			Map<String, String> mList) {
		mContext = context;
		this.callback = callback;
		this.url = url;
		parameter = RopUtils.getParams(mList);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		if (MobileOS.getNetworkType(mContext) == -1) {
			return NETWORK_ERROR;
		}

		String httpString = HttpUtils_Post.getString(mContext, parameter, url);
		if (!TextUtils.isEmpty(httpString)) {
			return httpString;
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		try {
			if (result != null) {
				if (result.equals(NETWORK_ERROR)) {
					callback.onFail(result);
				} else if (result.startsWith(SERVER_ERROR)) {
					callback.onFail(SERVER_ERROR);
				} else {
					callback.onSucceed(result);
				}
			} else {
				callback.onFail("请求失败");
			}
		} catch (Exception e) {
			Log.e("LTFTest", e.getMessage());
			callback.onFail("数据解析失败，请重试");
		}
		mContext = null;
		parameter = null;
		url = null;
		callback = null;
	}
}
