package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.os.Bundle;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.ProductManager;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

import java.util.ArrayList;

public class SearchActivity extends Activity implements ISearchUICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ProductManager pm = new ProductManager(this);
        pm.startSearch("chromecast");

        //pm.startGetProductDetails("MLU445072353");

        //pm.startGetProductDescription("MLU445072353");

    }


    @Override
    public void onSearchResultsDone(SearchResults searchResults) {
        System.out.println(searchResults.toString());
    }

    @Override
    public void onSearchError(String reason) {

    }
}
