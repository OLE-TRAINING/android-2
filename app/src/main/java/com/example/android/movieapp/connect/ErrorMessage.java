package com.example.android.movieapp.connect;

public class ErrorMessage {

    private String message;
    private String key;
    private int code;

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
