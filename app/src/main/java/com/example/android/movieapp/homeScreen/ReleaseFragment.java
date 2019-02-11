package com.example.android.movieapp.homeScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movieapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReleaseFragment extends Fragment {


    private String s;

    private TextView textView;

    public static ReleaseFragment newInstance(String s) {
        Bundle bundle = new Bundle();
        bundle.putString("s", s);


        ReleaseFragment fragment = new ReleaseFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            s = bundle.getString("s");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_release, container, false);
        textView = (TextView) view.findViewById(R.id.textRelese);


        readBundle(getArguments());

        textView.setText(s);


        return view;
    }

}
