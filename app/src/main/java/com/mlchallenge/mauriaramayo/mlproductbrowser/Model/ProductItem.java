package com.mlchallenge.mauriaramayo.mlproductbrowser.Model;

import org.json.JSONObject;

public class ProductItem {
    private String id;
    private String title;
    private String currencyId;
    private double price;
    private String thumbnail; //url
    private ItemDescription itemDescription = null;

    public ProductItem(String id, String title, String currencyId, double price, String thumbnail) {
        this.id = id;
        this. title = title;
        this.currencyId = currencyId;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }
}
