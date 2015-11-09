package app;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.miweikeij.app.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.litepal.LitePalApplication;

import java.util.Calendar;

import app.activity.AppStartActivity;
import app.activity.mywork.ParterMessageActivity;
import app.entity.CraftsResult;
import app.entity.UmengMessageResult;
import app.entity.UmentMessage;
import app.utils.Config;
import app.utils.JsonUtil;
import app.utils.Pref;
import app.utils.Uihelper;

/**
 * Created by Administrator on 2015/10/10.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;
    private static boolean isCurrent;
    private PushAgent mPushAgent;
    private Intent notificationIntent;

    public static boolean isCurrent() {
        return isCurrent;
    }

    public static void setIsCurrent(boolean isCurrent) {
        MyApplication.isCurrent = isCurrent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 参考集成文档的1.6.3
             * http://dev.umeng.com/push/android/integration#1_6_3
             * */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        //自定义处理消息
                        Uihelper.trace("=============" + msg.custom + "===============");
                        processCustomMessage(context, msg);
                    }
                });
            }

            /**
             * 参考集成文档的1.6.4
             * http://dev.umeng.com/push/android/integration#1_6_4
             * */
            @Override
            public Notification getNotification(Context context,
                                                UMessage msg) {
                return super.getNotification(context, msg);
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 参考集成文档的1.6.2
         * http://dev.umeng.com/push/android/integration#1_6_2
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void processCustomMessage(Context context, UMessage msg) {

        String custom = msg.custom;
        if (TextUtils.isEmpty(custom)) {
            return;
        }
        UmengMessageResult message = JsonUtil.parseObject(custom, UmengMessageResult.class);
        if (message == null) {
            return;
        }
        String title = message.getCustom().getTitle();
        String content = message.getCustom().getText();

        if (!MyApplication.getInstance().isCurrent()) {
            notificationIntent = new Intent(context, AppStartActivity.class);
            Pref.saveBoolean(Pref.PUSH, true, context);
        } else {
            notificationIntent = new Intent(context, ParterMessageActivity.class);
        }

        int requestCode = (int) System.currentTimeMillis();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews contentViews = new RemoteViews("com.miweikeij.app", R.layout.layout_jpush);

        final Calendar mCalendar = Calendar.getInstance();
        int mHour;
        boolean is24HourFormat = android.text.format.DateFormat.is24HourFormat(context);
        if (is24HourFormat) {
            mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        } else {
            mHour = mCalendar.get(Calendar.HOUR);
        }
        int mMinuts = mCalendar.get(Calendar.MINUTE);

        // 通过控件的Id设置属性
        contentViews.setTextViewText(R.id.titleNo, title);
        contentViews.setTextViewText(R.id.textNo, content);
        String string_Minutes = "" + mMinuts;
        if (mMinuts < 10) {
            string_Minutes = "0" + mMinuts;
        }
        contentViews.setTextViewText(R.id.timeNo, mHour + ":" + string_Minutes);
        String tickerText = context.getResources().getString(R.string.app_name);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setTicker(tickerText);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setContent(contentViews);
        mBuilder.setAutoCancel(true);
        mBuilder.setDefaults(Notification.DEFAULT_ALL);

        // 定义NotificationManager
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
        mNotificationManager.notify(message.getCustom().getMessageId(), mBuilder.build());

    }

    private void initImageLoader(Context context) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(50 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                        // default
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs()
                .threadPoolSize(5).build();
        ImageLoader.getInstance().init(config);

    }

    public static MyApplication getInstance() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }

        return myApplication;
    }
}
