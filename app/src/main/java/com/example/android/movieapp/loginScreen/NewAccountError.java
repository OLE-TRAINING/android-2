package com.example.android.movieapp.loginScreen;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android.movieapp.R;

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


        editText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //here is your code
                textView.setVisibility(INVISIBLE);
                imageView.setVisibility(INVISIBLE);
                editText.setBackgroundResource(R.drawable.edittext_bottom_border);

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void setValue(String value, String hint) {
        textView.setText(value);
        editText.setHint(hint);

    }

    public void setInvisibleError(View v) {

    }

    public String getEditText() {
        return editText.getText().toString();
    }


    public void setVisibleError(View v,Boolean b) {

        if(!b) {
            textView.setVisibility(v.VISIBLE);
            imageView.setVisibility(v.VISIBLE);
            editText.setBackgroundResource(R.drawable.errorborder);
        }
    }


    public void setText() {
        editText.setText("exemplo@mail.com");
    }

    public void setLength(int maxLength) {
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(fArray);
    }

    public void setPasswordType() {

        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
       editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    public int getLength() {

        return editText.length();
    }
}
