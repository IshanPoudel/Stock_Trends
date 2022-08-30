package com.example.stockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        handleSSLHandshake();
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
                    public void onResponse(StockModel model) {
                        Toast.makeText(MainActivity.this , "It worked. The stock price is " + model.getOpening_price().toString() , Toast.LENGTH_SHORT).show();

                    }
                });





            }
        });

        btn_getStockNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println(" I clicked the getStockNews Button");
                StockService stockService =new StockService(MainActivity.this);
                stockService.getNews("goog");


            }
        });

        btn_getCryptoNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("I am inside stocknews");

                StockService stockService = new StockService(MainActivity.this );
                stockService.getTradesOverTime(et_dataInput.getText().toString());
            }
        });













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