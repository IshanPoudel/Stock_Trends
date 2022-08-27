package com.example.stockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //    Set class member variables
    Button btn_getStockPrice , btn_getStockNews , btn_getCryptoNews;
    EditText et_dataInput;
    ListView lv_stockPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating values to each control in the layout

        btn_getStockPrice = findViewById(R.id.btn_getStockPrice);
        btn_getStockNews = findViewById(R.id.btn_getStockNews);
        btn_getCryptoNews=findViewById(R.id.get_cryptoNews);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_stockPrice = findViewById(R.id.lv_stockPrice);

//        Create listeners for each function
        btn_getStockPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StockService stockService = new StockService(MainActivity.this);

                //VolletResonselistener will listen to the response and give us either the actual value or the error.
                stockService.getStockPrice(et_dataInput.getText().toString(), new StockService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this , "Something Wrong" , Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(Double openingPrice) {
                        Toast.makeText(MainActivity.this , "It worked. The stock price is " + openingPrice.toString() , Toast.LENGTH_SHORT).show();

                    }
                });

                Toast.makeText(MainActivity.this , "It worked?" , Toast.LENGTH_SHORT ).show();



            }
        });

        btn_getStockNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this , "You clicked on StockNews"  , Toast.LENGTH_SHORT ).show();
            }
        });

        btn_getCryptoNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                et_dataInput.getText().toString() gets the text typed in the text field with id et_datainput
                Toast.makeText(MainActivity.this , "You typed " + et_dataInput.getText().toString()  , Toast.LENGTH_SHORT ).show();
            }
        });













    }
}