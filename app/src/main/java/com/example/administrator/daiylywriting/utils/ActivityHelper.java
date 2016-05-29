package com.example.administrator.daiylywriting.utils;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.daiylywriting.activity.BookDetailActivity;
import com.example.administrator.daiylywriting.configuration.IntentConfiguration;

/**
 * Created by windpush on 16/5/11.
 */
public class ActivityHelper {
    public static void showBookDetails(Long bookId,Context context){
        if (bookId == null){
            return;
        }
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(IntentConfiguration.KEY_BOOK_ID,bookId);
        context.startActivity(intent);
    }
}
