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
    public static final String KEY_ID = "id";
    public static final String KEY_DNI = "dni";
    public static final String KEY_CODUSUER = "coduser";
    public static final String KEY_TIPO = "tipousuario";
    public static final String KEY_NOM = "nombres";
    public static final String KEY_APELLIDO = "apellidos";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SKYPE = "skype";
    public static final String KEY_DIRECCION = "direccion";
    public static final String KEY_PASS = "password";
    public static final String KEY_EDAD = "edad";
    public static final String KEY_CEL= "celular";
    public static final String KEY_TEL = "telefono";
    public static final String KEY_GEN = "genero";
    public static final String KEY_INTERES = "intereses";

    public SharedPrefUsuarios(Context context){
        this.contexto = context;
        pref = contexto.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */

    public void createLoginSession(String shar_ID,
                                   String shar_DNI,
                                   String shar_CODUSUER,
                                   String shar_TIPO,
                                   String shar_NOM,
                                   String shar_APELLIDO,
                                   String shar_ESTADO,
                                   String shar_EMAIL,
                                   String shar_SKYPE,
                                   String shar_DIRECCION,
                                   String shar_PASS,
                                   String shar_EDAD,
                                   String shar_CEL,
                                   String shar_TEL,
                                   String shar_GEN,
                                   String shar_INTERES){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing strings
        editor.putString(KEY_ID,shar_ID);
        editor.putString(KEY_DNI,shar_DNI);
        editor.putString(KEY_CODUSUER,shar_CODUSUER);
        editor.putString(KEY_TIPO,shar_TIPO);
        editor.putString(KEY_NOM,shar_NOM);
        editor.putString(KEY_APELLIDO,shar_APELLIDO);
        editor.putString(KEY_ESTADO,shar_ESTADO);
        editor.putString(KEY_EMAIL,shar_EMAIL);
        editor.putString(KEY_SKYPE,shar_SKYPE);
        editor.putString(KEY_DIRECCION,shar_DIRECCION);
        editor.putString(KEY_PASS,shar_PASS);
        editor.putString(KEY_EDAD,shar_EDAD);
        editor.putString(KEY_CEL,shar_CEL);
        editor.putString(KEY_TEL,shar_TEL);
        editor.putString(KEY_GEN,shar_GEN);
        editor.putString(KEY_INTERES,shar_INTERES);

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

        user.put(KEY_ID,pref.getString(KEY_ID,null));
        user.put(KEY_DNI,pref.getString(KEY_DNI,null));
        user.put(KEY_CODUSUER,pref.getString(KEY_CODUSUER,null));
        user.put(KEY_TIPO,pref.getString(KEY_TIPO,null));
        user.put(KEY_NOM,pref.getString(KEY_NOM,null));
        user.put(KEY_APELLIDO,pref.getString(KEY_APELLIDO,null));
        user.put(KEY_ESTADO,pref.getString(KEY_ESTADO,null));
        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
        user.put(KEY_SKYPE,pref.getString(KEY_SKYPE,null));
        user.put(KEY_DIRECCION,pref.getString(KEY_DIRECCION,null));
        user.put(KEY_PASS,pref.getString(KEY_PASS,null));
        user.put(KEY_EDAD,pref.getString(KEY_EDAD,null));
        user.put(KEY_CEL,pref.getString(KEY_CEL,null));
        user.put(KEY_TEL,pref.getString(KEY_TEL,null));
        user.put(KEY_GEN,pref.getString(KEY_GEN,null));
        user.put(KEY_INTERES,pref.getString(KEY_INTERES,null));
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
