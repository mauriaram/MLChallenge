package com.mlchallenge.mauriaramayo.mlproductbrowser.Model;

import org.json.JSONObject;

public class ProductItem {
    private String id;
    private String title;
    private String currencyId;
    private String thumbnail; //url
    private ItemDescription itemDescription = null;

    public ProductItem(JSONObject jsonObject) {
        fromJSon(jsonObject);
    }

    private void fromJSon(JSONObject jsonObject) {


    }
}
