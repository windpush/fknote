package com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData;

import android.content.Context;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/2/17.
 */
public class ChartDatas_ClickAndLike {
    private GreenDaoService greenDaoService;
    public ChartDatas_ClickAndLike(GreenDaoService greenDaoService){
        this.greenDaoService=greenDaoService;
    }

    public  List<BookAddVaule> clickNumbersOfToday(Context context){
        SimpleDateFormat timeDayKey = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String timeDayVaule = timeDayKey.format(date);
        WebBookDatas webBookDatas =greenDaoService.getParticularWebData(timeDayVaule);
        List<WebData> dataShow =new LinkedList<>();
        dataShow.clear();
        List<BookAddVaule> bookAddVaules =new LinkedList<>();
        bookAddVaules.clear();
        dataShow=greenDaoService.getDayWebData(timeDayVaule);
        if (dataShow.size()<2){
            SomeStaticThing.toastSomthing(context,"数据正在采集中,请等上一两个小时先~");
        }else {
            for (int i =0;i<dataShow.size();i++){
                if (i>0){
                  int clickNum= dataShow.get(i).getClickNumber()-dataShow.get(i-1).getClickNumber();
                  int likeNum = dataShow.get(i).getLikeNumber()-dataShow.get(i-1).getLikeNumber();
                  String timeTo=dataShow.get(i).getTimeHour()+"点";
                  String timeFrom =dataShow.get(i-1).getTimeHour()+"点";
                  BookAddVaule bookAddVaule =new BookAddVaule();
                    bookAddVaule.setAddClickNumber(clickNum);
                    bookAddVaule.setAddLikeNumber(likeNum);
                    bookAddVaule.setTimeVaule(timeTo+"至"+timeFrom);
                    bookAddVaules.add(bookAddVaule);
                }
            }

        }
        return bookAddVaules;
    }

}
