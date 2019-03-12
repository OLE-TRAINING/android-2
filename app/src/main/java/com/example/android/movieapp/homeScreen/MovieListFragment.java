package com.example.android.movieapp.homeScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movieapp.R;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {


    private String s;

    private final LinkedList<MovieItem> movieList = new LinkedList<>();


    private RecyclerView mRecyclerView;
    private ListMovieAdapter mAdapter;

    MovieItem a = new MovieItem();

    MovieItem b = new MovieItem();

    public static MovieListFragment newInstance() {
        Bundle bundle = new Bundle();

        MovieListFragment fragment = new MovieListFragment();
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
        View view = inflater.inflate(R.layout.type_home_fragment, container, false);
        readBundle(getArguments());

        movieList.add(a);
        movieList.add(b);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mAdapter = new ListMovieAdapter(view.getContext(), movieList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
