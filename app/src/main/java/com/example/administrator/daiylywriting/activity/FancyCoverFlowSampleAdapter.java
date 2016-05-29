package com.example.administrator.daiylywriting.activity;

/**
 * Created by windpush on 16/3/19.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.views.BookFrameView;
import com.example.windpush.comment.ScreenUtils;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

    // =============================================================================
    // Private members
    // =============================================================================

    private int mScreenWidth;
    private int mScreenHeight;
    private List<Long> mBookIds = new LinkedList();

    public FancyCoverFlowSampleAdapter(Context context) {
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        mScreenHeight = ScreenUtils.getScreenHeight(context);
    }

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    public void setBookIds(List<Long> bookIds) {
        mBookIds = bookIds;
    }

    @Override
    public int getCount() {
        return mBookIds.isEmpty() ? 0 : mBookIds.size();
    }

    @Override
    public Long getItem(int i) {
        return mBookIds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(final int i, View reuseableView, ViewGroup viewGroup) {
        BookFrameView bookFrameView = new BookFrameView(viewGroup.getContext());
        bookFrameView.setLayoutParams(new FancyCoverFlow.LayoutParams(mScreenWidth / 4, mScreenWidth*4/ 9));
        bookFrameView.bindData(getItem(i));
        return bookFrameView;
    }
}