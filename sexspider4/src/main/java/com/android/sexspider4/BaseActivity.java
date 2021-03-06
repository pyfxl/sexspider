package com.android.sexspider4;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by feng on 2017/5/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected final int FIRST_REQUEST_CODE = 1;
    protected final int SECOND_REQUEST_CODE = 2;

    private Toast toast;
    private TextView actionBarTitle;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        actionBar = getSupportActionBar();
//        if(actionBar != null){
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        findView();
        setActionBar();

        //toolbar = getToolbar();
        //setSupportActionBar(toolbar);
    }

    //设置ActionBar
    private void setActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.action_bar);

            View actionView = actionBar.getCustomView();
            actionBarTitle = (TextView) actionView.findViewById(R.id.actionBarTitle);
            actionBarTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doBack();
                }
            });

            Toolbar parent =(Toolbar) actionView.getParent();
            parent.setContentInsetsAbsolute(0,0);

            //getActionBar().setDisplayHomeAsUpEnabled(true);
            //getActionBar().setHomeAsUpIndicator(R.drawable.ic_action_back);
        }
    }

    private Toolbar getToolbar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        View actionView = actionBar.getCustomView();
        return (Toolbar) actionView.getParent();
    }

    //设置标题
    public void setActionBarTitle(int resId) {
        actionBarTitle.setText(getString(resId));
    }

    //设置标题
    public void setActionBarTitle(String title) {
        actionBarTitle.setText(title);
    }

    //显示提示
    public void showToast(Context context, int resId) {
        showToast(context, getString(resId));
    }

    //显示提示
    public void showToast(Context context, String str) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doBack();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public abstract void doBack();

    public abstract void findView();

    public abstract void floatButton();

}
