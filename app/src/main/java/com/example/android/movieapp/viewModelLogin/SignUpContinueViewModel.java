package com.example.android.movieapp.viewModelLogin;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.movieapp.superClass.StatusSuperClass;
import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.model.User;
import com.example.android.movieapp.repository.TokenRepository;

public class SignUpContinueViewModel extends StatusSuperClass {
    private TokenRepository tokenRepository;
    private ErrorMessage errorMessage;
    private MutableLiveData<Boolean> tokenValidation = new MutableLiveData<>();


    public SignUpContinueViewModel(@NonNull Application application) {
        super(application);
        this.tokenRepository = new TokenRepository();
    }

    public void init(String email, String token){

        if(token.length()==6) {

            getLoading().setValue(true);
            getTokenValidation().setValue(true);

            tokenRepository.getToken(email, token).observeForever(new Observer<ResponseService<User>>() {
                @Override
                public void onChanged(@Nullable  ResponseService<User> responseService) {

                    if (responseService.getStatus()) {

                        getStatus().setValue("Ok");

                    } else {
                        errorMessage = responseService.getErrorMessage();
                        getStatus().setValue(errorMessage.getMessage());
                        getLoading().setValue(false);

                    }
                }
            });
        }else getTokenValidation().setValue(false);
    }
    public void initSendAgain(String email){

        tokenRepository.setToken(email).observeForever(new Observer<ResponseService<User>>() {
            @Override
            public void onChanged(@Nullable  ResponseService<User> responseService) {

                if (responseService.getStatus()) {

                    System.out.println("TOKEN CERTO VIEW MODEL");

                }
                else{
                    System.out.println("TKOEN INVALIDO  VIEW MODEL");
                }
            }
        });

    }

    public MutableLiveData<Boolean> getTokenValidation() {
        return tokenValidation;
    }


}
