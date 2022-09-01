package com.example.stockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
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

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {

    //    Set class member variables
    Button btn_getStockPrice , btn_getStockNews , btn_getCryptoNews;
    EditText et_dataInput;
    ListView lv_stockPrice;
    ScrollView data_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        handleSSLHandshake();
        setContentView(R.layout.activity_main);

        long unixTime = System.currentTimeMillis() / 1000L;



        //Creating values to each control in the layout

        btn_getStockPrice = findViewById(R.id.btn_getStockPrice);
        btn_getStockNews = findViewById(R.id.btn_getStockNews);
        btn_getCryptoNews=findViewById(R.id.get_cryptoNews);

        et_dataInput = findViewById(R.id.et_dataInput);
//        lv_stockPrice = findViewById(R.id.lv_stockPrice);
        data_output = findViewById(R.id.data_output);

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
                    public void onResponse(StockModel model) {
                        Toast.makeText(MainActivity.this , "The opening price for the previous day was " + model.getOpening_price().toString() , Toast.LENGTH_SHORT).show();


                    }
                });

                CurrentPriceService currentPriceService = new CurrentPriceService(MainActivity.this);
                currentPriceService.getCurrentRealTimePrice(et_dataInput.getText().toString(), new CurrentPriceService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this , "An error occured." , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Double current_price, Double percent_change) {

                        Toast.makeText(MainActivity.this , "Current Price is " + current_price.toString()+" Percentage change:" + percent_change.toString() , Toast.LENGTH_SHORT).show();

                    }
                });

                CandleService candleService = new CandleService(MainActivity.this);
                candleService.getCandlePrice( et_dataInput.getText().toString(), new CandleService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this , "Error getting CandleService" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<Double> Closing_Prices) {


                        System.out.println("Succesfully got the closingPrices. Good job." + Closing_Prices.toString());

                    }
                });





            }
        });

        btn_getStockNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           sendNews(view);


            }
        });

        btn_getCryptoNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                RandomNewsService randomNewsService = new RandomNewsService(MainActivity.this);
                randomNewsService.getShuffledNews(new RandomNewsService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this , "Error Retrieving Data try agsin" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ArrayList<NewsModel> randomNews) {
                        System.out.println("I got a response back from the volleyListener for my NewsModel class");
                        NewsModel first = randomNews.get(0);
                        NewsModel second = randomNews.get(0);
                        Toast.makeText(MainActivity.this , first.toString() , Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });















    }


    public void sendNews(View view )
    {
        Intent intent = new Intent(this , News_Activity.class);
        //first get reference to the first one.
       String msg = et_dataInput.getText().toString();

       //Send this value to the next screen.
        //Send next value to the dictionary.
        intent.putExtra("StockTicker" , msg);

        startActivity(intent);


    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake()
    {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }

    }

}