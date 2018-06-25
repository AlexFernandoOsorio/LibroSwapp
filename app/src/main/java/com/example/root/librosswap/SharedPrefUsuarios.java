package com.example.root.librosswap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.HashMap;

public class SharedPrefUsuarios {

    // Shared Preferences
    SharedPreferences pref;

    // Editor para Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context contexto;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref nombre archivo
    private static final String PREF_NAME = "AndroidHivePref";

    // Todos los Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // User name (make variable public to access from outside)
    public static final String KEY_PASS = "password";
    public static final String KEY_CODUSUER = "coduser";
    public static final String KEY_CEL= "celular";
    public static final String KEY_DNI = "dni";
    public static final String KEY_NOM = "nombres";
    public static final String KEY_TIPO = "tipousuario";

    public SharedPrefUsuarios(Context context){
        this.contexto = context;
        pref = contexto.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */

    public void createLoginSession(String sharemail, String sharpassword ,
                                   String sharid ,String sharcelular,
                                   String shardni,String sharnombres,String shartipousuario){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_EMAIL, sharemail);

        // Storing email in pref
        editor.putString(KEY_PASS, sharpassword);
        editor.putString(KEY_CODUSUER, sharid);
        editor.putString(KEY_CEL, sharcelular);
        editor.putString(KEY_DNI, shardni);
        editor.putString(KEY_NOM, sharnombres);
        editor.putString(KEY_TIPO, shartipousuario);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        if(!this.isLoggedIn()){
        }
        else
        {
            Intent i = new Intent(contexto, NavigLibros.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contexto.startActivity(i);
        }
    }
    public void IntentLog(Bundle bundle){
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
        }
        else
        {
            Intent i = new Intent(contexto, NavigLibros.class);
            // Closing all the Activities
            i.putExtras(bundle);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            contexto.startActivity(i);
        }

    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // user password
        user.put(KEY_PASS, pref.getString(KEY_PASS, null));
        ///user
        user.put(KEY_CODUSUER,pref.getString(KEY_CODUSUER,null));
        //
        user.put(KEY_CEL,pref.getString(KEY_CEL,null));
        //
        user.put(KEY_DNI,pref.getString(KEY_DNI,null));
        //
        user.put(KEY_NOM,pref.getString(KEY_NOM,null));

        user.put(KEY_TIPO,pref.getString(KEY_TIPO,null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(contexto, LoginLibros.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        contexto.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
