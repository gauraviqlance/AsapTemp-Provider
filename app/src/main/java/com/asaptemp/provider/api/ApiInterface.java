package com.asaptemp.provider.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("json?")
    Call<ResponseBody> getResponse(@Query("latlng") String param1, @Query("key") String param2);
}
