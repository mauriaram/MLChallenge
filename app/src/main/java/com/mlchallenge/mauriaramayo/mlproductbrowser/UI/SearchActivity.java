package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.ProductManager;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

import java.util.ArrayList;

public class SearchActivity extends Activity implements ISearchUICallback {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private ProductManager productManager;
    private EditText editTextSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextSearch = (EditText) findViewById(R.id.etSearch);
        productManager = new ProductManager(this);
        //pm.startSearch("chromecast");

        //pm.startGetProductDetails("MLU445072353");

        //pm.startGetProductDescription("MLU445072353");

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = editTextSearch.getText().toString().trim();
                if ( search.length() > 2 ) {
                    productManager.startSearch(search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onSearchResultsDone(SearchResults searchResults) {
        Log.d(TAG, "onSearchResultsDone: results = " + searchResults.getProductCount());
    }

    @Override
    public void onSearchError(String reason) {

    }



}
