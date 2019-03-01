package com.example.android.movieapp.loginScreen;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.movieapp.R;

public class Loading extends ConstraintLayout {

    ProgressBar loadingView;

    public Loading(Context context) {
        super(context);
        init();
    }

    public Loading(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Loading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.loading, this);

         loadingView = (ProgressBar) findViewById(R.id.loadingView);
    }

    public void setLoadingView(View v,Boolean b){

        if(b){
            loadingView.setVisibility(VISIBLE);
        }

        else loadingView.setVisibility(INVISIBLE);
    }

}
