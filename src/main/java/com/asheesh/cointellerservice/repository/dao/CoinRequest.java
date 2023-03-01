package com.asheesh.cointellerservice.repository.dao;

import com.asheesh.cointellerservice.dto.Mode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coin_request")
public class CoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer bill;

    @Enumerated(EnumType.STRING)
    private Mode mode;
    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "returned_coins")
    private String returnedCoins;
    @Column(name = "remaining_coins")
    private String remainingCoins;

    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "modify_date")
    private Date modifyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getReturnedCoins() {
        return returnedCoins;
    }

    public void setReturnedCoins(String returnedCoins) {
        this.returnedCoins = returnedCoins;
    }

    public String getRemainingCoins() {
        return remainingCoins;
    }

    public void setRemainingCoins(String remainingCoins) {
        this.remainingCoins = remainingCoins;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
