package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SearchResultsDataAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProductsAdapter extends RecyclerView.ViewHolder{

        public ProductsAdapter(View itemView) {
            super(itemView);
        }
    }
}
