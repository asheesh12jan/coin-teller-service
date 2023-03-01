package com.asheesh.cointellerservice.dto;

import java.util.Date;

public class CoinTellerError {
    private Integer statusCode;
    private String errorMessage;
    private Date timeStamp;
    private Boolean retryable;
    private Long retryAfterSec;

    public CoinTellerError(Integer statusCode, String errorMessage, Date timeStamp, Boolean retryable, Long retryAfterSec) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.timeStamp = timeStamp;
        this.retryable = retryable;
        this.retryAfterSec = retryAfterSec;
    }

    public CoinTellerError(Integer statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.timeStamp = new Date();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Long getRetryAfterSec() {
        return retryAfterSec;
    }

    public void setRetryAfterSec(Long retryAfterSec) {
        this.retryAfterSec = retryAfterSec;
    }
}
