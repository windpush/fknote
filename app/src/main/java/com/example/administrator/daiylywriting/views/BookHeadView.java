package com.example.administrator.daiylywriting.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.example.administrator.daiylywriting.R;
import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.ButterKnife;

/**
 * Created by windpush on 16/5/20.
 */
public class BookHeadView extends PercentRelativeLayout {
    public BookHeadView(Context context) {
        super(context);
        baseInit();
    }

    public BookHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        baseInit();
    }

    public BookHeadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        baseInit();
    }

    private void baseInit() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_headview, this);
        ButterKnife.bind(this);
    }


}
