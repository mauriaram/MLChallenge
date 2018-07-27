package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.ProductManager;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

public class SearchActivity extends Activity implements ISearchUICallback {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private ProductManager productManager;
    private EditText editTextSearch;
    private RecyclerView recyclerView;
    private SearchResultsDataAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);



        editTextSearch = (EditText) findViewById(R.id.etSearch);
        productManager = new ProductManager(this);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = editTextSearch.getText().toString().trim();
                if ( search.length() > 2 ) {
                    if (productAdapter != null) {
                        productAdapter.clear();
                    }
                    recyclerView.removeOnItemTouchListener(recyclerTouchListener);
                    productManager.startSearch(search);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onSearchResultsDone(final SearchResults searchResults) {
        Log.d(TAG, "onSearchResultsDone: results = " + searchResults.getProductCount());

        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                productAdapter = new SearchResultsDataAdapter(searchResults.getProductItems());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
                recyclerView.addOnItemTouchListener(recyclerTouchListener);
            }
        });
    }

    @Override
    public void onSearchError(String reason) {

    }


    private void openProductDetail(ProductItem productItem) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", productItem.getId());
        bundle.putString("title", productItem.getTitle());
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private RecyclerTouchListener recyclerTouchListener = new RecyclerTouchListener(getBaseContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
        @Override
        public void onClick(View view, int position) {
            try {
                //String id = productAdapter.getItemIdFromPosition(position);
                openProductDetail(productAdapter.getProductItem(position));
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
    });
}
