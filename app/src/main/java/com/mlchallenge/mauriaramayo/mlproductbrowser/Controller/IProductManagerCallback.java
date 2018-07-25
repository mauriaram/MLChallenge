package com.mlchallenge.mauriaramayo.mlproductbrowser.Controller;

import org.json.JSONObject;

import java.util.ArrayList;

public interface IProductManagerCallback {
    void onSearchReady(JSONObject jsonObject);
    void onSearchFailed();
    void onProductDetailsReady(JSONObject jsonObject);
    void onProductDetailsFailed();
    void onProductDescriptionReady(JSONObject jsonObject);
    void onProductDescriptionFailed();
}
