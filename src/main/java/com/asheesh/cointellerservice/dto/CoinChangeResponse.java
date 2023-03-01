package com.asheesh.cointellerservice.dto;

import java.util.List;

public class CoinChangeResponse {
    private List<CoinBag> coinBags;

    public CoinChangeResponse(List<CoinBag> coinBags) {
        this.coinBags = coinBags;
    }

    public List<CoinBag> getCoinBags() {
        return coinBags;
    }

    public void setCoinBags(List<CoinBag> coinBags) {
        this.coinBags = coinBags;
    }

    @Override
    public String toString() {
        return "CoinChangeResponse{" +
                "coinBags=" + coinBags +
                '}';
    }
}
