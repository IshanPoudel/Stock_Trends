// Creates a class to hold our custom card in headline_list_items.xml

package com.example.stockapi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title , text_sentiment , text_stock_symbol;
    ImageView img_headline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.text_title);
        text_sentiment=itemView.findViewById(R.id.text_sentiment);
        text_stock_symbol=itemView.findViewById(R.id.text_stock_symbol);
        img_headline=itemView.findViewById(R.id.img_healdine);
        cardView = itemView.findViewById(R.id.main_container);

    }


}
