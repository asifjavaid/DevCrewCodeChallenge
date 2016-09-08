package com.devcrewchallange.view;

/**
 * Created by asifj on 9/9/2016.
 */

/**
 * This is base view which contains all the common controls on the xml layouts.
 */
public interface BaseView
{
    /***
     * Show the progress spinner
     */
    void showProgressIndicator();

    /***
     * Hide the progress spinner
     */
    void hideProgressIndicator();

    /***
     * Show Error message from presenter to the view
     * @param resourceId
     */
    void showErrorMessage(int resourceId);

    /***
     * Show error message from api call in presenter to the view.
     * @param message
     */
    void showApiMessage(String message);
}
