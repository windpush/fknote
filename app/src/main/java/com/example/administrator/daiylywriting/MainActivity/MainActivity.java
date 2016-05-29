package com.example.administrator.daiylywriting.MainActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.daiylywriting.AlgorithmTest.AlgorithmTest;
import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.Setting;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.Views_For_Layout.ActivityViews_MainActivity;
import com.igexin.sdk.PushManager;


public class MainActivity extends BaseFragmentActivity {

    private ActivityViews_MainActivity views_mainActivity;
    private GreenDaoService greenDaoService;

    @Override
    public void setContentView() {

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        greenDaoService = GreenDaoService.getGreenDaoService(this);

        if (greenDaoService.loadSetting().size() == 0) {
            Setting setting = new Setting();
            setting.setIsItemAdd(true);
            setting.setIsWebSearch(false);
            setting.setTimeToSearch(1000 * 60 * 60 * 2);
            greenDaoService.upDateOrAddSetting(setting);
        }
        views_mainActivity = new ActivityViews_MainActivity(this);
        if (getShare()) {
            PushManager.getInstance().initialize(this.getApplicationContext());
        }
        setContentView(views_mainActivity);
        AlgorithmTest algorithmTest = new AlgorithmTest();
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        algorithmTest.SortTest(a);
    }


    @Override
    public void getData() {
    }

    @Override
    public void setStyles() {

    }

    @Override
    public void setActions() {
        views_mainActivity.setOnTheViewListener(new ActivityViews_MainActivity.OnTheViewListener() {
            @Override
            public void onNearlySelected() {
                views_mainActivity.mButtonNearly.performClick();
            }

            @Override
            public void onChartSelected() {
                views_mainActivity.mCharts.performClick();
            }

            @Override
            public void onPullDownSelected() {
                views_mainActivity.mButtonPush.performClick();
            }

            @Override
            public void onLifeSelected() {
                views_mainActivity.mButtonMe.performClick();
            }

            @Override
            public void onToolSelected() {
                views_mainActivity.mButtonTool.performClick();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
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


}
