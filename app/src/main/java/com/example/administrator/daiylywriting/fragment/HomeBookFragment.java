package com.example.administrator.daiylywriting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.activity.FancyCoverFlowSampleAdapter;
import com.example.administrator.daiylywriting.activity.MainActivity;
import com.example.administrator.daiylywriting.utils.ActivityHelper;
import com.example.administrator.daiylywriting.views.BookFrameView;
import com.example.windpush.comment.BookUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

/**
 * Created by windpush on 16/3/15.
 */
public class HomeBookFragment extends BaseFragment {

    private FancyCoverFlow mFancyCoverFlow;
    private FancyCoverFlowSampleAdapter mAdapter;
    private List<Long> mBookIds = new LinkedList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_homebook,null);
        return view;
    }

    @Override
    public void findViews() {
        mFancyCoverFlow = (FancyCoverFlow) getActivity().findViewById(R.id.gallery_flow);
    }

    @Override
    public void getData() {
//        testAddBook();
        rx.Observable.just(initBookIds())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<List<Long>, List<Long>>() {
                    @Override
                    public List<Long> call(List<Long> longs) {
                        return longs;
                    }
                })
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        initGallery();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @Override
    public void showContent() {

    }

    @Override
    public void setInteract() {
        mFancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Long bookId = mAdapter.getItem(i);
                BooksVaules booksVaules = GreenDaoService.getGreenDaoService(getActivity()).loadBooksVaule(bookId);
                Charpters charpters =new Charpters();
                charpters.setBookKey(booksVaules.getBookKey().toString());
                charpters.setCharpterName("你猜这个是第几章");
                charpters.setCharpterKey(BookUtils.findBookKey(charpters.getCharpterName()));
                GreenDaoService.getGreenDaoService(getActivity()).saveOrReplacChapters(charpters);
                ActivityHelper.showBookDetails(bookId,getActivity());
            }
        });
    }

    @Override
    public void reStartInit() {

    }



    private void initGallery()
    {
        mAdapter = new FancyCoverFlowSampleAdapter(getActivity());
        mAdapter.setBookIds(mBookIds);
        mFancyCoverFlow.setAdapter(mAdapter);
        mFancyCoverFlow.setUnselectedAlpha(1.0f);
        mFancyCoverFlow.setUnselectedSaturation(0.0f);
        mFancyCoverFlow.setUnselectedScale(0.5f);
        mFancyCoverFlow.setSpacing(80);
        mFancyCoverFlow.setMaxRotation(90);
        mFancyCoverFlow.setScaleDownGravity(0.2f);
        mFancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
    }

    private List<Long> initBookIds(){
       mBookIds.addAll(GreenDaoService.getGreenDaoService(getActivity()).getAllBookIds());
        return mBookIds;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void testAddBook(){
        BooksVaules booksVaules =new BooksVaules();
        booksVaules.setBookName("fk google");
        booksVaules.setBookKey(BookUtils.findBookKey("fk google"));
        GreenDaoService.getGreenDaoService(getActivity()).updateOrInsertBooks(booksVaules);

    }
}
