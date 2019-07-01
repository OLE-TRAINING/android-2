package com.example.android.movieapp.model;

import java.io.Serializable;

public class Genres implements Serializable {

    private final int id;

    private final String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
