package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.AnimateForWriting.CubeTransformer;
import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Fragment_Nearly_Adapter;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.Views_For_Layout.FragmentViews_Nearly;
import com.example.administrator.daiylywriting.Views_For_Layout.FragmentViews_Nearly_Books;

import java.util.List;


/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Nearly extends BaseFragment {


    private static FragmentViews_Nearly fragmentViews_nearly;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentViews_nearly = new FragmentViews_Nearly(getActivity(), this);
        return fragmentViews_nearly;
    }


    @Override
    public void findViews() {

//        int myId =getResources().getIdentifier("yes","id","com.example.administrator.daiylywriting");
//        useId(myId);
    }
//    private void useId(int id){}

    @Override
    public void getData() {
//        viewpager的适配器
    }

    @Override
    public void showContent() {
        /*子页的ViewPager
        * */
        GreenDaoService greenDaoService = GreenDaoService.getGreenDaoService(getActivity());

        fragmentViews_nearly.totalNumber.setText(greenDaoService.getAllNumers() + "字");
    }

    @Override
    public void setInteract() {

    }

    @Override
    public void reStartInit() {
        GreenDaoService greenDaoService = GreenDaoService.getGreenDaoService(getActivity());

        fragmentViews_nearly.totalNumber.setText(greenDaoService.getAllNumers() + "字");
    }


    /**
     * 用于选中之后跳转到添加小说页面
     */
    public static void gotoBook() {
        fragmentViews_nearly.mRecentViewPage.setCurrentItem(1);
    }


}
