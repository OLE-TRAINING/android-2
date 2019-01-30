package com.example.android.movieapp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class New_Account_Error extends ConstraintLayout {

    private EditText editText;
    private TextView textView;
    private ImageButton imageView;

    public New_Account_Error(Context context) {
        super(context);
        init();
    }

    public New_Account_Error(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public New_Account_Error(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }





    private void init() {
        inflate(getContext(), R.layout.new_account_error, this);

        textView = (TextView) findViewById(R.id.textNewAccountError);
        editText = (EditText) findViewById(R.id.editNewAccountError);
        imageView = (ImageButton) findViewById(R.id.imageNewAccountError);
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
