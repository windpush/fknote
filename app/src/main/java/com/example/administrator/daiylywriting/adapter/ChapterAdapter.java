package com.example.administrator.daiylywriting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoApplication;
import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.BooksSqilte.DaoMaster;
import com.example.administrator.daiylywriting.views.ChapterView;
import com.example.windpush.comment.ScreenUtils;

/**
 * Created by windpush on 16/5/11.
 */
public class ChapterAdapter extends DataAdapter<Long> {
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChapterView chapterView;
        if (view == null){
            chapterView = new ChapterView(viewGroup.getContext());
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ScreenUtils.getScreenWidth(viewGroup.getContext()),ScreenUtils.getScreenHeight(viewGroup.getContext())/4);
            chapterView.setLayoutParams(layoutParams);
            view = chapterView;
        }else {
            chapterView = (ChapterView) view;
        }
        Long chapterId = (Long) getItem(i);
        Charpters chapter = GreenDaoService.getGreenDaoService(viewGroup.getContext()).findChapterById(chapterId);
        chapterView.init(chapter);
        return view;
    }
}
