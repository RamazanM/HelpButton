package com.nerds.helpbutton;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by ramazan on 2/14/18.
 */

public class LocalStorage {
    private String PREFS_NAME="HelpButtonPrefenceres!*";
    private Context ctx;
    private SharedPreferences sharedPreferences;
    private Editor spEditor;

    public LocalStorage(Context ctx){
        this.ctx=ctx;
        sharedPreferences=ctx.getSharedPreferences(PREFS_NAME,0);
        spEditor=sharedPreferences.edit();
    }

    public void setString(String prefName,String value){

    }

}
