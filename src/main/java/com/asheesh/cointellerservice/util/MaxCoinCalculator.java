package com.asheesh.cointellerservice.util;

import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.repository.dao.CoinBox;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MAX_COINS")
public class MaxCoinCalculator implements CoinCalculator {

    @Override
    public List<CoinBag> findCoinBag(List<CoinBox> coinBoxes, Integer billAmount) {
        //TODO not yet implemented
        return null;
    }
}
