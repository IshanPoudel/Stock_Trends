package com.example.stockapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewsService {

    Context context;
    NewsModel newsModel = new NewsModel();


    public NewsService (Context context)
    {
        this.context = context;
    }

    public interface VolleyResponseListener{

        void onError(String message);
        void onResponse(NewsModel model);
    }

    public void getNews(String ticker , VolleyResponseListener volleyResponseListener)
    {
        String url = "http://10.219.165.150:5000/get_specific_news/amzn";
        System.out.println("I am inside getNews");
        JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String [] news_array = new String[5000];

                // try to parse the response.
                try {
                    System.out.println("i got a response back");
                    JSONArray news = response.getJSONArray("amzn");

                    for (int i=0 ; i<news.length();i++)
                    {

                        JSONArray individual_news = news.getJSONArray(i);
                        System.out.println(individual_news.getString(1));
                        System.out.println(individual_news.getString(2));
                    }


                }catch (Exception e)
                {
                    System.out.println("Could not parse it");
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());

            }
        });

        DataService.getInstance(context).addToRequestQueue(newsRequest);



    }





}
