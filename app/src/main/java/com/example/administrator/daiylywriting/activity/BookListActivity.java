package com.example.administrator.daiylywriting.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Adapter_Chapter;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;

/**
 * Created by windpush on 16/3/2.
 */
public class BookListActivity extends BaseFragmentActivity {
    private RecyclerView mRecyclerView;
    private Adapter_Chapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_bookslist);

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

            // improve performance if you know that changes in content
            // do not change the size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an com.example.administrator.daiylywriting.adapter (see also next example)
        }

    @Override
    public void getData() {

    }

    @Override
    public void setStyles() {

    }

    @Override
    public void setActions() {

    }
}
