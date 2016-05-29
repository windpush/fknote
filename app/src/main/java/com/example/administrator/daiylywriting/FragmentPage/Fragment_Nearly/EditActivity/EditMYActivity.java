package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.EditActivity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BigData;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.MyOwnViews.BaseAcitivty;
import com.example.administrator.daiylywriting.MyOwnViews.MyDialog;
import com.example.administrator.daiylywriting.NetWork.DailyPost;
import com.example.administrator.daiylywriting.NetWork.JsonCreate;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/28.
 */
public class EditMYActivity extends BaseAcitivty {
    private GreenDaoService greenDaoService;
    private Intent intent;
    private String chapterKey, bookKey, timeToday;
    private android.os.Handler handler = new android.os.Handler();
    private TextView chapterNumberText, chapterSaveText, speedText;
    private com.example.administrator.daiylywriting.MyOwnViews.LineEditText lineEditText;
    private int timeAddNumber, textAddNumber = 0;
    private Boolean Show = false;
    //    private ImageView rightButton, leftButton, webButton, noteButton;
    private RelativeLayout noteDiv, webDiv, undoDiv, reDoDiv;
    private SlidingDrawer slidingWeb, slidingModle;
    private WebView webView;
    private EditText modelEdit;
    private TextView dmTextView;
    private int TodayAddNumber;
    private List<String> theTextVauleTrace = new LinkedList<>();
    private int textChangeSign = 0;
    private int speed = 0;
    private int viewHeight = 0;
    private Boolean isFinished = false;
    private RelativeLayout editWindow;
    private android.os.Handler speedHandler, saveHandler;
    private HandlerThread speedThread, saveThread;
    private NotificationManager m_NotificationManager;
    private Notification mNotification;
    private UndoOrRedo undoOrRedo = new UndoOrRedo();
    private int doSign = 0;

    private String colorList[] = {"#0099CC", "#009933", "#666666", "#CC0033", "#6666CC", "#CC6633", "#000000"};
    private int dmColor = 6;

    private Boolean IsDanMuNow = false;
    /*for undo and redo*/
    private boolean isAdd = true;
    private ArrayList<String> doStringList = new ArrayList<>();
    private ArrayList<String> listNew = new ArrayList<>();

