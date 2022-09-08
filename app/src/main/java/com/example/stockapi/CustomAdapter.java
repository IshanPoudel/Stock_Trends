package com.example.stockapi;

//Used to store all our custom cardview (headlines) i.e CustomViewHolder

import android.content.Context;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private ArrayList<NewsModel> headlines;

    public CustomAdapter(Context context , ArrayList<NewsModel> headlines)
    {
        this.context=context;
        this.headlines=headlines;
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create viewholder based on headline_list_items.xml
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_items , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        //onBindViewHolder we pass in the actual text from our ArrayList
        holder.text_title.setText(headlines.get(position).getNews_headline());
        holder.text_sentiment.setText(headlines.get(position).getNews_sentiment());


        holder.text_stock_symbol.setText(headlines.get(position).getTicker().toUpperCase(Locale.ROOT));

//        System.out.println("I am supposed tto be printing" + headlines.get(position).getNews_sentiment());
//        System.out.println("I am supposed tto be printing" + headlines.get(position).getTicker());


        if(headlines.get(position).getImage_link()!=null)
        {






//            Picasso.get().load(headlines.get(position).getImage_link()).fit().into(holder.img_headline);
            Glide.with(context).load(headlines.get(position).getImage_link()).override(250 , 250).centerCrop().into(holder.img_headline);
            //need to resize.

            System.out.println("I am supposed to be loading " + headlines.get(position).getImage_link());
//            Picasso.get().load("https://s.yimg.com/ny/api/res/1.2/OvAPTlUKoCvXULMe9dh5ZA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTk2MDtoPTQ4MDtjZj13ZWJw/https://s.yimg.com/uu/api/res/1.2/qbg4FElLS8eIgn1Smy7VQg--~B/aD02NDA7dz0xMjgwO2FwcGlkPXl0YWNoeW9u/https://media.zenfs.com/en/marketwatch.com/7f5817ee649c054f865c2cbb8b31dbd1").into(holder.img_headline);


        }


    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
