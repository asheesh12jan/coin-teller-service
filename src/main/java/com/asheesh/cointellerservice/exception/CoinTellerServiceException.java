package com.asheesh.cointellerservice.exception;

import com.asheesh.cointellerservice.dto.CoinTellerError;

import java.util.Date;

public abstract class CoinTellerServiceException extends RuntimeException {

    public CoinTellerServiceException(String msg){
        super(msg);
    }

    public abstract int getHttpStatusCode();
    public abstract Boolean isRetryable();
    public Long retryAfter(){
        return null;
    }
    public CoinTellerError createErrorResponse(){
        CoinTellerError error = new CoinTellerError(this.getHttpStatusCode()
                , this.getMessage(), new Date(), this.isRetryable(), this.retryAfter());
        return error;
    }
}
