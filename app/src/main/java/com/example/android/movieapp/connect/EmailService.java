package com.example.android.movieapp.connect;

import com.example.android.movieapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

    public interface EmailService {



    @GET("users/{email}")
    Call<User> getUser(@Path("email") String email);

    @POST("users")
    Call<Void> setUser(@Body User user);

    @POST("users/{email}/register/{token}")
    Call<Void> getToken(@Path("email") String email, @Path("token") String token);

    @PUT("tokens/{email}")
    Call<Void> setToken(@Path("email") String email);

    @PUT("users/password")
    Call<ErrorMessage> setNewPassword(@Body NewPasswordObj newPasswordObj);

    @POST("users/validate")
    Call<Void> setLogin(@Body User user);

}
