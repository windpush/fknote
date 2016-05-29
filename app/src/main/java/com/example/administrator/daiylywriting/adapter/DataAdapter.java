package com.example.administrator.daiylywriting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by windpush on 16/5/11.
 */
public class DataAdapter<T> extends BaseAdapter {
    List<T> mDatas = new LinkedList<>();
    public void setDatas(Collection<T> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<T> getDatas(){
        return mDatas;
    }

    public void addDatas(Collection<T> datas,boolean needFirst){
        if (!needFirst) {
            mDatas.addAll(datas);
        }else {
            mDatas.addAll(0,datas);
        }
    }

    public void addData(T data,boolean needFirst){
        if (!needFirst){
            mDatas.add(data);
        }else {
            mDatas.add(0,data);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
