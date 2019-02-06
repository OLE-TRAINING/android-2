package com.example.android.movieapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class SignInError extends ConstraintLayout {

    TextView textView;

    public SignInError(Context context) {
        super(context);
        init();
    }

    public SignInError(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignInError(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.sign_in_error, this);

        textView = (TextView) findViewById(R.id.text_sign_in_error);

    }

    public void setVisible(View v) {

        textView.setVisibility(v.VISIBLE);
    }
}
