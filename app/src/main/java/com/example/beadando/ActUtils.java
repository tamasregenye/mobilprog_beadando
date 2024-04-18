package com.example.beadando;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActUtils {

    private static boolean nightMode;

    private static Toast currentToast;

    //ACTIVITIES
    public static void startActivity(Context context, Class<? extends Activity> activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }

    public static void startActivityAndFinishCurrent(Activity currentActivity, Class<? extends Activity> activityClass) {
        startActivity(currentActivity, activityClass);
        currentActivity.finish();
    }

    //TOASTS
    // Show new toast if currently no toast is shown, else cancel and show the new one
    private static void CancelToast(){
        if (currentToast != null) {
            currentToast.cancel();
        }
    }
    public static void createLongToast(Context context, String message) {
        CancelToast();
        currentToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        currentToast.show();
    }

    public static void createShortToast(Context context, String message) {
        CancelToast();
        currentToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        currentToast.show();
    }

    public static boolean isNightMode() {
        return nightMode;
    }

    public static void setNightMode(boolean nightMode) {
        ActUtils.nightMode = nightMode;
    }
}
