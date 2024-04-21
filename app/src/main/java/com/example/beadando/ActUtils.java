package com.example.beadando;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActUtils {



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
    private static void cancelToast(){
        if (currentToast != null) {
            currentToast.cancel();
            currentToast = null;
        }
    }
    public static void createShortToast(Context context, String message) {
        toastMaker(context, message,Toast.LENGTH_SHORT, Gravity.BOTTOM);
    }
    public static void createLongToast(Context context, String message) {
        toastMaker(context, message,Toast.LENGTH_LONG, Gravity.TOP);
    }

    private static void toastMaker(Context context, String message, int length, int location){
        // Cancel the current toast if it is still showing
        cancelToast();

        // Inflate custom layout for Toast
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Customize the view
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);

        // configure the Toast
        currentToast = new Toast(context);
        currentToast.setDuration(length);
        currentToast.setView(layout);

        currentToast.setGravity(Gravity.CENTER_VERTICAL | location, 0, 40);

        //and finally show the Toast
        currentToast.show();
    }
}
