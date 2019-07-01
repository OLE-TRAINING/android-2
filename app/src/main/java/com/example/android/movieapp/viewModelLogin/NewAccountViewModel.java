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
import com.example.android.movieapp.repository.NewAccountRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewAccountViewModel extends StatusSuperClass {

    private static final String NAME_PATTERN = "^[a-zA-Z]+$";
    private static final String USER_PATTERN = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$";

    private static final Pattern pattern = Pattern.compile(NAME_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern2 = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
    private static final Pattern pattern3 = Pattern.compile(USER_PATTERN, Pattern.CASE_INSENSITIVE);

    private MutableLiveData<Boolean> userNameValidation = new MutableLiveData<>();
    private MutableLiveData<Boolean> nameValidation = new MutableLiveData<>();
    private MutableLiveData<Boolean> passwordValidation = new MutableLiveData<>();

    private ErrorMessage errorMessage;

    private NewAccountRepository newAccountRepository;

    public NewAccountViewModel(@NonNull Application application) {
        super(application);

        this.newAccountRepository = new NewAccountRepository();

    }

    public void init(User user){

        if(!(nameValidation(user.getCompleteName()) && userValidation(user.getUsername()) && (passwordValidation(user.getPassword()) && user.getPassword().length() >= 6))) {

            if (!nameValidation(user.getCompleteName())) {

                getNameValidation().setValue(false);
            }
            if (!userValidation(user.getUsername())) {

                getUserNameValidation().setValue(false);
            }
            if (!(passwordValidation(user.getPassword()) && user.getPassword().length() >= 6)) {

                getPasswordValidation().setValue(false);
            }

        }else {

            //newAccountRepository.setUser(user);
            getLoading().setValue(true);
            System.out.println("ELSEEEEEMODEL");

            newAccountRepository.setUser(user).observeForever(new Observer<ResponseService<User>>() {
                @Override
                public void onChanged(@Nullable ResponseService<User> responseService) {

                    if (responseService.getStatus()) {

                        System.out.println("OKKK VUEW MODEL--------------------------");
                        getStatus().setValue("Ok");
                    }

                    else {
                        errorMessage = responseService.getErrorMessage();
                        getStatus().setValue(errorMessage.getMessage());
                    }

                }
            });
        }

    }

    private static boolean nameValidation(String name){
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private static boolean userValidation(String user){
        Matcher matcher = pattern3.matcher(user);
        return matcher.matches();
    }

    private static boolean passwordValidation(String name){
        Matcher matcher = pattern2.matcher(name);
        return matcher.matches();
    }


    public MutableLiveData<Boolean> getUserNameValidation() {
        return userNameValidation;
    }

    public MutableLiveData<Boolean> getNameValidation() {
        return nameValidation;
    }

    public MutableLiveData<Boolean> getPasswordValidation() {
        return passwordValidation;
    }


}
