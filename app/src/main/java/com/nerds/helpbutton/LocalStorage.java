package com.nerds.helpbutton;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by ramazan on 2/14/18.
 */

public class LocalStorage {

    public String PHONE_1="phone1";
    public String PHONE_2="phone2";
    public String MESSAGE="message";
    public String CALL_AFTER_MESSAGE="callafter";
    public String SEND_LOCATION="sendlocation";


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
        spEditor.putString(prefName,value);
        spEditor.commit();
    }
    public String getString(String prefName){
        return sharedPreferences.getString(prefName,"");
    }
    public void setBool(String prefName,Boolean value){
        spEditor.putBoolean(prefName,value);
        spEditor.commit();
    }
    public Boolean getBoolean(String prefName){
        return sharedPreferences.getBoolean(prefName,false);
    }


}
