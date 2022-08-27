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

//                After the button is clicked make the API request to get the value.
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                //Get opening , close for one stock.
                String url = "https://api.polygon.io/v2/aggs/ticker/AMZN/prev?adjusted=true&apiKey=jidNJUJtGY93nOyy97fLwCwlhP_HaQGJ";
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
                            Double opening_price = actual_results.getDouble("o");
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


                        Toast.makeText(MainActivity.this , response.toString() , Toast.LENGTH_SHORT ).show();
                        System.out.println(response.toString());

                    }

                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());

                    }
                });

                queue.add(request);



//


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