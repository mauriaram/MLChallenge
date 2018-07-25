package com.mlchallenge.mauriaramayo.mlproductbrowser.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResults {
    private ArrayList<ProductItem> productItems;
    private int productCount;

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public ArrayList<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(ArrayList<ProductItem> productItems) {
        this.productItems = productItems;
    }
}



