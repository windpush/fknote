package com.example.administrator.daiylywriting.FragmentPage.Fragment_Tools;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.Setting;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.MyOwnViews.MyDialog;
import com.example.administrator.daiylywriting.MyService.SearchBookStatus;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.Views_For_Layout.FragmentViews_Tools;

import java.util.StringTokenizer;

/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Tool extends BaseFragment {
    private FragmentViews_Tools fragmentViews_tools;
    private GreenDaoService greenDaoService;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentViews_tools = new FragmentViews_Tools(getActivity());
        greenDaoService = GreenDaoService.getGreenDaoService(getActivity());
        fragmentViews_tools.setTheSeek();
        fragmentViews_tools.setTheItem();
        fragmentViews_tools.setTheWebItem();
        return fragmentViews_tools;
    }

    @Override
    public void findViews() {

    }

    @Override
    public void getData() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void setInteract() {
        fragmentViews_tools.setOnTheViewListener(new FragmentViews_Tools.OnTheViewListener() {
            @Override
            public void onSeekEnd(SeekBar seekBar) {
                switch (seekBar.getProgress()) {
                    /**
                     *The seekBar is just three part 5 50 and 95 ,to make the ModleShow is right
                     */
                    case 5:
                       Setting setting=greenDaoService.loadSetting().get(0);
                        setting.setTimeToSearch(1000*60*60);
                        greenDaoService.upDateOrAddSetting(setting);
                        break;
                    case 50:
                        Setting setting1=greenDaoService.loadSetting().get(0);
                        setting1.setTimeToSearch(1000*60*60*2);
                        greenDaoService.upDateOrAddSetting(setting1);
                        break;
                    case 95:
                        Setting setting2=greenDaoService.loadSetting().get(0);
                        setting2.setTimeToSearch(1000*60*60*3);
                        greenDaoService.upDateOrAddSetting(setting2);
                        break;
                }


            }

            @Override
            public void onChangeTheWebAddress() {
                MyDialog myDialog =new MyDialog();
                final Dialog dialog =  myDialog.webAddress(getActivity(), R.style.popupDialog);
                Window window = dialog.getWindow();
                final EditText bookNameVaule = (EditText) window.findViewById(R.id.dialog_Vaule);
                final TextView okTextView = (TextView) window.findViewById(R.id.dialog_confirm);
                okTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Setting setting=greenDaoService.loadSetting().get(0);
                        setting.setWebAddress(bookNameVaule.getText().toString());
                        greenDaoService.upDateOrAddSetting(setting);
                        fragmentViews_tools.changeViewFromSqlite(setting.getTimeToSearch(),setting.getIsItemAdd(),setting.getIsWebSearch(),setting.getWebAddress());
                        dialog.dismiss();
                    }
                });

                okTextView.setClickable(false);
                bookNameVaule.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            StringTokenizer st1 = new StringTokenizer(s.toString());
                            String str = "";
                            while (st1.hasMoreTokens()) {
                                str = str + st1.nextToken(" ");
                            }
                            str = str.trim();
                            if (str.length() > 10) {
                                okTextView.setBackgroundResource(R.drawable.buttonrandiusbordergreen);
                                okTextView.setTextColor(Color.WHITE);
                                okTextView.setClickable(true);
                            } else {
                                okTextView.setBackgroundResource(R.drawable.buttonradiusborder);
                                okTextView.setTextColor(Color.parseColor("#444444"));
                                okTextView.setClickable(false);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                });
            }

            @Override
            public void onItemModelSelected(Boolean isTrue) {
                    Setting setting=greenDaoService.loadSetting().get(0);
                    setting.setIsItemAdd(isTrue);
                    greenDaoService.upDateOrAddSetting(setting);
            }

            @Override
            public void onWebModelSelected(Boolean isTrue, CompoundButton buttonView) {
                Setting setting = greenDaoService.loadSetting().get(0);

                    if (isTrue) {
                        if (setting.getWebAddress() == null){
                            SomeStaticThing.toastSomthing(getActivity(), "请先设置您的小说网址~");
                            isTrue=false;
                            buttonView.setChecked(false);
                        } else {
                            if (setting.getIsWebSearch()) {
                            } else {
                                startAlarmService();
                                SomeStaticThing.toastSomthing(getActivity(), "数据采集已开启,只会消耗极少量的流量,请放心使用~");
                            }
                        }
                    } else {
                        cancleAlarm();
                        SomeStaticThing.toastSomthing(getActivity(), "数据采集已关闭~");
                    }
                    setting.setIsWebSearch(isTrue);
                    greenDaoService.upDateOrAddSetting(setting);
                }
        });

        Setting setting =greenDaoService.loadSetting().get(0);
        fragmentViews_tools.changeViewFromSqlite(setting.getTimeToSearch(),setting.getIsItemAdd(),setting.getIsWebSearch(),setting.getWebAddress());

    }

    private void startAlarmService() {
        PendingIntent pend = PendingIntent.getService(getActivity(), 0,
                new Intent(getActivity(), SearchBookStatus.class), 0);
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        long firstTime = SystemClock.elapsedRealtime();
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, greenDaoService.loadSetting().get(0).getTimeToSearch(), pend);
//        SomeStaticThing.toastSomthing(getActivity(), "开启点击");
//        Intent intent = new Intent(getActivity(), SearchBookStatus.class);
//        // TODO Add extras if required.
//        getActivity().startService(intent);
    }

     private void cancleAlarm(){
         PendingIntent pend = PendingIntent.getService(getActivity(), 0,
                 new Intent(getActivity(), SearchBookStatus.class), 0);
         AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
         am.cancel(pend);
     }

    @Override
    public void reStartInit() {

    }
}
