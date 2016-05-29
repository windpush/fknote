package com.example.windpush.comment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by windpush on 16/4/3.
 */
public class BookUtils {
    public static String findBookKey(String bookName) {
    SimpleDateFormat timeKey = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    String timeKeyVaule = timeKey.format(date);
    return bookName+timeKeyVaule;
    }
}
