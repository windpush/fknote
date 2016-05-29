package com.example.administrator.daiylywriting.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by windpush on 16/3/27.
 */
public class BookFrameView extends RelativeLayout {
    @Bind(R.id.img_bookframe)
    ImageView mImgBookframe;
    @Bind(R.id.tx_bookname)
    TextView mTxBookname;
    private Long mBookId;
    private long mTimeLastClicked;
    private BooksVaules mBookVaules;

    public BookFrameView(Context context) {
        super(context);
        initViews();
    }

    public BookFrameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    public BookFrameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_bookdetailview, this);
        ButterKnife.bind(this);
        initAction();
    }

    public void bindData(Long bookId) {
        mBookId = bookId;
        if (bookId != null) {
            mBookVaules = GreenDaoService.getGreenDaoService(getContext()).getBookById(bookId);
        }
        setStyles();
    }

    public void setStyles() {
        mTxBookname.setText("魔起");
        mImgBookframe.setImageResource(R.drawable.bookb);
    }


    public void say() {
        Toast.makeText(getContext().getApplicationContext(), mBookVaules == null ? "null" : mBookVaules.getBookName()+"被点击啦", Toast.LENGTH_LONG).show();

    }

    public void initAction() {
        mImgBookframe.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mTimeLastClicked = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if ((System.currentTimeMillis() - mTimeLastClicked) < 300) {
                            Toast.makeText(getContext().getApplicationContext(), "被点击啦", Toast.LENGTH_LONG).show();
                        }
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }
}
