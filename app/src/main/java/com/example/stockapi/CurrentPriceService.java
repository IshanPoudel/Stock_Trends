package com.example.stockapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentPriceService {

    Context context;
    Double current_price;
    Double percent_change;


    public CurrentPriceService(Context context)
    {
        this.context = context;
    }

    public  interface  VolleyResponseListener
    {
        void onError(String message);
        void onResponse(Double current_price , Double percent_change);

    }

    public void getCurrentRealTimePrice(String ticker , VolleyResponseListener volleyResponseListener)
    {
        String url = "https://finnhub.io/api/v1/quote?symbol="+ticker+"&token=cc3vonqad3i7aelmvh8g";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    current_price = response.getDouble("c");
                    percent_change = response.getDouble("dp");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(current_price , percent_change);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                volleyResponseListener.onError("Could not connect to the API.");

            }
        });

        DataService.getInstance(context).addToRequestQueue(request);

    }



}
