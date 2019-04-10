package com.example.android.movieapp.homeScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.User;
import com.squareup.picasso.Picasso;

public class FavoriteFragment extends Fragment {

    private TextView textView;
    private String email;

    public static HomeFragment newInstance(String email){

        HomeFragment homeFragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putString("email",email);
        homeFragment.setArguments(args);

        return homeFragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            email = (String) bundle.getString("email");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        ImageView imageMovie=  v.findViewById(R.id.favorite_imageView);
        textView =(TextView) v.findViewById(R.id.textViewFF);

        //Picasso.load("https://ole.dev.gateway.zup.me/client-training/v1/movies/hVgLHgnsO46oSHJy5I4ekhqtoYv/image/w342?gw-app-key=593c3280aedd01364c73000d3ac06d76").into(imageMovie);

        Picasso.with(getActivity().getApplicationContext()).load("https://ole.dev.gateway.zup.me/client-training/v1/movies/hVgLHgnsO46oSHJy5I4ekhqtoYv/image/w342?gw-app-key=593c3280aedd01364c73000d3ac06d76").into(imageMovie);

        textView.setText(email);
        return v;
    }
}
