package com.example.stockapi;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;

public class StockService

{

    public static final String V_2_AGGS_TICKER = "https://api.polygon.io/v2/aggs/ticker/";
    public static final String HA_QGJ = "/prev?adjusted=true&apiKey=jidNJUJtGY93nOyy97fLwCwlhP_HaQGJ";
    Context context;
    Double opening_price;


    // to get the context , so we can have the stock make changes to the mainactivity.context

    public StockService (Context context)
    {
       this.context = context;
    }




    public Double getStockPrice(String stockTicker)

    {

       //Need to add a async function , because the whole section is skipped because it is running in the background.


        String url = V_2_AGGS_TICKER +stockTicker+ HA_QGJ;
        //Get previous day for every stock
//                String url = "https://api.polygon.io/v2/aggs/grouped/locale/us/market/stocks/2020-10-14?adjusted=true&apiKey=jidNJUJtGY93nOyy97fLwCwlhP_HaQGJ";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("I got a response back");

                //parse the JSON OBJECT
                try {
                    JSONArray results = response.getJSONArray("results");

                    System.out.println(results.toString());
                    //results is a array consistong of one json objecct
                    JSONObject actual_results = results.getJSONObject(0);
                    System.out.println(actual_results.toString());
                    String ticker = actual_results.getString("T");
                    opening_price = actual_results.getDouble("o");
                    Double closing_price = actual_results.getDouble("c");
                    Double highest_price = actual_results.getDouble("h");
                    Double lowest_price = actual_results.getDouble("l");
                    int total_volume_traded = actual_results.getInt("v");

                    System.out.println(ticker);
                    System.out.println(opening_price);
                    System.out.println(closing_price);
                    System.out.println(highest_price);
                    System.out.println(lowest_price);
                    System.out.println(total_volume_traded);


                    System.out.println("Get value from ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Toast.makeText(context , response.toString() , Toast.LENGTH_SHORT ).show();
                System.out.println(response.toString());

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());

            }
        });

//                after creating a JSON OBject request , we give this request to the DataService class which is a singleton class.

        DataService.getInstance(context).addToRequestQueue(request);

       // RETURNED A NULL VALUE.

        return opening_price;
    }

//    public List<StockServiceModel> getStockbyTicker(String ticker)
//    {
//
//    }



}
