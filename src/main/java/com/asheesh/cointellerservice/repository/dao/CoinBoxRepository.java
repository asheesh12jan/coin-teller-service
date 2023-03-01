package com.asheesh.cointellerservice.repository.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinBoxRepository extends JpaRepository<CoinBox, Integer> {

    List<CoinBox> findByRemainingAmountGreaterThan(Integer remainingAmount);

}
