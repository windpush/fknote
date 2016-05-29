package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Adapter_BooksRecycle;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.MyOwnViews.MyLayoutManager;
import com.example.administrator.daiylywriting.Views_For_Layout.FragmentViews_Nearly_Books;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Fragment_Nearly_Books extends BaseFragment {
    private  MyLayoutManager mBookLayoutManager,mChapterLayoutManager;
    private FragmentViews_Nearly_Books fragmentViews_nearly;
    private Adapter_BooksRecycle booksRecycleAdapter;
    private static final String KEY_TRANSITION_EFFECT = "transition_effect";

    private int mCurrentTransitionEffect = JazzyHelper.FLIP;
    private JazzyRecyclerViewScrollListener jazzyScrollListener;
    private ArrayList<BooksVaules> bookModels =new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentViews_nearly=new FragmentViews_Nearly_Books(getActivity());
        return fragmentViews_nearly;
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void findViews() {

    }

    @Override
    public void getData() {
        //上方横着的RcyclerView的适配
        GreenDaoService greenDaoService =GreenDaoService.getGreenDaoService(getActivity());
        bookModels.clear();
        bookModels.addAll(greenDaoService.loadAllBookVaules());
        booksRecycleAdapter =new Adapter_BooksRecycle(getActivity(),bookModels,fragmentViews_nearly.mChapterList_recycler);
        fragmentViews_nearly.mHot_fragment_recycler.setAdapter(booksRecycleAdapter);
    }

    @Override
    public void showContent() {
        //RcyclerView的初始化设置

        mChapterLayoutManager =new MyLayoutManager(getActivity());
        mBookLayoutManager = new MyLayoutManager(getActivity());
        mChapterLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBookLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentViews_nearly.mHot_fragment_recycler.setLayoutManager(mBookLayoutManager);
        fragmentViews_nearly.mChapterList_recycler.setLayoutManager(mChapterLayoutManager);
//        jazzyScrollListener = new JazzyRecyclerViewScrollListener();
//        setupJazziness(mCurrentTransitionEffect);
//        fragmentViews_nearly.mChapterList_recycler.setOnScrollListener(jazzyScrollListener);

    }

    @Override
    public void setInteract() {

    }

    @Override
    public void reStartInit() {

    }
    private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        jazzyScrollListener.setTransitionEffect(mCurrentTransitionEffect);
    }

}
