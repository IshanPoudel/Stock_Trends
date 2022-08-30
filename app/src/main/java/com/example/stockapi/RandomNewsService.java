package com.example.stockapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RandomNewsService {

    Context context;
    ArrayList<NewsModel> randomNews = new ArrayList<NewsModel>();


    public RandomNewsService(Context context)
    {
        this.context = context;
    }

    public interface VolleyResponseListener
    {
        void onError (String message);
        void onResponse(ArrayList<NewsModel> randomNews);

    }

    public void getShuffledNews(VolleyResponseListener volleyResponseListener) {
        String url = "http://10.219.165.150:5000/get_news";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray news = response.getJSONArray("data");

                    for (int i = 0; i < news.length(); i++) {

                        NewsModel newsModel = new NewsModel();

                        JSONArray individual_news = news.getJSONArray(i);
                        //Have it saved to the  model .
//                        System.out.println(individual_news.getString(0));
//                        System.out.println(individual_news.getString(1));
//                        System.out.println(individual_news.getString(2));
//                        System.out.println(individual_news.getString(3));
//                        System.out.println(individual_news.getString(4));
//                        System.out.println(individual_news.getString(5));
                        newsModel.initialize(individual_news.getString(0), individual_news.getString(1), individual_news.getString(2), individual_news.getString(3), individual_news.getString(4), individual_news.getString(5), individual_news.getString(6));
                        randomNews.add(newsModel);
                        System.out.println("Added" + newsModel.toString());
                    }


                } catch (Exception e) {
                    System.out.println("Could not parse it");
                    e.printStackTrace();

                }

                volleyResponseListener.onResponse(randomNews);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());

            }
        });

        DataService.getInstance(context).addToRequestQueue(request);
    }
}
