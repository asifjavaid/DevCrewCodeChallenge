package com.devcrewchallange.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.afollestad.materialdialogs.BuildConfig;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.devcrewchallange.R;
import com.devcrewchallange.listeners.IDialogListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to provide static common functionality to other classes.
 */
public class Utils {
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	private static Utils utils;

	public static Utils getInstance() {
		if (utils == null)
			return new Utils();
		return utils;
	}

	public boolean isNetworkAvailable(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null) { // connected to the internet
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				// connected to wifi
				// Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
				return true;
			} else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
				// connected to the mobile provider's data plan
				// Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
				return true;
			}

		} else {
			// not connected to the internet
			return false;
		}

		return false;
	}


	public boolean emailvalidation(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}


	/***
	 * @param activityName Activity Name to call
	 * @param context      Activity context
	 * @param milliseconds Time to wait in milliseconds e.g (1 sec equals 1000 millis)
	 */
	public void callIntentWithTime(final String activityName, final Activity context, final int milliseconds) {

		final boolean _alive = true;

		Thread splashThread = new Thread() {

			@Override
			public void run() {
				try {
					int splashTime = 0;
					while (_alive && (splashTime < milliseconds)) {
						sleep(100);
						if (_alive) {
							splashTime += 100;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					context.finish();
					callIntent(activityName, context);
				}

			}

		};
		splashThread.start();

	}

	/**
	 * This method call the intent and delete rest of activities from the
	 * activity stack to make the current activity on top.
	 */
	public void callAndMakeTopIntent(String activityName, Activity context) {
		String pacakageName = context.getPackageName();

		Intent i = new Intent();
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		i.setClassName(pacakageName, activityName);

		android.util.Log.e("Activity", activityName);
		context.startActivity(i);
	}

	/**
	 * This method call the intent and delete rest of activities from the
	 * activity stack to make the current activity on top.
	 */
	public void callAndMakeTopIntent(String activityName, Activity context, Bundle bundle) {
		String pacakageName = context.getPackageName();
		Intent i = new Intent();
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.putExtras(bundle);
		i.setClassName(pacakageName, activityName);

		android.util.Log.e("Activity", activityName);
		context.startActivity(i);
	}

	public void callIntent(String activityName, Activity context) {
		Intent i = new Intent();
		String pacakageName = context.getPackageName();
		i.setClassName(pacakageName, activityName);

		android.util.Log.e("Activity", activityName);
		context.startActivity(i);
	}


	public void callIntent(String activityName, Activity context, Bundle bundle) {
		String pacakageName = context.getPackageName();
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClassName(pacakageName, activityName);

		android.util.Log.e("Activity", activityName);
		context.startActivity(intent);
	}

	public void callIntentWithResult(String activityName, Activity context, Bundle bundle, int requestCode) {
		String pacakageName = context.getPackageName();
		Intent intent = new Intent();

		if (bundle != null)
			intent.putExtras(bundle);

		intent.setClassName(pacakageName, activityName);

		android.util.Log.e("Activity", activityName);
		context.startActivityForResult(intent, requestCode);
	}


	public void showDialog(Context context, String message, final IDialogListener listener) {
		final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
		materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				if (listener != null)
					listener.onClickOk(true);
			}
		}).positiveText(context.getString(R.string.ok));
		materialDialog.show();
	}

	public void showDialog(Context context, String title, String message, String ok, String cancel, final IDialogListener listener) {
		final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
		materialDialog.title(title).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).onPositive(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				dialog.dismiss();
				if (listener != null)
					listener.onClickOk(true);
			}
		}).onNegative(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				dialog.dismiss();
				if (listener != null)
					listener.onClickOk(false);
			}
		}).positiveText(ok).negativeText(cancel);
		// materialDialog.titleGravity(GravityEnum.CENTER);
		// materialDialog.contentGravity(GravityEnum.CENTER);

		materialDialog.show();
	}


	public void messageBox(String message, Activity context) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public void openWebURL(String inURL, Activity context) {
		Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(inURL));
		context.startActivity(browse);
	}

	public void showDialog7(Context context, String title, String message, String ok, final IDialogListener listener) {
		final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
		if (!TextUtils.isEmpty(title)) {
			materialDialog.title(title).content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);

		} else {
			materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok);
		}
		materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				dialog.dismiss();
				if (listener != null)
					listener.onClickOk(true);
			}
		});
		// materialDialog.titleGravity(GravityEnum.CENTER);
		// materialDialog.contentGravity(GravityEnum.CENTER);

		materialDialog.show();
	}

	public void showDialog(Context context, String message, String ok, String cancel, final IDialogListener listener) {
		final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
		materialDialog.content(message).positiveColorRes(R.color.blue_button_background).negativeColorRes(R.color.blue_button_background).positiveText(ok).negativeText(cancel);

		materialDialog.onPositive(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				dialog.dismiss();
				if (listener != null)
					listener.onClickOk(true);
			}
		});

		materialDialog.onNegative(new MaterialDialog.SingleButtonCallback() {
			@Override
			public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
				dialog.dismiss();
				if (listener != null)
					listener.onClickOk(false);
			}
		});

		materialDialog.show();
	}


	public String getAppVersion() {
		return BuildConfig.VERSION_NAME;
	}

	private String getOsVersion() {
		return String.valueOf(Build.VERSION.RELEASE);
	}


	public int getAndroidScreenWidth(Activity context) {

		DisplayMetrics displaymetrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		return width;
	}


	public String getDeviceDetails() {
		StringBuilder sbDeviceDetails = new StringBuilder();
		sbDeviceDetails.append(Build.MODEL);
		sbDeviceDetails.append(" - ");
		sbDeviceDetails.append("Android OS ");
		sbDeviceDetails.append(Build.VERSION.RELEASE);
		sbDeviceDetails.append(" - App Version ");
		sbDeviceDetails.append(BuildConfig.VERSION_NAME);
		return sbDeviceDetails.toString();
	}
}
