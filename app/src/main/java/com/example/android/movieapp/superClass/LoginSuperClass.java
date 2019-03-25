package com.example.android.movieapp.superClass;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class LoginSuperClass extends AndroidViewModel {

   private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LoginSuperClass(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
