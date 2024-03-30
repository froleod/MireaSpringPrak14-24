package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarketService {
    private final MarketRepository marketRepository;
    private final ProductRepository productRepository;

    public Market createMarket(Market market){
        log.info("Market:" + market + "was created");
        return marketRepository.save(market);
    }

    public List<Market> findAll(){
        log.info("Find all markets");
        return marketRepository.findAll();
    }

    public Market getMarketById(Long id){
        log.info("Find market with id: " + id);
        return marketRepository.findById(id).orElse(null);
    }

    public List<Market> findMarketByName(String name){
        log.info("Find market with name: " + name);
        return marketRepository.findMarketByName(name);
    }

    public List<Market> findMarketByAddress(String address){
        log.info("Find market with address: " + address);
        return marketRepository.findMarketByAddress(address);
    }

    public void deleteMarket(Long id){
        log.info("Market with id: " + id + " was deleted");
        marketRepository.deleteById(id);
    }

    public List<Product> getProductsByMarket(Long marketId) {
        log.info("Find product by market with id: " + marketId);
        Market market = marketRepository.findById(marketId).orElse(null);
        if (market != null) {
            return market.getProducts();
        }
        return null;
    }

    @Transactional
    public void addProductToMarket(Long marketId, Long productId) {
        log.info("Add product with id: " + productId + " to market with id: " + marketId);
        Market market = marketRepository.findById(marketId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (market != null && product != null) {
            product.setMarket(market);
            market.getProducts().add(product);
            marketRepository.save(market);
        }
    }
}
