package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.ProductManager;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

public class ProductDetailActivity extends Activity implements IProductDetailUICallback {

    private static final String TAG = ProductDetailActivity.class.getSimpleName();
    private String id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        id = getIntent().getExtras().getString("id");
        title = getIntent().getExtras().getString("title");
        if (id ==null || id.length()==0) {
            Log.e(TAG, "onCreate: Invalid id", null);
            return;
        }


        initViews();
    }

    private void initViews() {
        TextView textViewTitle = (TextView) findViewById(R.id.title);
        textViewTitle.setText(title);
    }

    @Override
    public void onProductDetailReady() {
        //TODO: load product's pictures in other thread.

    }

    @Override
    public void onProductDescriptionReady() {

    }
}
