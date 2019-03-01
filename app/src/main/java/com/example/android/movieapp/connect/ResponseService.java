package com.example.android.movieapp.connect;

public class ResponseService <T> {

    private T t;
    private Boolean status;

    public void set(T t) { this.t = t; }
    public T get() { return t; }

    private ErrorMessage errorMessage;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
