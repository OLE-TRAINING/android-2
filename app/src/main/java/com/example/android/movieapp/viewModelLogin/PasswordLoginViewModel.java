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
import com.example.android.movieapp.repository.LoginRepository;
import com.example.android.movieapp.repository.TokenRepository;

public class PasswordLoginViewModel extends StatusSuperClass {

    private TokenRepository tokenRepository;

    private LoginRepository loginRepository;
    private MutableLiveData<Boolean> linkState = new MutableLiveData<>();

    public PasswordLoginViewModel(@NonNull Application application) {

        super(application);
        this.tokenRepository = new TokenRepository();
        this.loginRepository = new LoginRepository();
    }

    public void init(User user) {



        getLoading().setValue(true);
        loginRepository.setLogin(user).observeForever(new Observer<ResponseService<User>>() {
            @Override
            public void onChanged(@Nullable ResponseService<User> responseService) {

                ErrorMessage errorMessage = responseService.getErrorMessage();
                if (responseService.getStatus()) {
                    getStatus().setValue("Ok");

                } else {
                    getStatus().setValue(errorMessage.getMessage());
                    getLoading().setValue(false);

                }

            }

        });


    }

    public void initSendToken(String email) {

        getLoading().setValue(true);
        tokenRepository.setToken(email).observeForever(new Observer<ResponseService<User>>() {
            @Override
            public void onChanged(@Nullable ResponseService<User> responseService) {

                if (responseService.getStatus()) {

                } else {
                    getLoading().setValue(false);

                }

            }

        });

    }

    public MutableLiveData<Boolean> getLinkState() {
        return linkState;
    }
}
