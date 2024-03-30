package com.ldf.springprak14.Controller;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Service.MarketService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping("/add")
    public Market createMarket(@RequestBody Market market) {
        return marketService.createMarket(market);
    }

    @DeleteMapping("/delete/{index}")
    public void deleteMarket(@PathVariable Long index) {
        marketService.deleteMarket(index);
    }

    @GetMapping("/all")
    public List<Market> getAllMarkets() {
        return marketService.findAll();
    }

    @GetMapping("/{marketId}/products")
    public List<Product> getProductsByMarket(@PathVariable Long marketId) {
        return marketService.getProductsByMarket(marketId);
    }

    @PostMapping("/{marketId}/addProduct/{productId}")
    public void addProductToMarket(@PathVariable Long marketId, @PathVariable Long productId) {
        marketService.addProductToMarket(marketId, productId);
    }
}
