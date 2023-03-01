package com.asheesh.cointellerservice.dto;

/**
 * Represents a coin, and it's count in the API response
 */
public class CoinBag {
    private Integer denomination;
    private Integer count;

    public CoinBag(Integer denomination, Integer count) {
        this.denomination = denomination;
        this.count = count;
    }

    public Integer getDenomination() {
        return denomination;
    }

    public void setDenomination(Integer denomination) {
        this.denomination = denomination;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CoinBag{" +
                "denomination='" + denomination + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
