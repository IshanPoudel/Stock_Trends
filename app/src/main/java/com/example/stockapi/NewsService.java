package com.example.stockapi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewsService {

    Context context;

    ArrayList<NewsModel> newsArray  = new ArrayList<NewsModel>();


    public NewsService (Context context)
    {
        this.context = context;
    }

    public interface VolleyResponseListener{

        void onError(String message);
        void onResponse(ArrayList<NewsModel> newsArray);
    }

    public void getNews(String ticker , VolleyResponseListener volleyResponseListener)
    {
        String url = "http://10.219.165.150:5000//get_specific_news/"+ticker;
        System.out.println("I am inside getNews");
        JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                // try to parse the response.
                try {

                    JSONArray news = response.getJSONArray("data");

                    for (int i=0 ; i<news.length();i++)
                    {

                        NewsModel newsModel = new NewsModel();

                        JSONArray individual_news = news.getJSONArray(i);
//                        Have it saved to the  model .
                        System.out.println("Hey");
                        System.out.println(individual_news.getString(0));
                        System.out.println(individual_news.getString(1));
                        System.out.println(individual_news.getString(2));
                        System.out.println(individual_news.getString(3));
                        System.out.println(individual_news.getString(4));
                        System.out.println(individual_news.getString(5));
                        newsModel.initialize(ticker , individual_news.getString(0) , individual_news.getString(1) , individual_news.getString(2) , individual_news.getString(3) , individual_news.getString(4) , individual_news.getString(5) );
                        newsArray.add(newsModel);
                        System.out.println("Added" + newsModel.toString());
                    }


                }catch (Exception e)
                {
                    System.out.println("Could not parse it");
                    e.printStackTrace();

                }

                volleyResponseListener.onResponse(newsArray);

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
