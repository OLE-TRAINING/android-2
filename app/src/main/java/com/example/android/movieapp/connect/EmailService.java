package com.example.android.movieapp.connect;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmailService {

    @GET("users/{email}?gw-app-key=593c3280aedd01364c73000d3ac06d76")
    Call<User> buscarEmail(@Path("email") String email);
}
