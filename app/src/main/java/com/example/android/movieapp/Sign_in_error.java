package com.example.android.movieapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sign_in_error extends ConstraintLayout {

    TextView textView;

    public Sign_in_error(Context context) {
        super(context);
        init();
    }

    public Sign_in_error(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Sign_in_error(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.sign_in_error, this);

        textView = (TextView) findViewById(R.id.textSignInError);

    }

    public void setVisible(View v){

        textView.setVisibility(v.VISIBLE);
    }
}
