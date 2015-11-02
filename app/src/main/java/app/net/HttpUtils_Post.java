/**
 * Copyright 2013 ChinaNetCenter
 * All right reserved.
 * @author  Jackie
 * @version 1.0.0
 * @created 2013-10-25 15:22
 */
package app.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.util.Log;

/**
 * Description:
 * 
 * @author Zheng Yuyun
 * @created 2011-12-8 上午11:28:53
 */
public class HttpUtils_Post {
	private static final String ENCODING_UTF_8 = "utf-8";

	// public static final String CONSTSTRING = "EVO";
	// public static final String DEFAULT_USER_AGENT =
	// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)";
	// public static final String DEFAULT_USER_AGENT=
	// "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X; en-us) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53";

	// url 是原始的url，不带前缀。加了前缀的url，需要调用httpGetRequest。
	// handleRedirectByAuto - 是否由httpClient自行处理重定向
	// public static String httClientGet(String url, boolean
	// handleRedirectByAuto) {
	// return httClientGet(url, null, handleRedirectByAuto);
	// }

	// Note:
	// httpUrRLConnection默认下会设置Accept-Encoding：gizp，而且返回自动getInputStream会自动处理GZIP，
	// 但httpClient不会有这个默认设置，
	public static String httClientGet(String url) {

		HttpClient httpclient = new DefaultHttpClient();
		// HttpParams params = httpclient.getParams();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			HttpResponse response = httpclient.execute(httpget);

			// 获取响应状态
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 返回响应内容
					// entity.isChunked 可以判断是否是transfer-encoding=chunked模式，
					// 即使是chunked模式下，toString函数也可以返回内容。
					return EntityUtils.toString(entity, "UTF-8");
					// if (entity.getContentLength() > 0){
					// return EntityUtils.toString(entity, "UTF-8");
					// }else{
					// LogUtil.w("tag", "transfer-encoding chunked = " +
					// entity.isChunked());
					// // 没有content-length字段，i.e。
					// transfer-encoding，则读inputstream直到空为止。
					// InputStream in = entity.getContent();
					//
					// if (entity.isChunked()){
					// in = new GZIPInputStream(in);
					// }
					//
					// StringBuffer sb = new StringBuffer();
					// String readLine;
					// BufferedReader responseReader;
					// // 处理响应流，必须与服务器响应流输出的编码一致
					// responseReader = new BufferedReader(new
					// InputStreamReader(in, ENCODING_UTF_8));
					// while ((readLine = responseReader.readLine()) != null) {
					// sb.append(readLine).append("\n");
					// }
					// responseReader.close();
					// in.close();
					// return sb.toString();
					// }
				}
			} else {
				Log.e("tag", "httpClient response code = " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}

	// public static String httpGetRequestConnect(String requestUrl, boolean
	// needUserAgent){
	// return httpGetRequestConnect (requestUrl, needUserAgent, null, true);
	// }

	// 其实map headerValue里也可以放置User-Agent字段
	// public static String httpGetRequestConnect(String requestUrl, boolean
	// needUserAgent, Map<String, String> headerValue){
	// return httpGetRequestConnect (requestUrl, needUserAgent, headerValue,
	// true);
	// }

	// Note:
	// httpUrRLConnection默认下会设置Accept-Encoding：gizp，而且返回自动getInputStream会自动处理GZIP，
	// public static String httpGetRequestConnect(String requestUrl, boolean
	// needUserAgent, Map<String, String> headerValue, boolean isProxy){
	// try{
	// if (isProxy) {
	// requestUrl = ProxyUtil.proxyUrl(requestUrl);
	// } else {
	// ProxyUtil.stopProxy();
	// }
	// LogUtil.d("tag", "urlGetRequest = " + requestUrl);
	//
	// URL url = new URL(requestUrl);
	// HttpURLConnection connection = null;
	// connection = (HttpURLConnection) url.openConnection();
	// connection.setRequestMethod("GET");
	// connection.setConnectTimeout(10000);
	// connection.setReadTimeout(10000);
	// connection.setInstanceFollowRedirects(false);//自行处理redirect
	// connection.setDoOutput(false);
	// if (needUserAgent){
	// connection.setRequestProperty("User-Agent", DEFAULT_USER_AGENT);
	// }
	// if (null != headerValue && !headerValue.isEmpty()) {
	// Set<String> headerKeySet = headerValue.keySet();
	// for (String headerKey : headerKeySet) {
	// connection.setRequestProperty(headerKey, headerValue.get(headerKey));
	// }
	// }
	// connection.connect();
	//
	// if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	// InputStream in = connection.getInputStream();
	// StringBuffer sb = new StringBuffer();
	// String readLine;
	// BufferedReader responseReader;
	// // 处理响应流，必须与服务器响应流输出的编码一致
	// responseReader = new BufferedReader(new InputStreamReader(in,
	// ENCODING_UTF_8));
	// while ((readLine = responseReader.readLine()) != null) {
	// sb.append(readLine).append("\n");
	// }
	// responseReader.close();
	// connection.getInputStream().close();
	// in.close();
	// connection.disconnect();
	// return sb.toString();
	// } else if (connection.getResponseCode() ==
	// HttpURLConnection.HTTP_MOVED_PERM ||
	// connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP){
	// String location = connection.getHeaderField("Location");
	// return httpGetRequestConnect(location, false, null, isProxy);
	// }else {
	// LogUtil.e("tag", "repsone code = " + connection.getResponseCode());
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	// post支持重定向，后续转get请求
	public static String httpPostRequest(String requestUrl,
			byte[] requestStringBytes) {
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setInstanceFollowRedirects(false);
			connection.setConnectTimeout(10000);// 定义请求时间，在ANDROID中最好是不好超过10秒。否则将被系统回收。设置连接主机超时（单位：毫秒）
			connection.setReadTimeout(10000); // 设置从主机读取数据超时（单位：毫秒）
			connection.setDoInput(true); // 允许输入
			connection.setDoOutput(true); // 允许输出
			// connection.setRequestProperty("Accept-Encoding", "gzip");
			// connection.getOutputStream().write(requestJson);

			// 建立输出流，并写入数据
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(requestStringBytes);
			outputStream.close();

			connection.getOutputStream().flush();
			connection.getOutputStream().close();
			if (connection.getResponseCode() == 200) {
				StringBuffer sb = new StringBuffer();
				String readLine;
				BufferedReader responseReader;
				// 处理响应流，必须与服务器响应流输出的编码一致
				responseReader = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), ENCODING_UTF_8));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				return sb.toString();
			} else {
				Log.e("tag", connection.getResponseCode() + "code");
				return MyAsynctask_Post.SERVER_ERROR + connection.getResponseCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		// 将输入流不断的读，并放到缓冲区中去。直到读完
		while ((len = inStream.read(buffer)) != -1) {
			// 将缓冲区的数据不断的写到内存中去。
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	// public static String getString(Context mContext, String requestJson, int
	// stringId) {
	// return getString(mContext, requestJson, stringId, true);
	// }

	public static String getString(Context mContext, String requestJson,
			String string) {
		return getPostString(mContext, string, requestJson);
	}

	public static String getPostString(Context mContext, String httpString,
			String requestJson) {
		return HttpUtils_Post.httpPostRequest(httpString, requestJson.getBytes());
	}

	// public static String getGetStringFromVideoServer(Context mContext, String
	// requestParam, int stringId, boolean needClientInfo){
	// StringBuilder stringBuilder = new StringBuilder();
	// stringBuilder.append(ProxyUtil.HTTP_HEAD).append(mContext.getResources().getString(R.string.video_parser_server)).append(mContext.getResources().getString(stringId));
	// return getGetString(mContext, stringBuilder.toString(), requestParam,
	// needClientInfo);
	// }

	// public static String getGetString(Context mContext, String httpString,
	// String requestParam, boolean needClientInfo){
	// StringBuilder builder = new StringBuilder();
	// builder.append(httpString);
	// if (!TextUtils.isEmpty(requestParam)){
	// builder.append("?").append(requestParam);
	// }
	// if (needClientInfo){
	// if (TextUtils.isEmpty(requestParam)) {
	// builder.append("?");
	// }else{
	// builder.append("&");
	// }
	// builder.append(getClientInfoJson(mContext));
	// }
	// return HttpUtils.httpGetRequest(builder.toString());
	// }

	// public static String getClientInfoJson(Context context) {
	// long currentTime = System.currentTimeMillis();
	// ClientInfo clientAuthData = new ClientInfo();
	// clientAuthData.setAuthKey(MobileOS.getMd5(currentTime + CONSTSTRING +
	// MobileOS.getIMEI(context)));
	// clientAuthData.setClientVersion(MobileOS.getClientVersion(context));
	// clientAuthData.setImei(MobileOS.getIMEI(context));
	// clientAuthData.setImsi(MobileOS.getIMSI(context));
	// clientAuthData.setTimeStamp("" + currentTime);
	// clientAuthData.setCarrier(MobileOS.getOperatorName(context));
	// clientAuthData.setModel(MobileOS.getDeviceModel());
	// clientAuthData.setSystemVersion(MobileOS.getOsVersion());
	// clientAuthData.setVersionCode(String.valueOf(MobileOS.getVersionCode(context)));
	// clientAuthData.setPackageName(context.getPackageName());
	// clientAuthData.setNetworkType(NetworkTypeUtil.getNetworkString(context));
	// clientAuthData.setPhoneNumber(Pref.getString(Pref.PHONE_NUMBER, context,
	// null));
	//
	// return "clientInfo=" + JSON.toJSONString(clientAuthData);
	// }
}
