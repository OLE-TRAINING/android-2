package com.example.android.movieapp.connect;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;


   public RetrofitConfig(){

       this.retrofit = new Retrofit.Builder()
               .baseUrl("https://ole.dev.gateway.zup.me/client-training/v1/")
               .addConverterFactory(JacksonConverterFactory.create())
               .build();

       System.out.println("retrotrotrotrotrotrotortortoooDOAoddsaasdasdsa");
    }

    public EmailService getEmailService() {
        return this.retrofit.create(EmailService.class);

    }

}
