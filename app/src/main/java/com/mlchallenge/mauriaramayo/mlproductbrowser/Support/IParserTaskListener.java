package com.mlchallenge.mauriaramayo.mlproductbrowser.Support;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;

import java.util.ArrayList;

public interface IParserTaskListener {
    void onParseSearchResultsDone(SearchResults searchResults);
    void onParseProductDetailDone(ProductItem productItem);
    void onParseProductDescriptionDone(ProductItem productItem);
}