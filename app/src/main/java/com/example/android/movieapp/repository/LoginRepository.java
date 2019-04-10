package com.example.android.movieapp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.android.movieapp.connect.EmailService;
import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.connect.RetrofitConfig;
import com.example.android.movieapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {


    private EmailService emailService;
    String nameHeader;
    private ErrorMessage errorMessage;

    public LiveData<ResponseService<User>> getUser(String email) {
        errorMessage = new ErrorMessage();
        ResponseService auxUser = new ResponseService();
        Call<User> call = new RetrofitConfig().getEmailService().getUser(email);
        final MutableLiveData<ResponseService<User>> data = new MutableLiveData<>();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    System.out.println(user);
                    auxUser.set(response.body());
                    auxUser.setStatus(true);

                    data.postValue(auxUser);
                } else {
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
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("EMAILService   ", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

    public LiveData<ResponseService<User>> setLogin(User user) {
        errorMessage = new ErrorMessage();
        ResponseService auxUser = new ResponseService();
        Call<Void> call = new RetrofitConfig().getEmailService().setLogin(user);
        final MutableLiveData<ResponseService<User>> data = new MutableLiveData<>();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

//                            nameHeader = response.headers().get("x-access-token");
//                            System.out.println("AQUII SERÁ PRINTADO O HEADEEER");
//                            System.out.println(nameHeader);

                if (response.code() == 200) {

                    auxUser.set(response.body());
                    auxUser.setStatus(true);
                    data.postValue(auxUser);
                } else {
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
                Log.e("EMAILService   ", "Erro ao buscar o email:" + t.getMessage());
            }
        });

        return data;
    }

    public void getListGenres() {



    }
}
