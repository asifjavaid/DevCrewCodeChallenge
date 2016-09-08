package com.devcrewchallange.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.devcrewchallange.R;
import com.devcrewchallange.listeners.IDialogListener;
import com.devcrewchallange.util.Utils;
import com.devcrewchallange.view.BaseView;

/**
 * Created by asifj on 9/8/2016.
 */

public class BaseActivity extends AppCompatActivity implements BaseView
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressIndicator() {
        findViewById(R.id.layoutProgress).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndicator() {
        findViewById(R.id.layoutProgress).setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(int resourceId) {
        Utils.getInstance().showDialog(this,getString(resourceId),null);
    }

    @Override
    public void showApiMessage(String message) {
        Utils.getInstance().showDialog(this, message, new IDialogListener() {
            @Override
            public void onClickOk(boolean isok) {

            }
        });
    }
}
