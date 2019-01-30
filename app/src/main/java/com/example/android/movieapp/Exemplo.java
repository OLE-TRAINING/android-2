package com.example.android.movieapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

public class Exemplo extends View {

    public Exemplo(Context context) {
        super(context);
        init(null);;
    }

    public Exemplo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Exemplo (Context context, AttributeSet attrs,int defStyleAttr ){
        super(context,attrs,defStyleAttr);
        init(attrs);
    }

    public Exemplo (Context context, AttributeSet attrs,int defStyleAttr,  int defStylerRes ){
        super(context,attrs,defStyleAttr,defStylerRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }
}
