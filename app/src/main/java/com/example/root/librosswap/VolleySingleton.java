package com.example.root.librosswap;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {


    private static VolleySingleton volleys = null;
    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;
    private static Context micontexto;

    private VolleySingleton(Context context) {
        micontexto = context;
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap> cache = new LruBitmapCache(micontexto);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (volleys == null) {
            volleys = new VolleySingleton(context);
        }
        return volleys;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(micontexto.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

}
