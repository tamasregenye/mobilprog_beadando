package com.example.beadando;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorManagerHelper implements SensorEventListener {
    private static final float LIGHT_THRESHOLD = 500.0f;
    private static boolean isRegistered = false;
    private static Context context;

    public static void registerSensorListener(Context ctx) {
        context = ctx;
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                SensorManagerHelper sensorManagerHelper = new SensorManagerHelper(); // Create an instance
                sensorManager.registerListener(sensorManagerHelper, lightSensor, SensorManager.SENSOR_DELAY_NORMAL); // Use the same instance
                isRegistered = true;
            }
        }
    }

    public static void unregisterSensorListener() {
        if (isRegistered && context != null) {
            SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            if (sensorManager != null) {
                sensorManager.unregisterListener(new SensorManagerHelper()); // Use the same instance
                isRegistered = false;
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightValue = event.values[0];

            if (lightValue < LIGHT_THRESHOLD) {
                if (!ThemeManager.isNightMode()) {
                    ThemeManager.setNightMode(true);
                    ThemeManager.applyTheme(context);
                }
            } else {
                if (ThemeManager.isNightMode()) {
                    ThemeManager.setNightMode(false);
                    ThemeManager.applyTheme(context);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }
}
