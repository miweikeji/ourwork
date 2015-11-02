/**
 *  
 * 日    期：12-6-2
 */
package app.net;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * 
 * @version 1.0
 */
public class RopUtils {

	// private static final Logger logger =
	// LoggerFactory.getLogger(RopUtils.class);
	public static List<String> ignoreParamNames = new ArrayList<String>();
	static {
		ignoreParamNames.add("v");
		ignoreParamNames.add("method");
		ignoreParamNames.add("sign");
		ignoreParamNames.add("userIcon");
		ignoreParamNames.add("img1");
		ignoreParamNames.add("img2");
		ignoreParamNames.add("img3");
		ignoreParamNames.add("img4");
		ignoreParamNames.add("img5");
		ignoreParamNames.add("img6");
		ignoreParamNames.add("pic1");
		ignoreParamNames.add("pic2");
		ignoreParamNames.add("pic3");
		ignoreParamNames.add("image");

	}

	/**
	 * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
	 * uppercase(hex(sha1(secretkey1value1key2value2...secret))
	 *
	 *            需要签名的参数名
	 * @param paramValues
	 *            参数列表
	 * @param secret
	 * @return
	 */
	public static String sign(Map<String, String> paramValues, String secret) {
		return sign(paramValues, ignoreParamNames, secret);
	}

	/**
	 * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
	 * 
	 * @param paramValues
	 * @param ignoreParamNames
	 * @param secret
	 * @return
	 */
	public static String sign(Map<String, String> paramValues,
			List<String> ignoreParamNames, String secret) {
		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<String>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);

			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			byte[] sha1Digest = getSHA1Digest(sb.toString());
			return byte2hex(sha1Digest);
		} catch (IOException e) {
			throw new RopException(e);
		}
	}

	public static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	private static byte[] getMD5Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			bytes = md.digest(data.getBytes("UTF-8"));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}

	public static String getParams(Map<String, String> form) {
		StringBuffer params = new StringBuffer();
		int i = 0;
		for (Map.Entry<String, String> entry : form.entrySet()) {
			if (i > 0) {
				params.append("&");
			}
			params.append(entry.getKey());
			params.append("=");
			params.append(entry.getValue());
			i++;
		}
		return params.toString();

	}

	public static String getParamsIcon(Map<String, String> form) {
		StringBuffer params = new StringBuffer();
		int i = 0;
		for (Map.Entry<String, String> entry : form.entrySet()) {
			if (i > 0) {
				params.append("&");
			}
			try {
				params.append(entry.getKey());
				params.append("=");
				String v = URLEncoder.encode(entry.getValue(), "UTF-8");
				params.append(v);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}

			i++;
		}
		return params.toString();

	}

}
