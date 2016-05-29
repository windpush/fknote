package com.example.administrator.daiylywriting.MyOwnViews;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/1/27.
 */
public abstract  class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        ButterKnife.bind(this);
        setupTransparentSystemBarsForLmp();
        init();
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    public void init(){
        getData();
        setStyles();
        setActions();

    }

    @Override
    public void onResume(){
        super.onResume();
    }
    public abstract void setContentView();
    public abstract void getData();
    public abstract void setStyles();
    public abstract void setActions();




    private void setupTransparentSystemBarsForLmp() {
        // TODO(sansid): use the APIs directly when compiling against L sdk.
        // Currently we use reflection to access the flags and the API to set the transparency
        // on the System bars.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Window window = getWindow();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    return;
                }
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                Field drawsSysBackgroundsField = WindowManager.LayoutParams.class.getField(
                        "FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS");
                getWindow().addFlags(drawsSysBackgroundsField.getInt(null));
                Method setStatusBarColorMethod =
                        Window.class.getDeclaredMethod("setStatusBarColor", int.class);
            /*Method setNavigationBarColorMethod =
                    Window.class.getDeclaredMethod("setNavigationBarColor", int.class);*/
                setStatusBarColorMethod.invoke(window, Color.TRANSPARENT);
                // setNavigationBarColorMethod.invoke(getWindow(), Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
