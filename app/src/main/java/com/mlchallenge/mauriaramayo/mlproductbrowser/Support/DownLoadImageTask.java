package com.mlchallenge.mauriaramayo.mlproductbrowser.Support;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap bitmap = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();

            bitmap = BitmapFactory.decodeStream(is);
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}