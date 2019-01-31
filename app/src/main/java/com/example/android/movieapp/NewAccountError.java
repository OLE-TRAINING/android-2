package com.example.android.movieapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewAccountError extends ConstraintLayout {

    private EditText editText;
    private TextView textView;
    private ImageButton imageView;

    public NewAccountError(Context context) {
        super(context);
        init();
    }

    public NewAccountError(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewAccountError(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }





    private void init() {
        inflate(getContext(), R.layout.new_account_error, this);

        textView = (TextView) findViewById(R.id.text_new_account_error);
        editText = (EditText) findViewById(R.id.edit_new_account_error);
        imageView = (ImageButton) findViewById(R.id.image_new_account_error);
    }

    public void setValue(String value,String hint) {
     textView.setText(value);
     editText.setHint(hint);

    }

    public void setVisible(View v){
        textView.setVisibility(v.VISIBLE);
        imageView.setVisibility(v.VISIBLE);
    }
}
