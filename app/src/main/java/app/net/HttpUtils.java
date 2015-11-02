package app.net;

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import app.utils.MobileOS;

/**
 * Created by Administrator on 2015/9/4.
 */
public class HttpUtils {

    public static String httpPostRequest(Context context, String url, List<Param> list) {
        if (MobileOS.getNetworkType(context) == -1) {
            return MyAsyncTask.NETWORK_ERROR;
        }
        final OkHttpClient client = new OkHttpClient();

        StringBuffer stringBuffer = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (Param param : list) {
                stringBuffer.append("&").append(param.key).append("=" + param.value);
            }
            stringBuffer.deleteCharAt(0);
        }
      String  realUrl=  url + "?" + stringBuffer;
        final Request request = new Request.Builder()
                .url(realUrl)
                .build();

        try {
            Response response = null;
            Call call = client.newCall(request);
            response = call.execute();

            if (response.isSuccessful()) {
                String httpString = response.body().string();
                Log.e("realUrl:", realUrl);
                Log.e("json:", httpString);
                return httpString;
            }
            return null;
        } catch (IOException e) {
            return MyAsyncTask.SERVER_ERROR + e.getMessage();
        }
    }


    /**
     * 对List<Param>按key进行升序排序
     */
    public static void sortParam(List<Param> list) {
        if (list == null) {
            return;
        }
        Collections.sort(list, new Comparator<Param>() {
            @Override
            public int compare(Param lhs, Param rhs) {
                return lhs.key.compareToIgnoreCase(rhs.key);
            }
        });
    }

}
