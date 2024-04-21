package com.example.beadando;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorManagerHelper implements SensorEventListener {

    private static final float LIGHT_THRESHOLD = 50.0f;
    private static SensorManager sensorManager;
    private static Context mContext;
    private static float lightValue;
    private static SensorEventListener sensorEventListener;
    private static boolean isSensorRegistered = false;

    public static void registerSensor(Context context) {
        mContext = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (lightSensor != null) {
            sensorEventListener = new SensorManagerHelper(); // Store the instance of SensorEventListener
            sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_UI);
            isSensorRegistered = true;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isSensorRegistered) return;    //ignore sensor events if the sensor is not registered

        LanguageManager.loadLocale(mContext); // Load the saved language preference
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightValue = event.values[0];
            if (lightValue <= LIGHT_THRESHOLD) {
                // Dark environment, switch to night mode
                ThemeManager.setTheme(mContext, true);
            } else {
                // Bright environment, switch to day mode
                ThemeManager.setTheme(mContext, false);
            }
        }

        logLightValue();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this implementation
    }

    public static void unregisterSensor() {
        if (sensorManager != null && sensorEventListener != null) {
            sensorManager.unregisterListener(sensorEventListener);
            sensorEventListener = null;
            isSensorRegistered = false;
        }
    }

    public static void logLightValue() {
        Log.d("Light Value", String.valueOf(lightValue));
    }
}
