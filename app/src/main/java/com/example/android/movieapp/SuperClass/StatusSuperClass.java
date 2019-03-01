package com.example.android.movieapp.SuperClass;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;





public class StatusSuperClass extends LoginSuperClass {

    private MutableLiveData <String> status = new MutableLiveData<>();

    public StatusSuperClass(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getStatus() {
        return status;
    }
}
