package com.example.administrator.daiylywriting.MyService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;
import com.igexin.sdk.PushConsts;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/3/8.
 */
public class SecretMessageFromGT extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.toString());
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {

            case PushConsts.GET_MSG_DATA:
                // 获取透传（payload）数据

                byte[] payload = bundle.getByteArray("payload");
                if (payload != null) {
                    String data = new String(payload);
                    JSONTokener jsonTokener = new JSONTokener(data.replace(" ", ""));
                    try {
                        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                        String dmVaule =jsonObject.getString("danMuVaule");
                        String color =jsonObject.getString("color");
                        Intent dmIntent = new Intent(SomeStaticThing.DM_BROADCAST_TAG);
                        dmIntent.putExtra("dm", dmVaule);
                        dmIntent.putExtra("color",color);
                        Log.d("GetuiSdkDemo", "onReceive() action=" + dmVaule+color);
                        /**
                         *Tell me Somebody use dm tools
                         */
//                        SimpleDateFormat timeKey = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//                        Date date =new Date();
//                        CharSequence from = timeKey.format(date).toString();
//                        CharSequence message = dmVaule;
//
//                        Intent intentNy = new Intent();
//                        ComponentName componentName = new ComponentName("com.lixueli",
//                                "com.lixueli.Test");
//                        intentNy.setComponent(componentName);
//                        intentNy.setAction("android.intent.action.MAIN");
//                        intentNy.addCategory("android.intent.category.LAUNCHER");
//                        intentNy.addFlags(Notification.FLAG_ONGOING_EVENT);

                        // The PendingIntent to launch our activity if the user selects this notification
//                        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intentNy, 0);
//                        // construct the Notification object.
//                        Notification notif = new Notification(R.drawable.ttmz,dmVaule,
//                                System.currentTimeMillis());
//                        notif.flags = Notification.FLAG_ONGOING_EVENT ;
//                        notif.defaults=Notification.DEFAULT_SOUND;
//                        notif.setLatestEventInfo(context, from, message, contentIntent);

                        // look up the notification manager service
//                        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
//                        nm.notify(10, notif);

                        context.sendBroadcast(dmIntent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    // TODO:接收处理透传（payload）数据
                }
                break;
            case PushConsts.GET_CLIENTID:
                // 获取ClientID(CID)
                String cid = bundle.getString("clientid");
                Log.d("GetuiSdkDemo", "Got ClientID:" + cid);
                // TODO:
                /* 第三方应用需要将ClientID上传到第三方服务器，并且将当前用户帐号和ClientID进行关联，
                以便以后通过用户帐号查找ClientID进行消息推送。有些情况下ClientID可能会发生变化，为保证获取最新的ClientID，
                请应用程序在每次获取ClientID广播后，都能进行一次关联绑定 */
                break;
            //添加其他case
            //.........
        }
    }


}