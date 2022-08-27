
// We are creating a singleton class which performs all of our http request over the server.

package com.example.stockapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class DataService {
    private static DataService instance;
    private RequestQueue requestQueue;

    private static Context ctx;



    //constructr sets up the ability for one of the classes to exist
    //request queue creares one version
    private DataService(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();


    }
    //Only allows one instance of the DataService to exist
    //if null, creates new , else just answers from one.
    public static synchronized DataService getInstance(Context context) {
        if (instance == null) {
            instance = new DataService(context);
        }
        return instance;
    }
    // if request queue  does not exist , we create one.
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
