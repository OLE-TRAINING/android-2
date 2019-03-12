package com.example.android.movieapp.viewModelLogin;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.movieapp.SuperClass.StatusSuperClass;
import com.example.android.movieapp.connect.NewPasswordObj;
import com.example.android.movieapp.connect.ErrorMessage;
import com.example.android.movieapp.connect.ResponseService;
import com.example.android.movieapp.connect.User;
import com.example.android.movieapp.repository.NewPasswordRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewPasswordViewModel extends StatusSuperClass {

    private NewPasswordRepository newPasswordRepository;
    private ErrorMessage errorMessage;

    private MutableLiveData<Boolean> invalidToken = new MutableLiveData<>();
    private MutableLiveData<String> invalidPassword = new MutableLiveData<>();

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);


    public NewPasswordViewModel(@NonNull Application application) {
        super(application);

        this.newPasswordRepository = new NewPasswordRepository();

    }

    public static boolean passwordValidation(String name) {
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public void init(NewPasswordObj newPasswordObj) {

       if (!(passwordValidation(newPasswordObj.getNewPassword()) && (newPasswordObj.getNewPassword()).equals(newPasswordObj.getNewPasswordConfirmation()) && (newPasswordObj.getConfirmationToken().length() == 6))) {

            if(!passwordValidation(newPasswordObj.getNewPassword())) {

                getInvalidPassword().setValue("InvalidPassword");
            }
            else if(!(newPasswordObj.getNewPassword()).equals(newPasswordObj.getNewPasswordConfirmation()))
                getInvalidPassword().setValue("DifferentPassword");
            if(!(newPasswordObj.getConfirmationToken().length() == 6))
                getInvalidToken().setValue(false);

        } else {

            getLoading().setValue(true);

            newPasswordRepository.setNewPassword(newPasswordObj).observeForever(new Observer<ResponseService<User>>() {
                @Override
                public void onChanged(@Nullable ResponseService<User> responseService) {
                    System.out.println("Service Passwrod entrouuuu");
                    if (responseService.getStatus()) {

                        getStatus().setValue("Ok");

                    } else{
                        errorMessage = responseService.getErrorMessage();
                        getStatus().setValue(errorMessage.getMessage());
                        getLoading().setValue(false);
                    }

                }

            });

        }
   }


    public MutableLiveData<Boolean> getInvalidToken() {
        return invalidToken;
    }

    public MutableLiveData<String> getInvalidPassword() {
        return invalidPassword;
    }

}
