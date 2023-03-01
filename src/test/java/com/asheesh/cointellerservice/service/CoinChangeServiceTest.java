package com.asheesh.cointellerservice.service;

import com.asheesh.cointellerservice.AppProps;
import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.dto.CoinChangeRequest;
import com.asheesh.cointellerservice.dto.Mode;
import com.asheesh.cointellerservice.exception.InvalidInputException;
import com.asheesh.cointellerservice.exception.UnprocessableRequestException;
import com.asheesh.cointellerservice.repository.CoinRequestRepository;
import com.asheesh.cointellerservice.repository.dao.CoinBox;
import com.asheesh.cointellerservice.repository.dao.CoinBoxRepository;
import com.asheesh.cointellerservice.util.CoinCalculator;
import com.asheesh.cointellerservice.util.MinCoinCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoinChangeServiceTest {

    @InjectMocks
    private CoinChangeService coinChangeService;

    @Mock
    private CoinBoxRepository coinBoxRepository;

    @Mock
    private CoinRequestRepository coinRequestRepository;

    @Mock
    private AppProps appProps;

    @Spy
    private CoinCalculator coinCalculator = new MinCoinCalculator();

    private static final List<Integer> availableBills = List.of(1, 2, 5, 10, 20, 50, 100);


    @BeforeEach
    public void setup(){
        when(appProps.getAvailableBills()).thenReturn(availableBills);
    }

    @Test
    public void testGetCoinBagWithSuccess(){
        CoinChangeRequest request = new CoinChangeRequest();
        request.setBill(10);
        request.setMode(Mode.MIN_COINS);

        when(coinBoxRepository.findByRemainingAmountGreaterThan(0)).thenReturn(createMockedCoinBoxList());

        List<CoinBag> coinBags = coinChangeService.getCoinBag(request);
        assertEquals(coinBags.size(), 1);
        assertEquals(coinBags.get(0).getDenomination(), 25);
        assertEquals(coinBags.get(0).getCount(), 40);
    }

    @Test
    public void testGetCoinBagWithInvalidBillDenomination(){
        CoinChangeRequest request = new CoinChangeRequest();
        request.setBill(3);
        request.setMode(Mode.MIN_COINS);

        InvalidInputException ex = assertThrows(InvalidInputException.class, () -> {
            coinChangeService.getCoinBag(request);
        });
        assertEquals(ex.getMessage(), "Please input valid denomination bill. Accepted denominations are "+ availableBills);
    }

    @Test
    public void testGetCoinBagWithNotEnoughCoins(){
        CoinChangeRequest request = new CoinChangeRequest();
        request.setBill(100);
        request.setMode(Mode.MIN_COINS);

        UnprocessableRequestException ex = assertThrows(UnprocessableRequestException.class, () -> {
            coinChangeService.getCoinBag(request);
        });
        assertEquals(ex.getMessage(), "Not enough money available in the machine.");
    }

    private List<CoinBox> createMockedCoinBoxList(){
        return Arrays.asList(new CoinBox(){{
            setCoinId(1);
            setRemainingAmount(100);
        }},new CoinBox(){{
            setCoinId(5);
            setRemainingAmount(100);
        }},new CoinBox(){{
            setCoinId(10);
            setRemainingAmount(100);
        }},new CoinBox(){{
            setCoinId(25);
            setRemainingAmount(100);
        }});
    }

}
