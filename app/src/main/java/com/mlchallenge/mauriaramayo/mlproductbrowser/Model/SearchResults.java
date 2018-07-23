package com.mlchallenge.mauriaramayo.mlproductbrowser.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchResults {
    private static final String RESULTS = "results";
    private ArrayList<ProductItem> productItems;

    public SearchResults(JSONObject searchResults) {
        productItems = new ArrayList<>();

        //Fill the arraylist.
        JSONArray jsonArrayResults = null;
        try {
            jsonArrayResults = searchResults.getJSONArray(RESULTS);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonArrayResults == null) {
            return;
        }
        for (int i = 0; i < jsonArrayResults.length(); i++) {
            try {
                JSONObject object = jsonArrayResults.getJSONObject(i);
                productItems.add(new ProductItem(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
