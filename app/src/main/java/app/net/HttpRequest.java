package app.net;

import android.content.Context;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import app.entity.Meta;
import app.utils.JsonUtil;

/**
 * Created by Jackie on 2015/9/4.
 */
public class HttpRequest {

    private static List<Param> mList;

    /**
     * 测试
     */
    public static void testHttp(Context context, final ICallback<Meta> callback, String mobile,
                                String type) {
        if (mList == null) {
            mList = new ArrayList<Param>();
        }
        mList.clear();
        mList.add(new Param("mobile", mobile));
        mList.add(new Param("type", type));

        new MyAsyncTask(context, Urls.test, mList, new ICallback<String>() {

            @Override
            public void onSucceed(String result) {

                Meta test = JsonUtil.parseObject(result, Meta.class);
                if (test.getStatus() == 0) {
                    callback.onSucceed(test);
                } else {
                    callback.onFail(test.getMsg());
                }
            }

            @Override
            public void onFail(String error) {
                callback.onFail(error);
            }
        }).executeOnExecutor();
    }


}
