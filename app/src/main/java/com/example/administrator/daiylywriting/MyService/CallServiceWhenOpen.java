package com.example.administrator.daiylywriting.MyService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;

/**
 * Created by Administrator on 2015/2/1.
 */
public class CallServiceWhenOpen extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GreenDaoService greenDaoService=GreenDaoService.getGreenDaoService(context);
        if (greenDaoService.loadSetting().size()>0) {
         if (greenDaoService.loadSetting().get(0).getIsWebSearch()) {
             Intent service = new Intent(context, SearchBookStatus.class);
             context.startService(service);
         }
        }
}
}
