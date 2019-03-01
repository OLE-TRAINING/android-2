package com.example.android.movieapp.connect;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    public final static String VALUE_SERVICE =
            "593c3280aedd01364c73000d3ac06d76";
    public final static String NAME_SERVICE =
            "gw-app-key";
    public final static String URL_SERVICE =
            "https://ole.dev.gateway.zup.me/client-training/v1/";





   public RetrofitConfig(){

       OkHttpClient.Builder client = new OkHttpClient.Builder();

      client.addInterceptor(new Interceptor() {
           @Override
           public Response intercept(Chain chain) throws IOException {
               Request request = chain.request();
               client.connectTimeout(30,TimeUnit.SECONDS); // connect timeout
               client.readTimeout(30, TimeUnit.SECONDS);
               HttpUrl url = request.url().newBuilder().addQueryParameter(NAME_SERVICE,VALUE_SERVICE).build();
               request = request.newBuilder().url(url).build();
               return chain.proceed(request); }
       });
      client.addInterceptor(logger);

       this.retrofit = new Retrofit.Builder()
               .baseUrl(URL_SERVICE)
               .addConverterFactory(GsonConverterFactory.create())
               .client(client.build())
               .build();

    }

    private static HttpLoggingInterceptor logger =
        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

   public EmailService getEmailService() {
        return this.retrofit.create(EmailService.class);

    }



}
