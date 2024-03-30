package com.ldf.springprak14.Repo;

import com.ldf.springprak14.Entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findMarketByName(String name);
    List<Market> findMarketByAddress(String location);

}
