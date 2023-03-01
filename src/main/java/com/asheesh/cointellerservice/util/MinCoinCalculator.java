package com.asheesh.cointellerservice.util;

import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.repository.dao.CoinBox;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component("MIN_COINS")
public class MinCoinCalculator implements CoinCalculator {
    public List<CoinBag> findCoinBag(List<CoinBox> coinBoxes, Integer billAmount){
        //Sort in descending order of coin denomination
        coinBoxes.sort(Comparator.comparingInt(CoinBox::getCoinId).reversed());

        //Create a new CoinBox list to avoid modification in original list
        List<CoinBox> newCoinBoxList = coinBoxes.stream().map(c -> {
            CoinBox cb = new CoinBox();
            cb.setCoinId(c.getCoinId());
            cb.setRemainingAmount(c.getRemainingAmount());
            return cb;
        }).collect(Collectors.toList());
        List<Integer> matchingCoinList = findMinCoins(newCoinBoxList, billAmount*100, new HashMap<>());
        Map<Integer, Long> coinCounts = matchingCoinList.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        List<CoinBag> coinBags = new ArrayList<>();
        coinCounts.forEach((k,v) -> {
            CoinBag coinBag = new CoinBag(k, v.intValue());
            coinBags.add(coinBag);
            coinBoxes.stream().filter(cb -> cb.getCoinId().equals(k)).forEach(c -> c.setRemainingAmount(c.getRemainingAmount() - v.intValue()));
        });
        return coinBags;
    }

    private List<Integer> findMinCoins(List<CoinBox> coinBoxes, Integer billAmount, Map<Integer, List<Integer>> memo){
        if(memo.containsKey(billAmount)){
            return memo.get(billAmount);
        }
        if(billAmount == 0){
            return List.of();
        }
        if(billAmount < 0){
            return null;
        }
        List<Integer> minLength = null;
        for(CoinBox coinBox : coinBoxes) {
            int remainder = billAmount - coinBox.getCoinId();
            coinBox.setRemainingAmount(coinBox.getRemainingAmount()-1);
            List<CoinBox> newCoinBox = new ArrayList<>(coinBoxes);
            newCoinBox.removeIf(c -> c.getRemainingAmount() == 0);
            List<Integer> remainderResult = findMinCoins(newCoinBox, remainder, memo);
            if(remainderResult != null){
                List<Integer> newResult = new ArrayList<>(remainderResult);
                newResult.add(coinBox.getCoinId());
                if(minLength == null || newResult.size() < minLength.size()){
                    minLength = newResult;
                }
            }
        }
        memo.put(billAmount, minLength);
        return minLength;
    }
}
