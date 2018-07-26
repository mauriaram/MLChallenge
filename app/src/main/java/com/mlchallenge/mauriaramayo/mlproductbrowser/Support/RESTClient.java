package com.mlchallenge.mauriaramayo.mlproductbrowser.Support;

import android.os.AsyncTask;

import com.mlchallenge.mauriaramayo.mlproductbrowser.Controller.IProductManagerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.spec.InvalidParameterSpecException;

import javax.net.ssl.HttpsURLConnection;

public class RESTClient extends AsyncTask<String, Integer, JSONObject>
{

    public enum SERVICE{ SEARCH, PRODUCT_DETAIL, PRODUCT_DESCRIPTION }

    /**
     * The interface to return the result.
     */
    private final IProductManagerCallback productManagerCallback;

    /**
     * The service type to request the data.
     */
    private SERVICE serviceType;


    /**
     * Search query, needs to pass a string word to search parameter.
     */
    private static final String SEARCH_WS = "https://api.mercadolibre.com/sites/MLU/search?q=";

    /**
     * Product detail service, needs to pass an product id.
     */
    private static final String PRODUCT_DETAIL_WS = "https://api.mercadolibre.com/items/";

    /**
     * Product description web service, needs to pass an product id.
     */
    private static final String PRODUCT_DESCRIPTION_WS = "https://api.mercadolibre.com/items/";


    public RESTClient(SERVICE serviceType, IProductManagerCallback productManagerCallback) {
        this.serviceType = serviceType;
        this.productManagerCallback = productManagerCallback;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        if (params != null && params.length > 0 && isInternetAvailable()) {
            String urlString = null;
            try {
                urlString = completeURL(serviceType, params);
                URL url = new URL(urlString);
                return executeService(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidParameterSpecException e) {
                e.printStackTrace();
                //It must execute a callback to notify the condition error.
                return null;
            }
        }
        return null;
    }

    private boolean isInternetAvailable() {
        //TODO: Implement.
        return true;
    }

    private JSONObject executeService(URL url) throws IOException {
        HttpsURLConnection httpsURLConnection = null;
        InputStream data = null;
        JSONObject ret = null;
        try {
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setReadTimeout(5000);
            httpsURLConnection.setConnectTimeout(5000);
            httpsURLConnection.connect();
            int responseCode = httpsURLConnection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            data = httpsURLConnection.getInputStream();
            if (data != null) {
                ret = new JSONObject(inputStreamToString(data));
                data.close();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (data != null) {
                data.close();
            }
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
        return ret;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        switch (serviceType) {
            case SEARCH:
                //TODO: Send a status to callback as a way to check error.
                productManagerCallback.onSearchReady(jsonObject);
                break;
            case PRODUCT_DETAIL:
                productManagerCallback.onProductDetailsReady(jsonObject);
                break;
            case PRODUCT_DESCRIPTION:
                productManagerCallback.onProductDescriptionReady(jsonObject);
                break;
        }
    }

    private String inputStreamToString(InputStream is) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                byteArrayOutputStream.write(i);
                i = is.read();
            }
            return byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String completeURL(SERVICE serviceType, String ...param ) throws InvalidParameterSpecException {
        if (param[0] == null || param[0].length()==0) {
            //Currently is not supported a service without parameters.
            throw new InvalidParameterSpecException("Invalid parameters for service " +
                    serviceType.name());
        }
        StringBuilder urlString = new StringBuilder();
        String productId = null;
        switch (serviceType) {
            case SEARCH:
                String searchWord;
                searchWord = param[0];
                urlString.append(SEARCH_WS).append(searchWord);
                break;
            case PRODUCT_DETAIL:
                productId = param[0];
                urlString.append(PRODUCT_DETAIL_WS).append(productId);
                break;
            case PRODUCT_DESCRIPTION:
                productId = param[0];
                urlString.append(PRODUCT_DESCRIPTION_WS).append(productId).append("/description");
                break;
            default:
        }
        return urlString.toString();
    }
}
