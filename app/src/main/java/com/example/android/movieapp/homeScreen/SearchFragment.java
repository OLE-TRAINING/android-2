package com.example.android.movieapp.homeScreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.ViewModelHome.HomeFragmentViewModel;
import com.example.android.movieapp.ViewModelHome.SearchFragmentViewModel;
import com.example.android.movieapp.model.ListMovie;

import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment implements OnOpenDetailMovie {

    private SearchFragmentViewModel searchFragmentViewModel;
    private TextView textView;
    private EditText editText;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView mRecyclerView;
    private ListMovieAdapter mAdapter;
    private Timer timer;
    public static final String EXTRA_MESSAGE_OBJECT =
            "ID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        editText = v.findViewById(R.id.edit_search);

        searchFragmentViewModel =  ViewModelProviders.of(this).get(SearchFragmentViewModel.class);


        editText.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //here is your code
                if (timer != null) {
                    timer.cancel();
                }


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub


            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        searchFragmentViewModel.init(editText.getText().toString(),"1");
                    }
                }, 750);
            }
        });


        mAdapter = new ListMovieAdapter(v.getContext());

        mRecyclerView = v.findViewById(R.id.recyclerview_search);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchFragmentViewModel.getListMovie().observe(this, observerMovieList);
        mAdapter.setOpenDetailMovie(this);
        mRecyclerView.setAdapter(mAdapter);


        return v;




    }

    Observer<ListMovie> observerMovieList= new Observer<ListMovie>() {
        @Override
        public void onChanged(@Nullable ListMovie movieList) {

            mAdapter.setMovieList(movieList);

        }
    };

    @Override
    public void openMovieDetail(String id) {

        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE_OBJECT, id);

        startActivity(intent);
    }
}
