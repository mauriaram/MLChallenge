package com.mlchallenge.mauriaramayo.mlproductbrowser.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Model.ProductItem;
import com.mlchallenge.mauriaramayo.mlproductbrowser.R;
import com.mlchallenge.mauriaramayo.mlproductbrowser.Support.DownLoadImageTask;

import java.util.List;

public class SearchResultsDataAdapter extends RecyclerView.Adapter {


    private List<ProductItem> productItemList;

    public SearchResultsDataAdapter(List<ProductItem> productItemList) {
        this.productItemList = productItemList;

    }


    public String getItemIdFromPosition(int position) {
        return productItemList.get(position).getId();
    }

    public ProductItem getProductItem(int position) {
        return productItemList.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productItemList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;

        productViewHolder.price.setText( productItemList.get(position).getPriceFormatted());
        productViewHolder.title.setText(productItemList.get(position).getTitle());
        productViewHolder.thumbnail.setImageBitmap(null);
        DownLoadImageTask downloadImageTask = new DownLoadImageTask(productViewHolder.thumbnail);
        downloadImageTask.execute(productItemList.get(position).getThumbnail());
    }

    public void clear() {
        productItemList.clear();
        notifyDataSetChanged();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView title, price;
        public ImageView thumbnail;
        public ProductViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

    }


}
