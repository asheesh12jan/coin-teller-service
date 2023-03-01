package com.asheesh.cointellerservice.controller;

import com.asheesh.cointellerservice.AppProps;
import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.dto.CoinChangeRequest;
import com.asheesh.cointellerservice.dto.CoinChangeResponse;
import com.asheesh.cointellerservice.service.CoinChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/coin-changer")
public class CoinChangeController {

    @Autowired
    private CoinChangeService coinChangeService;


    @PostMapping("/change-bill")
    public CoinChangeResponse changeBill(@Valid @RequestBody CoinChangeRequest request){
        List<CoinBag> coinBags = coinChangeService.getCoinBag(request);
        return new CoinChangeResponse(coinBags);
    }
}
