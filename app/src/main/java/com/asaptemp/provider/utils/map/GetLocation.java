package com.asaptemp.provider.utils.map;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.asaptemp.provider.R;
import com.asaptemp.provider.api.ApiInterface;
import com.asaptemp.provider.map.Address;
import com.asaptemp.provider.utils.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetLocation {

    private Retrofit retrofit;
    private ApiInterface apiInterface;
    private String addressHeader="";


    public String getAddress(final Context context, final double latitude, final double longitude) {
        final Address address = new Address();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/geocode/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getResponse(latitude + "," + longitude,
                context.getResources().getString(R.string.map_api_key));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e("sUCESS", "SUCESS" + response.body());
                if (response.body() != null) {
                    try {
                        String bodyString = new String(response.body().bytes());
                        Log.e("sUCESS", "bodyString" + bodyString);
                        JSONObject jsonObj = new JSONObject(bodyString);
                        JSONArray jsonArray = jsonObj.optJSONArray("results");
                        if (jsonArray.length() > 0) {
                            JSONArray addressArray = jsonArray.optJSONObject(0).optJSONArray("address_components");
                            addressArray.optJSONObject(0).optString("long_name");
                            address.setCity(addressArray.optJSONObject(2).optString("long_name"));
                            address.setState(addressArray.optJSONObject(3).optString("long_name"));
                            if (addressArray.optJSONObject(4).optString("long_name") != null)
                                address.setCountry(addressArray.optJSONObject(4).optString("long_name"));
                            address.setLatitude(latitude);
                            address.setLongitude(longitude);
                            address.setPincode(addressArray.optJSONObject(5).optString("long_name"));
                            addressHeader = addressArray.optJSONObject(0).optString("long_name");
                            String address = jsonArray.optJSONObject(0).optString("formatted_address");
                            addressHeader = address;
                            Log.v("Formatted Address", "" + AppConstants.addressHeader);
                        } else {
                            addressHeader = "" + latitude + "" + longitude;
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();

                    }
                } else {
                    addressHeader = "" + latitude + "" + longitude;
                }

                /*//BroadCast Listner
                Intent intent = new Intent("location");
                // You can also include some extra data.
                intent.putExtra("message", "This is my message!");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("onFailure", "onFailure" + call.request().url());
                addressHeader = "" + latitude + "" + longitude;

            }
        });

        return address.getMapAddress();
    }
}
