package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.Views_For_Layout.AdapterView_New;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/11.
 */
public class Adapter_News extends BaseAdapter {
    private Context activity;
    List<WebData> webDatas=new LinkedList<>();
    public Adapter_News(Context activity, List<WebData> webDatas){
        this.activity=activity;
        this.webDatas=webDatas;
        Collections.reverse(webDatas);
    }
//    @Override
//    public int getCount() {
//        return nowHappentses.size();
//    }
@Override
public int getCount() {
    return webDatas.size();
}
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterView_New adapterView_new=null;
        if (convertView==null){
            adapterView_new = new AdapterView_New(activity);
        }else {
            adapterView_new=(AdapterView_New)convertView;
        }
//        adapterView_new.mListPartNewVauleTextView.setText(Html.fromHtml(nowHappentses.get(position).getNews()));
        WebData webData=webDatas.get(position);
        adapterView_new.mListPartNewVauleTextView.setText(webData.getBookName().toString()+"："+webData.getClickNumber()+"点击"+webData.getLikeNumber()+"推荐"+webData.getTimeDate());
        return adapterView_new;
    }
}
