package com.example.android.movieapp.viewModelLogin;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.android.movieapp.superClass.StatusSuperClass;
import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.model.User;
import com.example.android.movieapp.repository.LoginRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginViewModel extends StatusSuperClass {


    public LoginRepository looginRepository;
    private LiveData<User> user;
    private ErrorMessage errorMessage;
    public int flag = 0;

    private MutableLiveData<String> nextScreen = new MutableLiveData<>();
    private MutableLiveData<Boolean> emailValid = new MutableLiveData<>();
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
    private LoginRepository loginRepository;
    public static User userObj;


    public LoginViewModel(Application app) {
        super(app);
        this.loginRepository = new LoginRepository();
    }

    public void init(String email) {
        if (this.user != null) {

            return;
        }

        if (validarEmail(email)) {

            getLoading().setValue(true);

            loginRepository.getUser(email).observeForever(new Observer<ResponseService<User>>() {
                @Override
                public void onChanged(@Nullable ResponseService<User> responseService) {

                    if (responseService.getStatus()) {
                        
                        User usuario = responseService.get();

                        if ((usuario.getRegistrationStatus()).equals("PENDING"))
                            getNextScreen().setValue("PENDING");
                        else if ((usuario.getRegistrationStatus()).equals("REGISTERED"))
                            getNextScreen().setValue("REGISTERED");
                        else if ((usuario.getRegistrationStatus()).equals("INEXISTENT"))
                            getNextScreen().setValue("INEXISTENT");
                        else getNextScreen().setValue("TERACAO");
                        getLoading().setValue(false);
                        //LoginViewModel.userObj = usuario;
                    }else {
                        errorMessage = responseService.getErrorMessage();
                        getStatus().setValue(errorMessage.getMessage());
                    }
                }
            });
        } else
            getEmailValid().setValue(false);

    }

    public MutableLiveData<String> getNextScreen() {
        return nextScreen;
    }

    public MutableLiveData<Boolean> getEmailValid() {
        return emailValid;
    }

    public LiveData<User> getUser() {

        return this.user;
    }

    public static boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

}
