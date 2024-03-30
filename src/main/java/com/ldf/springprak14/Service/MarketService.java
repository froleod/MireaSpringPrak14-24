package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;

    public Market createMarket(Market market){
        return marketRepository.save(market);
    }

    public List<Market> findAll(){
        return marketRepository.findAll();
    }

    public Market getMarketById(Long id){
        return marketRepository.findById(id).orElse(null);
    }

    public List<Market> findMarketByName(String name){
        return marketRepository.findMarketByName(name);
    }

    public List<Market> findMarketByAddress(String address){
        return marketRepository.findMarketByAddress(address);
    }

    public void deleteMarket(Long id){
        marketRepository.deleteById(id);
    }

    public List<Product> getProductsByMarket(Long marketId) {
        Market market = marketRepository.findById(marketId).orElse(null);
        if (market != null) {
            return market.getProducts();
        }
        return null;
    }

    @Transactional
    public void addProductToMarket(Long marketId, Long productId) {
        Market market = marketRepository.findById(marketId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (market != null && product != null) {
            product.setMarket(market);
            market.getProducts().add(product);
            marketRepository.save(market);
        }
    }
}
