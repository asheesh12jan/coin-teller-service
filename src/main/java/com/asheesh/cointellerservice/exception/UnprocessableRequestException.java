package com.asheesh.cointellerservice.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableRequestException extends CoinTellerServiceException {

    public UnprocessableRequestException(String msg){
        super(msg);
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY.value();
    }

    @Override
    public Boolean isRetryable() {
        return true;
    }

    @Override
    public Long retryAfter() {
        return Long.valueOf(60);
    }
}
