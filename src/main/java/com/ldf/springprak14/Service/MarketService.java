package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Repo.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public Market createMarket(Market market){
        return marketRepository.save(market);
    }

    public List<Market> getAllMarkets(){
        return marketRepository.findAll();
    }

    public void deleteMarket(Long id){
        marketRepository.deleteById(id);
    }
}
