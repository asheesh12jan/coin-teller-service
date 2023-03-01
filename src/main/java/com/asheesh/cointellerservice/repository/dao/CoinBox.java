package com.asheesh.cointellerservice.repository.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coin_box")
public class CoinBox {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coin_id")
    private Integer coinId;
    @Column(name = "max_amount")
    private Integer maxAmount;
    @Column(name = "remaining_amount")
    private Integer remainingAmount;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "modify_date")
    private Date modifyDate;

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
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
