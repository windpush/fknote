package com.example.administrator.daiylywriting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.adapter.ChapterAdapter;
import com.example.administrator.daiylywriting.configuration.DefualtConfiguration;
import com.example.administrator.daiylywriting.configuration.IntentConfiguration;
import com.example.administrator.daiylywriting.views.BookHeadView;
import com.example.windpush.comment.ScreenUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by windpush on 16/5/11.
 */
public class BookDetailActivity extends BaseFragmentActivity implements View.OnClickListener {
    public Long bookId;
    public List<Long> chapterIds = new LinkedList<>();
    public ChapterAdapter mAdapter;
    public BookHeadView mHeadview;
    @Bind(R.id.listview)
    ListView mListview;
    @Bind(R.id.btn_action)
    FloatingActionButton mBtnAction;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_bookdetails);
    }

    @Override
    public void getData() {
        mHeadview = new BookHeadView(this);
        mAdapter = new ChapterAdapter();
        Intent intent = getIntent();
        bookId = intent.getLongExtra(IntentConfiguration.KEY_BOOK_ID, DefualtConfiguration.BOOK_ID_DEFAULT);
        BooksVaules booksVaules = GreenDaoService.getGreenDaoService(this).loadBooksVaule(bookId);
        chapterIds.addAll(GreenDaoService.getGreenDaoService(this).getChapterIds(booksVaules.getBookKey()));
    }

    @Override
    public void setStyles() {
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ScreenUtils.getScreenWidth(this), (int) (ScreenUtils.getScreenHeight(this) * 0.49));
        mHeadview.setLayoutParams(params);
        mListview.addHeaderView(mHeadview);
        mAdapter.setDatas(chapterIds);
        mListview.setAdapter(mAdapter);
    }

    @Override
    public void setActions() {
    mBtnAction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_action:
                break;
        }
    }
}
