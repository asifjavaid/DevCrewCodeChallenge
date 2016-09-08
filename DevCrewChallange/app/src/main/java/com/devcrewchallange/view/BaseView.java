package com.devcrewchallange.view;

/**
 * Created by asifj on 9/9/2016.
 */

public interface BaseView
{
    void showProgressIndicator();
    void hideProgressIndicator();
    void showErrorMessage(int resourceId);
    void showApiMessage(String message);
}
