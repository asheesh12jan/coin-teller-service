package com.asheesh.cointellerservice.repository;

import com.asheesh.cointellerservice.repository.dao.CoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRequestRepository extends JpaRepository<CoinRequest, Long> {

}
