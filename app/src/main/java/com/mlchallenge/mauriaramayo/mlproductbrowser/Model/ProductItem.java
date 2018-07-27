package com.mlchallenge.mauriaramayo.mlproductbrowser.Model;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Currency;

public class ProductItem {
    private String id;
    private String title;
    private String currencyId;
    private double price;
    private String thumbnail; //url
    private String description = "";
    private String[] pictures = null;

    public ProductItem(String id, String title, String currencyId, double price, String thumbnail) {
        this.id = id;
        this. title = title;
        this.currencyId = currencyId;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }

    public String[] getPictures() {
        return pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormatted() {
        String ret = null;
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        ret = currencyId + " " + format.format(price);
        return ret;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
