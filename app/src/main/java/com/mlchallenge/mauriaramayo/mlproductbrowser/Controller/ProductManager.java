package com.mlchallenge.mauriaramayo.mlproductbrowser.Controller;

import android.util.Log;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.IParserTaskListener;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.JSONParser;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.RESTClient;
import com.mlchallenge.mauriaramayo.mlproductbrowser.UI.ISearchUICallback;

import org.json.JSONObject;

import java.security.InvalidParameterException;

public class ProductManager implements IProductManagerCallback, IParserTaskListener {

    private static final String TAG = ProductManager.class.getSimpleName();
    private ISearchUICallback searchUICallback;
    ProductItem selectedProduct = null;

    public ProductManager(ISearchUICallback searchUICallback) {
        this.searchUICallback = searchUICallback;
    }

    public void startSearch(String searchString){
        try  {
            Log.d(TAG, "startSearch: " + searchString);
            String searchStringValid = checkSearchString(searchString);
            RESTClient restClient = new RESTClient(RESTClient.SERVICE.SEARCH, this);
            restClient.execute(new String[]{searchString});
        } catch (InvalidParameterException ex) {
            System.out.println("Search can't start.");
            ex.printStackTrace();
        }
    }

    public void startGetProductDetails(ProductItem productItem) {
        try {
            selectedProduct = productItem;
            RESTClient restClient = new RESTClient(RESTClient.SERVICE.PRODUCT_DETAIL, this);
            restClient.execute(new String[]{productItem.getId()});
        } catch (InvalidParameterException ex) {
            System.out.println("Get product detail can't start.");
            ex.printStackTrace();
        }
    }

    public void startGetProductDescription(String id) {
        try {
            RESTClient restClient = new RESTClient(RESTClient.SERVICE.PRODUCT_DESCRIPTION, this);
            restClient.execute(new String[]{id});
        } catch (InvalidParameterException ex) {
            System.out.println("get Product description can't start.");
            ex.printStackTrace();
        }
    }

    @Override
    public void onSearchReady(JSONObject jsonObject) {
        JSONParser.parseSearchResults(jsonObject, this);
    }

    @Override
    public void onProductDetailsReady(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
        JSONParser.parseProductDetail(jsonObject, selectedProduct,this);
    }

    @Override
    public void onProductDescriptionReady(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
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
    public void onParseSearchResultsDone(SearchResults searchResults) {
        searchUICallback.onSearchResultsDone(searchResults);
    }

    @Override
    public void onParseProductDetailDone(ProductItem productItem) {

    }

    @Override
    public void onParseProductDescriptionDone(ProductItem productItem) {

    }
}


