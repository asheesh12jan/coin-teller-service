package com.asheesh.cointellerservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ValueConverter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "spring.com.asheesh.cointellerservice")
@Configuration
public class AppProps {
    private String h2UserName;
    private String h2Password;
    private String h2Url;
    private String postgresUserName;
    private String postgresPassword;
    private String postgresUrl;
    private List<Integer> availableBills;

    private Map<String, Integer> availableCoins;

    public String getH2UserName() {
        return h2UserName;
    }

    public void setH2UserName(String h2UserName) {
        this.h2UserName = h2UserName;
    }

    public String getH2Password() {
        return h2Password;
    }

    public void setH2Password(String h2Password) {
        this.h2Password = h2Password;
    }

    public String getH2Url() {
        return h2Url;
    }

    public void setH2Url(String h2Url) {
        this.h2Url = h2Url;
    }

    public String getPostgresUserName() {
        return postgresUserName;
    }

    public void setPostgresUserName(String postgresUserName) {
        this.postgresUserName = postgresUserName;
    }

    public String getPostgresPassword() {
        return postgresPassword;
    }

    public void setPostgresPassword(String postgresPassword) {
        this.postgresPassword = postgresPassword;
    }

    public String getPostgresUrl() {
        return postgresUrl;
    }

    public void setPostgresUrl(String postgresUrl) {
        this.postgresUrl = postgresUrl;
    }

    public List<Integer> getAvailableBills() {
        return availableBills;
    }

    public void setAvailableBills(List<Integer> availableBills) {
        this.availableBills = availableBills;
    }

    public Map<String, Integer> getAvailableCoins() {
        return availableCoins;
    }

    public void setAvailableCoins(Map<String, Integer> availableCoins) {
        this.availableCoins = availableCoins;
    }
}
