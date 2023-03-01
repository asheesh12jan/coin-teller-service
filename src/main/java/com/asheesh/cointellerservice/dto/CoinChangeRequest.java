package com.asheesh.cointellerservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CoinChangeRequest {
    @NotNull(message = "Bill cannot be null")
    @Min(value = 1, message = "Minimum $1 bill accepted.")
    @Max(value = 100, message = "Maximum $100 bill accepted.")
    private Integer bill;

    @Valid
    private Mode mode;

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "CoinChangeRequest{" +
                "bill=" + bill +
                ", mode=" + mode +
                '}';
    }
}
