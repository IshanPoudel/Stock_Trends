package com.example.stockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class News_Activity extends AppCompatActivity {

    TextView stockTicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String stock_ticker = intent.getStringExtra("StockTicker");
        stockTicker = findViewById(R.id.stockTicker);
        stockTicker.setText(stock_ticker);

        System.out.println(" I clicked the getStockNews Button");
        NewsService newsService = new NewsService(News_Activity.this);
        newsService.getNews("AMZN", new NewsService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println("There was some error");
            }

            @Override
            public void onResponse(ArrayList<NewsModel> newsArray) {
                System.out.println("I got a response back from the volleyListener for my NewsModel class");
                NewsModel first = newsArray.get(0);
                Toast.makeText(News_Activity.this , first.toString() , Toast.LENGTH_SHORT).show();


            }
        });




    }


}