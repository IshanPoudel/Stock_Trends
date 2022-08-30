package com.example.stockapi;

public class NewsModel {

    public String ticker;
    public String news_headline;
    public String news_sentiment;
    public String news_summary;
    public String news_provider;
    public String news_link;
    public String image_link;

    public NewsModel()
    {

    }

    public void initialize(String ticker , String news_headline , String news_sentiment, String news_summary , String news_provider , String news_link , String image_link)
    {
        this.ticker = ticker;
        this.news_headline=news_headline;
        this.news_sentiment=news_sentiment;
        this.news_summary=news_summary;
        this.news_provider = news_provider;
        this.news_link=news_link;
        this.image_link = image_link;
    }

    public String getTicker() {return ticker;}
    public String getNews_headline()
    {
        return news_headline;
    }
    public String getNews_summary()
    {
        return news_summary;
    }
    public String getNews_provider()
    {
        return news_provider;
    }
    public String getNews_link()
    {
        return  news_link;
    }
    public String getImage_link()
    {
        return image_link;
    }
    public String getNews_sentiment() {return news_sentiment;}

    @Override
    public String toString()
    {
        return  this.ticker + " " + getNews_headline() ;
    }


}
