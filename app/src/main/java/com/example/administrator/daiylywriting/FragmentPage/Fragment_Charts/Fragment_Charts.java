package com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData.BookAddVaule;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData.ChartDatas_ClickAndLike;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData.WebBookDatas;
import com.example.administrator.daiylywriting.MyChartsView.MySplineChart;
import com.example.administrator.daiylywriting.MyChartsView.PieChartTwo;
import com.example.administrator.daiylywriting.MyChartsView.PieDateStruct;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.MyService.CallServiceWhenOpen;
import com.example.administrator.daiylywriting.MyService.SearchBookStatus;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Charts extends BaseFragment {
    private static int SEVENDAY = 7;
    private static int TODAY = 6;
    private PieChart pieChart;
    private List<PieData> chartData = new LinkedList<>();
    private GreenDaoService greenDaoService;
    private RelativeLayout mChartsRadio_DailyNumbersDiv;
    private TextView mChartsRadio_DailyNumbersImg;
    private TextView mChartsRadio_DailyNumbersText;
    private RelativeLayout mChartsRadio_ClickDiv;
    private TextView mChartsRadio_ClickImg;
    private TextView mChartsRadio_ClickText;
    private RelativeLayout mChartsRadio_CollectionDiv;
    private TextView mChartsRadio_CollectionImg;
    private TextView mChartsRadio_CollectionText;
    private LinearLayout mChartsDiv;
    private MySplineChart webDataChart;
    private com.example.administrator.daiylywriting.MyChartsView.PieChartTwo mBookPieChart;
    private Handler handler = new Handler();
    private Document doc = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }

    @Override
    public void findViews() {
        mChartsRadio_DailyNumbersDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_DailyNumbersDiv);
        mChartsRadio_DailyNumbersImg = (TextView) getActivity().findViewById(R.id.chartsRadio_DailyNumbersImg);
        mChartsRadio_DailyNumbersText = (TextView) getActivity().findViewById(R.id.chartsRadio_DailyNumbersText);
        mChartsRadio_ClickDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_ClickDiv);
        mChartsRadio_ClickImg = (TextView) getActivity().findViewById(R.id.chartsRadio_ClickImg);
        mChartsRadio_ClickText = (TextView) getActivity().findViewById(R.id.chartsRadio_ClickText);
        mChartsRadio_CollectionDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_CollectionDiv);
        mChartsRadio_CollectionImg = (TextView) getActivity().findViewById(R.id.chartsRadio_CollectionImg);
        mChartsRadio_CollectionText = (TextView) getActivity().findViewById(R.id.chartsRadio_CollectionText);
        mChartsDiv = (LinearLayout) getActivity().findViewById(R.id.chartsDiv);
