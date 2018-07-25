package com.mlchallenge.mauriaramayo.mlproductbrowser.Controller;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.IParserTaskCallback;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.JSONParser;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.RESTClient;
import com.mlchallenge.mauriaramayo.mlproductbrowser.UI.ISearchUICallback;

import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class ProductManager implements IProductManagerCallback, IParserTaskCallback {
    private ISearchUICallback searchUICallback;

    public ProductManager(ISearchUICallback searchUICallback) {
        this.searchUICallback = searchUICallback;
    }

    public void startSearch(String searchString){
        try  {
            String searchStringValid = checkSearchString(searchString);
            RESTClient restClient = new RESTClient(RESTClient.SERVICE.SEARCH, this);
            restClient.execute(new String[]{searchString});
        } catch (InvalidParameterException ex) {
            System.out.println("Search can't start yet.");
        }
    }

    public void startGetProductDetails(String id) {
        RESTClient restClient = new RESTClient(RESTClient.SERVICE.PRODUCT_DETAIL, this);
        restClient.execute(new String[]{id});
    }

    public void startGetProductDescription(String id) {
        RESTClient restClient = new RESTClient(RESTClient.SERVICE.PRODUCT_DESCRIPTION, this);
        restClient.execute(new String[]{id});
    }

    @Override
    public void onSearchReady(JSONObject jsonObject) {
        JSONParser.parseSearchResults(jsonObject, this);
    }

    @Override
    public void onSearchFailed() {

    }

    @Override
    public void onProductDetailsReady(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
    }

    @Override
    public void onProductDetailsFailed() {

    }

    @Override
    public void onProductDescriptionReady(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
    }

    @Override
    public void onProductDescriptionFailed() {

    }

    /**
     * This method must check the search string to avoid search too open or invalid characters.
     * @param searchString
     */
    private String checkSearchString(String searchString) throws InvalidParameterException {
        //TODO: Add more rules.
        if (searchString != null && searchString.length()>2) {
            return searchString;
        } else {
            throw new InvalidParameterException("Search too short");
        }
    }

    @Override
    public void onParseSearchResultsReady(SearchResults searchResults) {
        searchUICallback.onSearchResultsDone(searchResults);
    }
}


