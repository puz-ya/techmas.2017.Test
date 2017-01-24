package com.puzino.techmastest01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.security.MessageDigest;

/**
 * Created by PY on 24.01.2017.
 */

public class DataPreference {

    private SharedPreferences mPreferences;
    private static String mPrefLoginName = "test_login";
    private static String mPrefPassName = "test_password";

    DataPreference(Activity activity){
        mPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    String getLogin(){
        return mPreferences.getString(mPrefLoginName, "");
    }

    String getPassword(){
        return mPreferences.getString(mPrefPassName, "");
    }

    void setLogin(String set){
        //commit writes directly, apply - in background
        mPreferences.edit().putString(mPrefLoginName, set).apply();
    }

    void setPassword(String set){
        set = getSha1Hex(set);  //encode password
        mPreferences.edit().putString(mPrefPassName, set).apply();
    }

    //add a little cryptography here
    @Nullable
    private static String getSha1Hex(String clearString)
    {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(clearString.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();

            StringBuilder buffer = new StringBuilder();
            for (byte b : bytes) {
                buffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        }
        catch (Exception ignored)
        {
            ignored.printStackTrace();
            return null;
        }
    }
}
