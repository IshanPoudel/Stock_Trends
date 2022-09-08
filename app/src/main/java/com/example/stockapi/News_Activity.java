package com.example.stockapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class News_Activity extends AppCompatActivity {

    TextView stockTicker;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    Boolean news_loaded = Boolean.FALSE;
    Boolean previous_day_closing_price =Boolean.FALSE;
    Boolean currentPrice = Boolean.FALSE;
    Boolean candle_price = Boolean.FALSE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        String stock_ticker = intent.getStringExtra("StockTicker");
        stockTicker = findViewById(R.id.stock_current_price);










        NewsService newsService = new NewsService(News_Activity.this);
        newsService.getNews(stock_ticker, new NewsService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println("There was some error");
            }

            @Override
            public void onResponse(ArrayList<NewsModel> newsArray) {
                System.out.println("I got a response back from the volleyListener for my NewsModel class");
                showNews(newsArray );





//                NewsModel first = newsArray.get(0);
//                Toast.makeText(News_Activity.this , first.toString() , Toast.LENGTH_SHORT).show();



            }
        });












    }

    //Need to pass in the stockticker.

    private void showNews(ArrayList<NewsModel> newsArray)
    {
        recyclerView = findViewById(R.id.recycler_view_for_each_stock);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 1));
        adapter = new CustomAdapter(this , newsArray );
        recyclerView.setAdapter(adapter);




        CurrentPriceService currentPriceService = new CurrentPriceService(News_Activity.this);
        currentPriceService.getCurrentRealTimePrice(newsArray.get(0).getTicker(), new CurrentPriceService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(News_Activity.this , "An error occured." , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Double current_price, Double percent_change) {

//                Toast.makeText(News_Activity.this , "Current Price is " + current_price.toString()+" Percentage change:" + percent_change.toString() , Toast.LENGTH_SHORT).show();
                currentPrice = Boolean.TRUE;
                stockTicker.setText("$" + current_price.toString());


            }
        });


        CandleService candleService = new CandleService(News_Activity.this);
        candleService.getCandlePrice( newsArray.get(0).getTicker(), new CandleService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(News_Activity.this , "Error getting CandleService" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<Double> Closing_Prices , ArrayList<Double> High_Prices , ArrayList<Double> Low_Prices , ArrayList<Integer> timeInUnix) {
//
//
//                System.out.println("Succesfully got the closingPrices. Good job." + Closing_Prices.toString());
//                System.out.println("Succesfully got the highPrices. Good job." + High_Prices.toString());
//                System.out.println("Succesfully got the lowPrices. Good job." + Low_Prices.toString());
//                System.out.println("Succesfully got the unix timestamps. Good job"+ timeInUnix.toString());

                //Get the timestamp of the function.

                ArrayList<String> dateArray = convertUnixToDate(timeInUnix);

//                System.out.println("Succesfully got the dateTime .Good job" + dateArray.toString());


                candle_price = Boolean.TRUE;

                //Make a chart here.
                AnyChartView anyChartView = findViewById(R.id.any_chart_view);
//                anyChartView.setProgressBar();

                Cartesian cartesian = AnyChart.line();

                cartesian.animation(true);

                cartesian.padding(10d, 20d, 5d, 20d);

                cartesian.crosshair().enabled(true);
                cartesian.crosshair()
                        .yLabel(true)
                        // TODO ystroke
                        .yStroke((Stroke) null, null, null, (String) null, (String) null);

                cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

                cartesian.title("Recent 365 days stock price of " + newsArray.get(0).getTicker());

                cartesian.yAxis(0).title("Price");
                cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

                List<DataEntry> seriesData = new ArrayList<>();

                int count = Closing_Prices.size();
                if(count > dateArray.size())
                {
                  count = dateArray.size();
                }

                for (int i=0 ; i<count; i++)
                {
                    seriesData.add(new CustomDataEntry(dateArray.get(i).toString() , Closing_Prices.get(i).doubleValue()  , High_Prices.get(i).doubleValue() , Low_Prices.get(i).doubleValue()));

                }
//                seriesData.add(new CustomDataEntry("1986", 3.6, 2.3, 2.8));
//                seriesData.add(new CustomDataEntry("1987", 7.1, 4.0, 4.1));
//                seriesData.add(new CustomDataEntry("1988", 8.5, 6.2, 5.1));
//                seriesData.add(new CustomDataEntry("1989", 9.2, 11.8, 6.5));
//                seriesData.add(new CustomDataEntry("1990", 10.1, 13.0, 12.5));
//                seriesData.add(new CustomDataEntry("1991", 11.6, 13.9, 18.0));
//                seriesData.add(new CustomDataEntry("1992", 16.4, 18.0, 21.0));
//                seriesData.add(new CustomDataEntry("1993", 18.0, 23.3, 20.3));
//                seriesData.add(new CustomDataEntry("1994", 13.2, 24.7, 19.2));
//                seriesData.add(new CustomDataEntry("1995", 12.0, 18.0, 14.4));
//                seriesData.add(new CustomDataEntry("1996", 3.2, 15.1, 9.2));
//                seriesData.add(new CustomDataEntry("1997", 4.1, 11.3, 5.9));
//                seriesData.add(new CustomDataEntry("1998", 6.3, 14.2, 5.2));
//                seriesData.add(new CustomDataEntry("1999", 9.4, 13.7, 4.7));
//                seriesData.add(new CustomDataEntry("2000", 11.5, 9.9, 4.2));
//                seriesData.add(new CustomDataEntry("2001", 13.5, 12.1, 1.2));
//                seriesData.add(new CustomDataEntry("2002", 14.8, 13.5, 5.4));
//                seriesData.add(new CustomDataEntry("2003", 16.6, 15.1, 6.3));
//                seriesData.add(new CustomDataEntry("2004", 18.1, 17.9, 8.9));
//                seriesData.add(new CustomDataEntry("2005", 17.0, 18.9, 10.1));
//                seriesData.add(new CustomDataEntry("2006", 16.6, 20.3, 11.5));
//                seriesData.add(new CustomDataEntry("2007", 14.1, 20.7, 12.2));
//                seriesData.add(new CustomDataEntry("2008", 15.7, 21.6, 10));
//                seriesData.add(new CustomDataEntry("2009", 12.0, 22.5, 8.9));

                Set set = Set.instantiate();
                set.data(seriesData);
                Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
                Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
                Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");

                Line series1 = cartesian.line(series1Mapping);
                series1.name("Brandy");
                series1.hovered().markers().enabled(true);
                series1.hovered().markers()
                        .type(MarkerType.CIRCLE)
                        .size(4d);
                series1.tooltip()
                        .position("right")
                        .anchor(Anchor.LEFT_CENTER)
                        .offsetX(5d)
                        .offsetY(5d);

                cartesian.legend().enabled(true);
                cartesian.legend().fontSize(13d);
                cartesian.legend().padding(0d, 0d, 10d, 0d);

                anyChartView.setChart(cartesian);
//                System.out.println("I am here after setting anychart view");








            }
        });

        System.out.println(" I clicked the getStockNews Button");

//        Get StockPrice.
        StockService stockService = new StockService(News_Activity.this);

//        VolletResonselistener will listen to the response and give us either the actual value or the error.
        stockService.getStockPrice(newsArray.get(0).getTicker(), new StockService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(News_Activity.this , "Something Wrong" , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(StockModel model) {
//                Toast.makeText(News_Activity.this , "The opening price for the previous day was " + model.getOpening_price().toString() , Toast.LENGTH_SHORT).show();
                previous_day_closing_price = Boolean.TRUE;


            }
        });

        //Sample.
        //Sample



    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }

    //Convert UNIX to Date String
    private   ArrayList<String>  convertUnixToDate(ArrayList<Integer> unix)
    {
        ArrayList<String> dateArray = new ArrayList<String>();

        //Loop through the array.

        for (int i=0 ; i<unix.size();i++)
        {
            Integer unix_seconds = unix.get(i);
            //convert seconds to milliseconds
            Date date = new Date(unix_seconds*1000L);
            // format of the date
            SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
            String java_date = jdf.format(date);
            dateArray.add(java_date);
//            System.out.println("\n Unix converted to "+java_date+"\n");

        }

        return dateArray;





    }


}