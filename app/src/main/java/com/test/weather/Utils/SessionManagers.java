package com.test.weather.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.weather.R;

public class SessionManagers {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static SessionManagers mInstance;
    private Context context;

    public SessionManagers(Context context) {
        this.context = context;
    }

    public static synchronized SessionManagers getInstance(Context context) {
        if(mInstance== null) {
            mInstance = new SessionManagers(context);
        }
        return mInstance;
    }

    public void saveLocationName(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("location", name);
        editor.apply();
    }

    public String fetchLocationName(Context context) {
        sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);
        return sharedPreferences.getString("location", "Delhi");
    }

    public void saveServerResponse(String key,String value) {
        context.getSharedPreferences(String.valueOf(R.string.app_name),Context.MODE_PRIVATE)
                .edit()
                .putString(key,value)
                .apply();
    }

    public String getServerResponse(String key) {
        return context.getSharedPreferences(String.valueOf(R.string.app_name),Context.MODE_PRIVATE)
                .getString(key,"");
    }

}

