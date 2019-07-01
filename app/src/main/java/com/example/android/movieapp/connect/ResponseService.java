package com.example.android.movieapp.connect;

import java.io.Serializable;

public class ResponseService <ServiceBody> {

    private ServiceBody serviceBody;
    private Boolean status;

    public void set(ServiceBody serviceBody) { this.serviceBody = serviceBody; }
    public ServiceBody get() { return serviceBody; }

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
