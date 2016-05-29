package com.example.administrator.daiylywriting.Views_For_Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/19.
 */
public class FragmentViews_Tools extends LinearLayout implements View.OnClickListener{
    private com.cengalabs.flatui.views.FlatToggleButton mWithItem;
    private com.cengalabs.flatui.views.FlatToggleButton mWithWebSItem;
    private TextView mWebAddress;
    private com.cengalabs.flatui.views.FlatButton mWebAddressSetting;
    private com.cengalabs.flatui.views.FlatSeekBar mWebTimerSetting;

    public FragmentViews_Tools(Context context) {
        super(context);
        ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_tool,this);
        mWithItem = (com.cengalabs.flatui.views.FlatToggleButton) findViewById(R.id.withItem);
        mWithWebSItem = (com.cengalabs.flatui.views.FlatToggleButton) findViewById(R.id.withWebSItem);
        mWebAddress = (TextView) findViewById(R.id.webAddress);
        mWebAddressSetting = (com.cengalabs.flatui.views.FlatButton) findViewById(R.id.webAddressSetting);
        mWebTimerSetting = (com.cengalabs.flatui.views.FlatSeekBar) findViewById(R.id.webTimerSetting);

    }

    public void changeViewFromSqlite(int timeNum,Boolean addItem,Boolean searchWeb,String webAddress){
            switch (timeNum){
                case 1000*60*60:
                    mWebTimerSetting.setProgress(5);
                    break;
                case 1000*60*60*2:
                    mWebTimerSetting.setProgress(50);
                    break;
                case 1000*60*60*3:
                    mWebTimerSetting.setProgress(95);
                    break;
            }
        mWithItem.setChecked(addItem);
        mWithWebSItem.setChecked(searchWeb);
        if (webAddress==null){}else {
            if (webAddress.length()>10){
                mWebAddress.setText(webAddress);
            }
        }

    }

    public void setTheItem(){
        mWithItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onTheViewListener!=null) {
                    onTheViewListener.onItemModelSelected(isChecked);
                }
                }
        });
    }

    public void setTheWebItem(){
        mWithWebSItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onTheViewListener!=null) {
                onTheViewListener.onWebModelSelected(isChecked,buttonView);
            }}
        });

        mWebAddressSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTheViewListener!=null) {
                onTheViewListener.onChangeTheWebAddress();
            }}
        });
    }

    public void setTheSeek(){
        mWebTimerSetting.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int seekVaule =mWebTimerSetting.getProgress();
                if (seekVaule<25|seekVaule==25){
                    mWebTimerSetting.setProgress(5);
                }else if (seekVaule>25&seekVaule<75){
                    mWebTimerSetting.setProgress(50);
                }else if (seekVaule>75|seekVaule==75){
                    mWebTimerSetting.setProgress(95);
                }
                onTheViewListener.onSeekEnd(seekBar);
            }

        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * view interface
     */
    public static interface OnTheViewListener {
        public void onSeekEnd(SeekBar seekBar);

        public void onChangeTheWebAddress();

        public void onItemModelSelected(Boolean isTrue);

        public void onWebModelSelected(Boolean isTrue,CompoundButton buttonView);
    }

    /**
     * init the interface
     */
    OnTheViewListener onTheViewListener = null;

    /**
     * view listenr callback
     */
    public void setOnTheViewListener(OnTheViewListener listener) {
        onTheViewListener = listener;
    }

}

