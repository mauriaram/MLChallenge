package com.mlchallenge.mauriaramayo.mlproductbrowser.Support;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;

import java.util.ArrayList;

public interface IParserTaskCallback {
    void onParseSearchResultsReady(SearchResults searchResults);
}