package com.example.beadando;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LanguageManager {
    private static final String LANG_PREF_KEY = "My_Lang";
    private static SharedPreferences sharedPreferences;
    private static String[] supportedLanguages = {"", "hu"}; // currently supported languages

    public static void setLocale(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        saveLanguagePreference(context, lang);
    }

    public static void loadLocale(Context context) {
        sharedPreferences = context.getSharedPreferences("MODE", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString(LANG_PREF_KEY, "");
        setLocale(context, language);
    }

    private static void saveLanguagePreference(Context context, String lang) {
        sharedPreferences.edit().putString(LANG_PREF_KEY, lang).apply();
    }

    public static void switchToNextLanguage(Context context) {
        String currentLanguage = getCurrentLanguage(context);
        int currentIndex = findLanguageIndex(currentLanguage);
        int nextIndex = (currentIndex + 1) % supportedLanguages.length;
        setLocale(context, supportedLanguages[nextIndex]);
    }

    private static String getCurrentLanguage(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    private static int findLanguageIndex(String lang) {
        for (int i = 0; i < supportedLanguages.length; i++) {
            if (supportedLanguages[i].equals(lang)) {
                return i;
            }
        }
        return -1;
    }
}
