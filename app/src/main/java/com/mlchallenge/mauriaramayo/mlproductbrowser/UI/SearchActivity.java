package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.os.Bundle;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.ProductManager;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

import java.util.ArrayList;

public class SearchActivity extends Activity implements ISearchUICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /*ProductManager pm = new ProductManager();
        pm.startAsyncSearch("chromecast",this);
        */
    }

    @Override
    public void onSearchResultsDone(ArrayList<ProductItem> productItems) {

    }

    @Override
    public void onSearchError(String reason) {

    }
}
