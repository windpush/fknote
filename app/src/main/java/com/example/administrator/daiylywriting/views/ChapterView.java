package com.example.administrator.daiylywriting.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by windpush on 16/5/11.
 */
public class ChapterView extends RelativeLayout {
    @Bind(R.id.chaptername)
    TextView chaptername;
    @Bind(R.id.chapternum)
    TextView chapternum;

    Charpters mChapters;

    public ChapterView(Context context) {
        super(context);
        baseInit();
    }

    public ChapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        baseInit();
    }

    public ChapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        baseInit();
    }

    private void baseInit() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_chapterview, this);
        ButterKnife.bind(this);
    }

    public void init(Charpters charpters){
        mChapters = charpters;
        initStyle();
    }

    public void initStyle(){
        chaptername.setText(mChapters.getCharpterName());
    }
}
