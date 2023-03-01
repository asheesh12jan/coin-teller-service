package com.asheesh.cointellerservice.service;

import com.asheesh.cointellerservice.AppProps;
import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.dto.CoinChangeRequest;
import com.asheesh.cointellerservice.exception.InvalidInputException;
import com.asheesh.cointellerservice.exception.UnprocessableRequestException;
import com.asheesh.cointellerservice.repository.CoinRequestRepository;
import com.asheesh.cointellerservice.repository.dao.CoinBox;
import com.asheesh.cointellerservice.repository.dao.CoinBoxRepository;
import com.asheesh.cointellerservice.repository.dao.CoinRequest;
import com.asheesh.cointellerservice.util.AppUtil;
import com.asheesh.cointellerservice.util.CoinCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoinChangeService {

    @Autowired
    private AppProps appProps;

    @Autowired
    private CoinRequestRepository coinRequestRepository;

    @Autowired
    private CoinBoxRepository coinBoxRepository;

    @Autowired
    @Qualifier("MIN_COINS")
    private CoinCalculator coinCalculator;


    /**
     * Load the coinBox from property file to database
     */
    @PostConstruct
    private void loadCoinBox(){
        //Get coinBoxes from database
        List<Integer> coinBoxes = coinBoxRepository.findAll().stream().map(CoinBox::getCoinId).collect(Collectors.toList());
        Map<String, Integer> availableCoins = appProps.getAvailableCoins();
        List<CoinBox> newCoins = new ArrayList<>();
        availableCoins.keySet().stream().filter(k -> !coinBoxes.contains(Integer.valueOf(k))).forEach(k -> {
            CoinBox coinBox = new CoinBox();
            coinBox.setCoinId(Integer.valueOf(k));
            coinBox.setRemainingAmount(availableCoins.get(k));
            coinBox.setMaxAmount(availableCoins.get(k));
            coinBox.setCreateDate(new Date());
            coinBox.setModifyDate(new Date());

            newCoins.add(coinBox);
        });
        //Save new coinBox
        coinBoxRepository.saveAll(newCoins);
    }

    public List<CoinBag> getCoinBag(CoinChangeRequest coinChangeRequest){
        List<Integer> availableBills = appProps.getAvailableBills();
        if(!availableBills.contains(coinChangeRequest.getBill())){
            throw new InvalidInputException("Please input valid denomination bill. Accepted denominations are "+ availableBills);
        }

        List<CoinBox> coinBoxes = coinBoxRepository.findByRemainingAmountGreaterThan(0);
        if(coinChangeRequest.getBill().doubleValue() > getTotalMoneyInCoinBox(coinBoxes)){
            throw new UnprocessableRequestException("Not enough money available in the machine.");
        }

        //Get the coin bags with minimum number of coins, and update the coinBoxes
        List<CoinBag> listOfCoinBag = coinCalculator.findCoinBag(coinBoxes, coinChangeRequest.getBill());

        //Update DB
        createRequestAndUpdateCoinStats(coinChangeRequest, listOfCoinBag, coinBoxes);

        return listOfCoinBag;
    }

    @Transactional
    public void createRequestAndUpdateCoinStats(CoinChangeRequest coinChangeRequest, List<CoinBag> returnedCoins, List<CoinBox> updatedCoinBox){
        CoinRequest coinRequest = createCoinChangeRequest(coinChangeRequest, returnedCoins, updatedCoinBox);
        coinRequestRepository.save(coinRequest);
        coinBoxRepository.saveAll(updatedCoinBox);
    }

    private double getTotalMoneyInCoinBox(List<CoinBox> coinBoxes){
        return coinBoxes.stream().mapToDouble(c -> c.getRemainingAmount()*c.getCoinId()/100).sum();
    }

    private CoinRequest createCoinChangeRequest(CoinChangeRequest coinChangeRequest, List<CoinBag> returnedCoins, List<CoinBox> updatedCoinBox){
        List<CoinBag> remainingCoins = updatedCoinBox.stream().map(cBox -> new CoinBag(cBox.getCoinId(), cBox.getRemainingAmount())).collect(Collectors.toList());
        CoinRequest coinRequest = new CoinRequest();
        coinRequest.setBill(coinChangeRequest.getBill());
        coinRequest.setMode(coinChangeRequest.getMode());
        coinRequest.setReturnedCoins(AppUtil.toJson(returnedCoins));
        coinRequest.setRemainingCoins(AppUtil.toJson(remainingCoins));
        coinRequest.setCreateDate(new Date());
        coinRequest.setModifyDate(new Date());

        return coinRequest;
    }

}
