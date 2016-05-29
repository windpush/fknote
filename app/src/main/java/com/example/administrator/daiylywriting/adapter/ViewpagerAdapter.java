package com.example.administrator.daiylywriting.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.fragment.HomeBookFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by windpush on 16/5/9.
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {
    List<BaseFragment> mFragments = new LinkedList<>();
    private HomeBookFragment homeBookFragment;
    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
        homeBookFragment = new HomeBookFragment();
        mFragments.add(homeBookFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
