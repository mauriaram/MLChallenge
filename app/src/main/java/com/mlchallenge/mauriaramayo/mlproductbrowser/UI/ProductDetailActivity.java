package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.app.Activity;
import android.os.Bundle;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;

public class ProductDetailActivity extends Activity implements IProductDetailUICallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }

    @Override
    public void onProductDetailReady() {
        //TODO: load product's pictures in other thread.

    }

    @Override
    public void onProductDescriptionReady() {

    }
}
