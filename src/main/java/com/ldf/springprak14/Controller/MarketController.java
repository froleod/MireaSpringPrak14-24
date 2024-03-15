package com.ldf.springprak14.Controller;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Service.MarketService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {
//    private List<Market> markets = new ArrayList<>();

    private MarketService marketService;

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
        return marketService.getAllMarkets();
    }

}
