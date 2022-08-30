package com.example.stockapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CandleService {

    Context context;
    //These two are used for the api call to finnhub for the candle data.
    long endTime = System.currentTimeMillis() / 1000L;
    long startTime = endTime-31556926;

    ArrayList<Double> Closing_Prices = new ArrayList<Double>();

    //Get the closing price and stock date.
//    "c" :123 , 459
//    123 is the oldest , 459 is the most recent.

    public CandleService(Context context)
    {
        this.context = context;
    }

    public  interface VolleyResponseListener{

        void onError(String message);
        void onResponse(ArrayList<Double> Closing_Prices);
    }

    public void getCandlePrice(String ticker , VolleyResponseListener volleyResponseListener)
    {
        String url = "https://finnhub.io/api/v1/stock/candle?symbol="+ticker+"&resolution=D&from=" +startTime+"&to=" + endTime +"&token=cc3vonqad3i7aelmvh8g";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray result = response.getJSONArray("c");
                    for (int i = 0; i < result.length(); i++) {
                        Closing_Prices.add(result.getDouble(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(Closing_Prices);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                volleyResponseListener.onError("Error getting StockCandleData");

            }
        });

        DataService.getInstance(context).addToRequestQueue(request);



    }






}
