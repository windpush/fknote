package com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData;

import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;

/**
 * Created by Administrator on 2015/2/18.
 */
public class BookAddVaule  {
    String timeVaule ;
    int addClickNumber;
    int addLikeNumber;
    public BookAddVaule(){

    }

    public void setTimeVaule(String timeVaule){
        this.timeVaule=timeVaule;
    }

    public void setAddLikeNumber(int addLikeNumber){
        this.addLikeNumber=addLikeNumber;
    }

    public void setAddClickNumber(int addClickNumber){
        this.addClickNumber=addClickNumber;
    }
    public String getTimeVaule(){
        return timeVaule;
    }

    public int getAddLikeNumber(){
        return addLikeNumber;
    }

    public int getAddClickNumber(){
        return addClickNumber;
    }
}
