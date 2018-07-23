package com.mlchallenge.mauriaramayo.mlproductbrowser.Controller;

import org.json.JSONObject;

import java.util.ArrayList;

public interface IProductManagerCallback {
    void onSearchReady(JSONObject jsonObject);
    void onSearchFailed();
    void onProductDetailsReady();
    void onProductDetailsFailed();
    void onProductDescriptionReady();
    void onProductDescriptionFailed();
}
