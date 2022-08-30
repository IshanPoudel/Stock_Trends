package com.example.stockapi;

// the StockModel contanins the basic facts about the data.


public class StockModel {

    public Double opening_price;
    public Double closing_price;
    public Double highest_price;
    public Double lowest_price;
    public String ticker;
    public  int total_volume_traded;

    public StockModel()
    {

    }

    public  void initialize(String ticker , Double opening_price , Double closing_price , Double highest_price , Double lowest_price ,  int total_volume_traded)
    {
        this.ticker = ticker;
        this.opening_price = opening_price;
        this.closing_price = closing_price;
        this.highest_price = highest_price;
        this.lowest_price = lowest_price;
        this.total_volume_traded = total_volume_traded;

    }



    public Double getOpening_price()
    {
        return this.opening_price;
    }

    public Double getClosing_pricee()
    {
        return this.closing_price;
    }

    public Double getHighest_price()
    {
        return  this.highest_price;
    }

    public  Double getLowest_price()
    {
        return  this.lowest_price;
    }

    public int getTotal_volume_traded()
    {
        return this.total_volume_traded;
    }

    @Override
    public String toString()
    {
        return  this.ticker + " has an opening price of " + this.opening_price.toString();
    }


}
