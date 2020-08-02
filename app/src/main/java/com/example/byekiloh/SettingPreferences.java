package com.example.byekiloh;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SettingPreferences {

    private static final String FRASE_PREFERENCE_KEY = "FRASE_PREFERENCE_KEY";
    private final Context context;

    public SettingPreferences(Context context) {
        this.context = context;
    }

    public void save(Frase frase) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        String json = new Gson().toJson(frase);
        edit.putString(FRASE_PREFERENCE_KEY,json);
        edit.commit();
    }

    public Frase getFrase() {
        String json = getSharedPreferences().getString(FRASE_PREFERENCE_KEY,null);
        if(json == null){
            return new Frase();
        }
        Frase frase = new Gson().fromJson(json, Frase.class);
        return frase;
    }

    private SharedPreferences getSharedPreferences(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences;
    }

}