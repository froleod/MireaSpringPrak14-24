package com.ldf.springprak14;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import com.ldf.springprak14.Service.EmailService;
import com.ldf.springprak14.Service.MarketService;
import com.ldf.springprak14.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @Mock
    private MarketRepository marketRepository;


    @InjectMocks
    private MarketService marketService;


    @Test
    void testCreateMarket() {
        Market market = new Market();
        when(marketRepository.save(market)).thenReturn(market);

        Market savedMarket = marketService.createMarket(market);

        assertEquals(market, savedMarket);
    }

    @Test
    void testFindAll(){
        List<Market> markets = new ArrayList<>();
        when(marketRepository.findAll()).thenReturn(markets);

        List<Market> markets1 = marketService.findAll();

        assertEquals(markets, markets1);
    }
}
