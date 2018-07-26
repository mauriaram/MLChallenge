package com.mlchallenge.mauriaramayo.mlproductbrowser.Support;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.SearchResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    private static final String RESULTS = "results";

    public static void parseSearchResults(final JSONObject searchResultsJSON, final IParserTaskListener parseTaskListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList productItems;
                productItems = new ArrayList<>();
                int count = 0;

                //Fill the arraylist.
                JSONArray jsonArrayResults = null;
                try {
                    jsonArrayResults = searchResultsJSON.getJSONArray(RESULTS);
                    JSONObject jsonObjectPaging = searchResultsJSON.getJSONObject("paging");
                    count = jsonObjectPaging.getInt("primary_results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (jsonArrayResults == null) {
                    //TODO: Make a better way to return errors.
                    return;
                }

                for (int i = 0; i < jsonArrayResults.length(); i++) {
                    try {
                        JSONObject jsonProduct = jsonArrayResults.getJSONObject(i);
                        String id = jsonProduct.getString("id");
                        String title = jsonProduct.getString("title");
                        String currencyId = jsonProduct.getString("currency_id");
                        double price = jsonProduct.getDouble("price");
                        String thumbnail = jsonProduct.getString("thumbnail");
                        productItems.add(new ProductItem(id,title,currencyId,price,thumbnail));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                SearchResults searchResults = new SearchResults();
                searchResults.setProductItems(productItems);
                searchResults.setProductCount(count);
                parseTaskListener.onParseSearchResultsDone(searchResults);
            }
        }, "ParseSearchResultsThread").start();

    }

    public static void parseItemDescription(JSONObject jsonObject, ProductItem productItem, IParserTaskListener taskListener) {
        try {
            String description = jsonObject.getString("description");
            productItem.setDescription(description);
            taskListener.onParseProductDescriptionDone(productItem);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseProductDetail(JSONObject jsonObject, ProductItem productItem, IParserTaskListener taskListener) {
        String[] pictures;
        try {
            JSONArray jsonArrayPictures = jsonObject.getJSONArray("pictures");
            pictures = new String[jsonArrayPictures.length()];
            for (int i = 0; i < jsonArrayPictures.length(); i++) {
                pictures[i] = jsonArrayPictures.getJSONObject(i).getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        taskListener.onParseProductDescriptionDone(productItem);
    }
}
