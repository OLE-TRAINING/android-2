package com.example.android.movieapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.connect.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountRepository {

    int code;
    public LiveData<ResponseService<User>>  setUser(User user){

        final ErrorMessage errorMessage = new ErrorMessage();
        ResponseService auxUser = new ResponseService();
        Call<Void> call = new RetrofitConfig().getEmailService().setUser(user);
        final MutableLiveData<ResponseService<User>> data = new MutableLiveData<>();
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {

                    auxUser.set(response.body());
                    auxUser.setStatus(true);
                    data.postValue(auxUser);
                }

                else {
                    try {
                        auxUser.setStatus(false);
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        errorMessage.setMessage((jObjError.getString("message")));
                        errorMessage.setKey((jObjError.getString("key")));
                        auxUser.setErrorMessage(errorMessage);
                        data.setValue(auxUser);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println( "Erro ao buscar o email:" + t.getMessage());
            }


        });
        return data;
    }
}