    private RelativeLayout danMuDiv;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit);
    }

    @Override
    public void findViews() {
        /*danmutext*/
//        dmTextView= (TextView) findViewById(R.id.danmu);
//        dmTextView.setVisibility(View.GONE);
        //webView for search data for books in Internet in the SlidingView
        editWindow = (RelativeLayout) findViewById(R.id.editWindow);
        slidingWeb = (SlidingDrawer) findViewById(R.id.slidingWeb);
        webView = (WebView) findViewById(R.id.editWebView);
        lineEditText = (com.example.administrator.daiylywriting.MyOwnViews.LineEditText) findViewById(R.id.articleEditView);

        /**
         *show div int the top of Acitivy layout like speed allChapterVauleNumbers and saving status
         */
        chapterNumberText = (TextView) findViewById(R.id.chapterVaule);
        chapterSaveText = (TextView) findViewById(R.id.savedTitle);
        speedText = (TextView) findViewById(R.id.speedVaule);

        /**
         * sliding page  for bookmodel
         */
        modelEdit = (EditText) findViewById(R.id.modelEdit);
        modelEdit.getBackground().setAlpha(200);
        slidingModle = (SlidingDrawer) findViewById(R.id.slidingModel);
/**
 * A dm button
 */
        danMuDiv = (RelativeLayout) findViewById(R.id.danmudiv);
/**
 *     A simple menu like pathMenu
 */
        noteDiv = (RelativeLayout) findViewById(R.id.notediv);
        webDiv = (RelativeLayout) findViewById(R.id.webdiv);
        undoDiv = (RelativeLayout) findViewById(R.id.undodiv);
        reDoDiv = (RelativeLayout) findViewById(R.id.redodiv);
//        menuButton = (ImageView) findViewById(R.id.menuAdd);
//        menuButton.setAlpha(220);
//        rightButton = (ImageView) findViewById(R.id.pathRight);
//        leftButton = (ImageView) findViewById(R.id.pathLeft);
//        webButton = (ImageView) findViewById(R.id.pathWeb);
//        noteButton = (ImageView) findViewById(R.id.pathNote);
    }

    @Override
    public void getData() {
        //greenDaoSqlite
        greenDaoService = GreenDaoService.getGreenDaoService(getApplicationContext());
        /**
         *Intent vaule to get the chapterKey and bookKey for search the Vaule of corrently chapter
         */
        intent = getIntent();
        chapterKey = (String) intent.getSerializableExtra("chapterkey");
        bookKey = (String) intent.getSerializableExtra("bookkey");
        /**
         *       init BigData  for the data of the Writer ,Now it just hast daily writing Number
         */
        List<BigData> bigDatas = new LinkedList<>();
        Date today = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
        timeToday = matter.format(today);
        bigDatas = greenDaoService.getOneDateBigData(timeToday);
        if (bigDatas.size() == 0) {
            BigData bigData = new BigData();
            TodayAddNumber = 0;
            bigData.setOneDayNumber(TodayAddNumber);
            bigData.setTimeDate(timeToday);
            greenDaoService.saveOrReplacBigData(bigData);
        } else {
            TodayAddNumber = bigDatas.get(0).getOneDayNumber();
        }
    }

    @Override
    public void showContent() {
        /**
         *chapterVaule show
         */
        initThreads();
        chapterNumberText.setText(greenDaoService.getTheOnlyChapters(chapterKey).get(0).getChapterVauleNumbers().toString() + "字");
        lineEditText.setText(greenDaoService.getTheOnlyChapters(chapterKey).get(0).getCharpterVaules());
        doStringList.add(lineEditText.getText().toString());
        lineEditText.setSelection(lineEditText.getText().toString().length());
    }

    @Override
    public void setInteract() {
        chapterEditInteraction();
//        menuAnimationAndInteraction();
        slidingInteraction();
        danMuDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog = new MyDialog();
                final Dialog dmdialog = myDialog.danMuDialog(EditMYActivity.this, R.style.popupDialog, colorList[dmColor]);
                final View blueView, greenView, grayView, redView, ziSeView, originView, blackView, colorShowView;
                Window window = dmdialog.getWindow();
                final EditText dmVaule = (EditText) window.findViewById(R.id.dmVaule);
                dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                colorShowView = window.findViewById(R.id.colorShow);
                blueView = window.findViewById(R.id.blueView);
                greenView = window.findViewById(R.id.greenView);
                grayView = window.findViewById(R.id.grayView);
                redView = window.findViewById(R.id.redView);
                ziSeView = window.findViewById(R.id.ziSeView);
                originView = window.findViewById(R.id.originView);
                blackView = window.findViewById(R.id.blackView);
                TextView btnClose = (TextView) window.findViewById(R.id.closedm);

                btnClose.setText(getShare()? "关闭弹幕":"打开弹幕");

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sp =EditMYActivity.this.getSharedPreferences("dmpush", MODE_PRIVATE);
                        //获取到编辑对象
                        SharedPreferences.Editor edit = sp.edit();
                        //添加新的值，可见是键值对的形式添加
                        Boolean ispush =sp.getBoolean("ispush",true);
                        if (ispush){
                            edit.putBoolean("ispush",false);
                        }else {
                            edit.putBoolean("ispush",true);
                        }

                        edit.commit();
                        ((TextView)v).setText(getShare()? "关闭弹幕":"打开弹幕");

                    }
                });
                blueView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 0;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                greenView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 1;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                grayView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 2;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                redView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 3;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                ziSeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 4;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                originView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 5;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                blackView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmColor = 6;
                        colorShowView.setBackgroundColor(Color.parseColor(colorList[dmColor]));
                        dmVaule.setTextColor(Color.parseColor(colorList[dmColor]));
                    }
                });

                TextView okButton = (TextView) window.findViewById(R.id.yes);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dmVaule.getText().toString().length() > 1) {
                            sendDm(dmVaule.getText().toString(), colorList[dmColor]);
                            dmdialog.dismiss();
                        } else {
                            SomeStaticThing.toastSomthing(getApplicationContext(), "燃料不足！");
                        }
                    }
                });
                TextView noButton = (TextView) window.findViewById(R.id.no);
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dmdialog.dismiss();
                    }
                });
            }
        });
    }


    private void initThreads() {
        speedThread = new HandlerThread("mySpeedThread");
        saveThread = new HandlerThread("mySaveThread");
        speedThread.start();
        saveThread.start();
        speedHandler = new android.os.Handler(speedThread.getLooper());
        saveHandler = new android.os.Handler(saveThread.getLooper());
        saveHandler.post(saveRunnable);
        speedHandler.post(speedRunable);
    }

    private void slidingInteraction() {
        /**
         *WebButton set
         */
        webDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingWeb.toggle();
            }
        });
        noteDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingModle.toggle();
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // 在当前的webview中跳转到新的url
                return true;
            }
        });

        undoDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (doSign > 0) {
                    isAdd = false;
                    doSign--;
                    lineEditText.setText(doStringList.get(doSign));
                    lineEditText.setSelection(lineEditText.getText().toString().length());
                    isAdd = true;
//                    SomeStaticThing.toastSomthing(getApplicationContext(), doStringList.get(doSign));
                    Log.w("TVS", doSign + "left");
                }
            }
        });

        reDoDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doSign < (doStringList.size() - 1)) {
                    isAdd = false;
                    if (doSign < 19) {
                        doSign++;
                    }
                    lineEditText.setText(doStringList.get(doSign));
                    lineEditText.setSelection(lineEditText.getText().toString().length());
                    isAdd = true;
                    Log.w("TVS", doSign + "right");
//                    SomeStaticThing.toastSomthing(getApplicationContext(), doStringList.get(doSign));
                }
            }
        });


    }

    private  Boolean getShare(){
        SharedPreferences sp = this.getSharedPreferences("dmpush", MODE_PRIVATE);
        //获取到编辑对象
        SharedPreferences.Editor edit = sp.edit();
        //添加新的值，可见是键值对的形式添加

        Boolean ispush =sp.getBoolean("ispush",true);
        //提交.
        return ispush;
    }


    private void danMu(String danMuVaule, String color) {
        if (!getShare()){
            return;
        }

        final TextView textView = new TextView(getApplicationContext());
        textView.setText(danMuVaule);
        textView.setTextSize(16);
        textView.setTextColor(Color.parseColor(color));
        editWindow.addView(textView);
        textView.setVisibility(View.GONE);
        IsDanMuNow = true;
        TranslateAnimation dmMove = new TranslateAnimation(-1 * danMuVaule.length() * 20f + 20f, 900.0f, 0.1f, 0.1f);
        dmMove.setDuration(10000);
        textView.setText(danMuVaule);
        textView.setAnimation(dmMove);
        dmMove.start();
        dmMove.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                editWindow.removeView(textView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
//        YoYo.with(Techniques.Swing).playOn(dmTextView);
    }

    private void sendDm(String dmVuale,String colorVaule){
        JsonCreate jsonCreate = new JsonCreate();
        RequestParams requestParams = jsonCreate.dmCreate(dmVuale, colorVaule);
        DailyPost.post(requestParams, new BaseJsonHttpResponseHandler<JSONArray>() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONArray response) {
                Log.d("DWT",rawJsonResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONArray errorResponse) {
                SomeStaticThing.toastSomthing(getApplicationContext(),"发送失败,请检查网络或服务器公告~");
            }

            @Override
            protected JSONArray parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                return null;
            }
        });
    }


    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && slidingWeb.isOpened()) {
            slidingWeb.toggle();
            return true;
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK) && slidingModle.isOpened()) {
            slidingModle.toggle();
            return true;
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }



    private void chapterEditInteraction() {
        lineEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isAdd) {
                    textAddNumber = textAddNumber + count;
                    TodayAddNumber = TodayAddNumber + count;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (isAdd) {
                    if (doStringList.size() > 1) {
                        doStringList.remove(doStringList.subList(doSign, doStringList.size() - 1));
                        doStringList = undoOrRedo.saveDoList(s.toString(), doStringList);
                    } else {
                        doStringList.add(s.toString());
                    }
                    doSign = doStringList.size() - 1;
                    Log.w("TVS", doSign + "one");
                }
                if (!greenDaoService.loadSetting().get(0).getIsItemAdd()) {
                    String look = s.toString().replaceAll("[`~!@#$%^&*()\"+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
                    chapterNumberText.setText(look.length() + "字");
                } else {
                    chapterNumberText.setText(s.length() + "字");
                }
//                    if (textChangeSign == 10) {
//                        textChangeSign = 0;
//                    }
//                    if (theTextVauleTrace.size() < 10) {
//                        theTextVauleTrace.add(s.toString());
//                    } else {
//                        theTextVauleTrace.set(textChangeSign, s.toString());
//                    }
            }
        });

        modelEdit.setText(greenDaoService.getOneBook(bookKey).get(0).getBookModel());

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        isFinished = true;
    }


    Runnable speedRunable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    if (isFinished) {
                        break;
                    }
                    Thread.sleep(1000);
                    timeAddNumber++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable saveRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    if (isFinished) {
                        break;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chapterSaveText.setText("保存中...");
                        }
                    });

                    Thread.sleep(2000);
                    updatechapter();
                    Thread.sleep(1000);
                    speed = textAddNumber * 3600 / timeAddNumber;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            speedText.setText(speed + "字/小时");
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void updatechapter() {
        List<BigData> bigDatas = greenDaoService.getOneDateBigData(timeToday);
        BigData bigData = bigDatas.get(0);
        bigData.setOneDayNumber(TodayAddNumber);
        greenDaoService.saveOrReplacBigData(bigData);
        Charpters charpters = greenDaoService.getTheOnlyChapters(chapterKey).get(0);
//        System.out.println(charpters.getId() + "" + charpters.getCharpterKey() + "cicun" + lineEditText.getText().toString());
        charpters.setCharpterVaules(lineEditText.getText().toString());
        charpters.setChapterVauleNumbers(lineEditText.getText().length());
        greenDaoService.upDateChapters(charpters);
        List<BooksVaules> booksVauleses = new LinkedList<>();
        booksVauleses.clear();
        BooksVaules booksVaules = new BooksVaules();
        booksVaules = greenDaoService.getOneBook(bookKey).get(0);
        booksVaules.setBookModel(modelEdit.getText().toString());
        booksVauleses.add(booksVaules);
        greenDaoService.saveBookLists(booksVauleses);
        handler.post(new Runnable() {
            @Override
            public void run() {
                chapterSaveText.setText("已保存!");
            }
        });

    }

    private void pushDanMu() {
        String[] colors = {"#00FFFF", "#FF0000", "#0000FF", "#CC0099", "#00FF00", "#871F78", "#FF7F00", "#CC3300", "#009933", "#330066", "#33CCFF", "#6699FF", "#FF66FF", "#99CC66", "#666666"};
        int index = (int) (Math.random() * colors.length);
        String color = colors[index];
        String danmuVaule = "我就是我,颜色不一样的烟火";
        danMu(danmuVaule, color);
    }

    private BroadcastReceiver demuReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String demuVaule = intent.getStringExtra("dm");
            String color = intent.getStringExtra("color");
            danMu(demuVaule, color);

        }
    };

    private void simplePopShow(View v){
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.GRAY);
        TextView tv = new TextView(this);
        tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
        tv.setText("I'm a pop -----------------------------!");
        tv.setTextColor(Color.WHITE);
        layout.addView(tv);

       PopupWindow popupWindow = new PopupWindow(layout,120,120);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0],location[1]-popupWindow.getHeight());
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter danMuFilter = new IntentFilter(SomeStaticThing.DM_BROADCAST_TAG);
        registerReceiver(demuReceiver, danMuFilter);

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(demuReceiver);
    }
}
