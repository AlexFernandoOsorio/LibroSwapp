package com.example.root.librosswap;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by amagaña on 19/07/2016.
 */
public class InternetConnection{
    public Context _context;

    public InternetConnection(Context context) {
        this._context = context;
    }
    public boolean isConnectingInternet(){
        ConnectivityManager cm =
                (ConnectivityManager)this._context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }


}