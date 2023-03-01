package com.asheesh.cointellerservice.controller;

import com.asheesh.cointellerservice.dto.CoinBag;
import com.asheesh.cointellerservice.dto.CoinChangeRequest;
import com.asheesh.cointellerservice.dto.CoinChangeResponse;
import com.asheesh.cointellerservice.service.CoinChangeService;
import com.asheesh.cointellerservice.util.AppUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CoinChangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        List<CoinBag> coinBags = List.of(new CoinBag(25, 40));
        CoinChangeResponse response = new CoinChangeResponse(coinBags);
        String responseJson = AppUtil.toJson(response);
        this.mockMvc.perform(post("/coin-changer/change-bill")
                .contentType(MediaType.APPLICATION_JSON).content("{\"bill\": 10,\"mode\": \"MIN_COINS\"}")).andExpect(status().isOk())
                .andExpect(content().string(responseJson));
    }
}
