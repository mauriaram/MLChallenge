package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;

public interface ISearchUICallback {
    void onSearchResultsDone(SearchResults searchResults);
    void onSearchError(String reason);
}
