package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;

import java.util.ArrayList;

public interface ISearchUICallback {
    void onSearchResultsDone(SearchResults searchResults);
    void onSearchError(String reason);
}
