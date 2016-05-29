package com.example.administrator.daiylywriting.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.adapter.ViewpagerAdapter;
import com.zhy.android.percent.support.PercentLinearLayout;

import be.webelite.ion.IconView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windpush on 16/5/3.
 */
public class MainActivity extends BaseFragmentActivity {

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.div_menu)
    PercentLinearLayout divMenu;
    @Bind(R.id.cardview_add)
    CardView mBtnAdd;
    @Bind(R.id.btn_books)
    IconView mBtnBooks;
    @Bind(R.id.btn_socials)
    IconView mBtnSocials;
    @Bind(R.id.btn_profile)
    IconView mBtnProfile;
    @Bind(R.id.btn_setting)
    IconView mBtnSetting;

    private ViewpagerAdapter mAdapter ;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getData() {
        mAdapter = new ViewpagerAdapter(getSupportFragmentManager());
    }

    @Override
    public void setStyles() {
        mViewpager.setAdapter(mAdapter);
    }

    @Override
    public void setActions() {

    }

    @OnClick({R.id.cardview_add,R.id.btn_books, R.id.btn_socials, R.id.btn_profile, R.id.btn_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_books:
                break;
            case R.id.btn_socials:
                break;
            case R.id.btn_profile:
                break;
            case R.id.btn_setting:
                break;
            case R.id.cardview_add:
                break;
        }
    }
}
