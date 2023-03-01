package com.asheesh.cointellerservice.util;

import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.repository.dao.CoinBox;

import java.util.*;

public interface CoinCalculator {
    List<CoinBag> findCoinBag(List<CoinBox> coinBoxes, Integer billAmount);
}