//        webDataChart= (MySplineChart) getActivity().findViewById(R.id.webDataChart);
        mBookPieChart = (com.example.administrator.daiylywriting.MyChartsView.PieChartTwo) getActivity().findViewById(R.id.bookPieChart);
    }

    @Override
    public void getData() {
        greenDaoService = GreenDaoService.getGreenDaoService(getActivity());
        pieChart = mBookPieChart.getChart();
    }


    @Override
    public void showContent() {
        mChartsRadio_DailyNumbersText.setTextColor(Color.parseColor("#49dafa"));
        //设置图标数据
        if (greenDaoService.getAllDateBigData().size() > 0) {
            mBookPieChart.initPieData(getSevenDays());
        }
//        initSplineChartsForWebBook();
    }

    @Override
    public void setInteract() {
        mChartsRadioInteractionsSet(mChartsRadio_DailyNumbersDiv);
        mChartsRadioInteractionsSet(mChartsRadio_ClickDiv);
        mChartsRadioInteractionsSet(mChartsRadio_CollectionDiv);
    }

    @Override
    public void reStartInit() {
        //设置图标数据
//        if (greenDaoService.getAllDateBigData().size() > 0) {
//            mBookPieChart.initPieData(getSevenDays());
//        }
    }


    /**
     * Get seven DayVaules
     */
    private List<PieDateStruct> getSevenDays() {
        int totalNumbers = 1;
        List<Integer> dayNumbers = new LinkedList<>();
        List<PieDateStruct> pieDateStructs = new LinkedList<>();
        for (int i = 0; i < SEVENDAY; i++) {
            //Every *24*60*60*1000 is one day
            Date today = new Date(new Date().getTime() - i * 24 * 60 * 60 * 1000);
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
            String timeToday = matter.format(today);
            PieDateStruct pieDateStruct = new PieDateStruct();
            if (i == 0) {
                pieDateStruct.setPieDataName("今天");
            } else {
                pieDateStruct.setPieDataName(timeToday);
            }
            pieDateStructs.add(0, pieDateStruct);
            /**
             * get Total number in the last seven days
             */
            if (greenDaoService.getOneDateBigData(timeToday).size() > 0) {
                dayNumbers.add(0, greenDaoService.getOneDateBigData(timeToday).get(0).getOneDayNumber());
                totalNumbers = totalNumbers + greenDaoService.getOneDateBigData(timeToday).get(0).getOneDayNumber();
            } else {
                totalNumbers = totalNumbers + 0;
                dayNumbers.add(0, 0);
            }
        }
        /**
         * the percent vaule of the pie chart
         */
        int pieTotal = 0;
        for (int i = 0; i < 7; i++) {


            if (i == TODAY) {
                pieDateStructs.get(i).setPieDateNumber(100 - pieTotal);
            } else {
                if (totalNumbers == 0) {
                    totalNumbers = 1;
                }
                pieDateStructs.get(i).setPieDateNumber(dayNumbers.get(i) * 100 / totalNumbers);
                if (dayNumbers.get(i) * 100 / totalNumbers == 0) {
                    pieDateStructs.get(i).setPieDateNumber(0);
                } else {
                    pieTotal = pieTotal + dayNumbers.get(i) * 100 / totalNumbers;
                }

            }

            pieDateStructs.get(i).setPieDateVaule(dayNumbers.get(i) + "字:(" + pieDateStructs.get(i).getPieDateNumber() + "%)");
//            System.out.println(dayNumbers.get(i) + "字:(" + pieDateStructs.get(i).getPieDateNumber() + "%)");
        }
        return pieDateStructs;
    }

    /**
     * The ChartDivGroupButton Interactions
     */
    private void mChartsRadioInteractionsSet(View view) {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMChartsColor();
                switch (v.getId()) {
                    case R.id.chartsRadio_DailyNumbersDiv:
                        if (greenDaoService.getAllDateBigData().size() > 0) {
                            mBookPieChart.initPieData(getSevenDays());
                        }else{
                            SomeStaticThing.toastSomthing(getActivity(), "少年,你还没有动笔呢,哪来的数据~~!");
                        }
                        mChartsRadio_DailyNumbersText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                    case R.id.chartsRadio_ClickDiv:
                        if (greenDaoService.loadSetting().size() == 0) {

                        } else {
                            if (greenDaoService.loadSetting().get(0).getIsWebSearch()) {
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
                                    SomeStaticThing.toastSomthing(getActivity(),"数据正在采集中,请等上一两个小时先~");
                                }else {
                                    ChartDatas_ClickAndLike chartDatas_clickAndLike = new ChartDatas_ClickAndLike(greenDaoService);
                                    mBookPieChart.initClickData(chartDatas_clickAndLike.clickNumbersOfToday(getActivity()));
                                }
                            } else {
                                SomeStaticThing.toastSomthing(getActivity(), "你还未开启数据采集,请去设置页面设置!");
                            }
                        }
                        mChartsRadio_ClickText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                    case R.id.chartsRadio_CollectionDiv:
                        if (greenDaoService.loadSetting().get(0).getIsWebSearch()) {
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
                                SomeStaticThing.toastSomthing(getActivity(),"数据正在采集中,请等上一两个小时先~");
                            }else {
                                ChartDatas_ClickAndLike chartDatas_clickAndLike1 = new ChartDatas_ClickAndLike(greenDaoService);
                                mBookPieChart.initLikeData(chartDatas_clickAndLike1.clickNumbersOfToday(getActivity()));
                            }
                        } else {
                            SomeStaticThing.toastSomthing(getActivity(), "你还未开启数据采集,请去设置页面设置!");
                        }
                        mChartsRadio_CollectionText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                }

            }
        });
    }


    private void initMChartsColor() {
        mChartsRadio_DailyNumbersText.setTextColor(Color.GRAY);
        mChartsRadio_ClickText.setTextColor(Color.GRAY);
        mChartsRadio_CollectionText.setTextColor(Color.GRAY);
    }


}
