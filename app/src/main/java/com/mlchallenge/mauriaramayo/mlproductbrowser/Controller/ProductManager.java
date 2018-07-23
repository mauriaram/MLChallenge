package com.mlchallenge.mauriaramayo.mlproductbrowser.Controller;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.RESTClient;
import com.mlchallenge.mauriaramayo.mlproductbrowser.UI.ISearchUICallback;

import org.json.JSONObject;

public class ProductManager implements IProductManagerCallback{

    public void startAsyncSearch(String searchString, ISearchUICallback callback){
        RESTClient restClient = new RESTClient(RESTClient.SERVICE.SEARCH, this);
        restClient.execute(new String[]{searchString});
    }

    @Override
    public void onSearchReady(JSONObject jsonObject) {
        SearchResults results = new SearchResults(jsonObject);

    }

    @Override
    public void onSearchFailed() {

    }

    @Override
    public void onProductDetailsReady() {

    }

    @Override
    public void onProductDetailsFailed() {

    }

    @Override
    public void onProductDescriptionReady() {

    }

    @Override
    public void onProductDescriptionFailed() {

    }
}


