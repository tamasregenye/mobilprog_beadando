package com.example.beadando;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {
    private static boolean nightMode;
    private static boolean autoMode;
    private static final String THEME_PREF_KEY = "night_mode";
    private static final String AUTO_MODE_PREF_KEY = "auto_mode";

    public static void applyTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean(THEME_PREF_KEY, false);
        autoMode = sharedPreferences.getBoolean(AUTO_MODE_PREF_KEY, false);
        setTheme(context, nightMode);
    }

    public static void toggleTheme(Context context) {
        if(autoMode) {
            ActUtils.createLongToast(context, context.getString(R.string.warning_auto_mode));
        }
        else{
            SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
            boolean currentTheme = sharedPreferences.getBoolean(THEME_PREF_KEY, false);
            boolean newTheme = !currentTheme;
            setTheme(context, newTheme);
        }
        Log.d("Theme", "Current theme: " + (nightMode ? "Night" : "Day"));
    }

    public static void toggleAutoMode(Context context) {
        autoMode = !autoMode;
        setAutoMode(autoMode);
        if (autoMode) {
            // set themes based on sensor data
            SensorManagerHelper.registerSensor(context);
        }
        else {
            SensorManagerHelper.unregisterSensor();
        }
        saveAutoModePreference(context, autoMode);
        Log.d("AutoMode", "Auto mode: " + autoMode);
    }

    protected static void setTheme(Context context, boolean isNightMode) {

        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setNightMode(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            setNightMode(false);
        }
        saveThemePreference(context, isNightMode);
    }

    public static void saveAutoModePreference(Context context, boolean isAutoMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(AUTO_MODE_PREF_KEY, isAutoMode).apply();
    }

    private static void saveThemePreference(Context context, boolean isNightMode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(THEME_PREF_KEY, isNightMode).apply();
    }

    public static boolean isNightMode() {
        return nightMode;
    }

    public static void setNightMode(boolean nightMode) {
        ThemeManager.nightMode = nightMode;
    }

    public static boolean isAutoMode() {
        return autoMode;
    }

    public static void setAutoMode(boolean autoMode) {
        ThemeManager.autoMode = autoMode;
    }

    public static void logTheme() {
        Log.d("Theme", "Current theme: " + (nightMode ? "Night" : "Day"));
        Log.d("Theme", "Auto mode: " + (autoMode ? "on" : "off"));
    }

}

