package com.example.android.movieapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.android.movieapp.connect.NewPasswordObj;
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

public class NewPasswordRepository {

    private ErrorMessage errorMessage = new ErrorMessage();

    public LiveData<ResponseService<User>> setNewPassword(NewPasswordObj newPasswordObj){

        Call<ErrorMessage> call = new RetrofitConfig().getEmailService().setNewPassword(newPasswordObj);
        ResponseService auxUser = new ResponseService();
        final MutableLiveData<ResponseService<User>> data = new MutableLiveData<>();
        call.enqueue(new Callback<ErrorMessage>() {

            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                System.out.println("ONNNNRESPONSE");
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
            public void onFailure(Call<ErrorMessage> call, Throwable t) {
                System.out.println( "Erro ao buscar o email:" + t.getMessage());
            }


        });
        return data;
    }
}
