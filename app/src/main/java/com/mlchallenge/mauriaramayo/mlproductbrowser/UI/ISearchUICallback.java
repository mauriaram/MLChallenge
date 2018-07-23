package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;

import java.util.ArrayList;

public interface ISearchUICallback {
    void onSearchResultsDone(ArrayList<ProductItem> productItems);
    void onSearchError(String reason);
}
