package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;

    public Product createProduct(Product product) {
        log.info("Product was created");
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        log.info("Find all products");
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        log.info("Find product by id: " + id);
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        log.info("Delete product by id: " + id);
        productRepository.deleteById(id);
    }

    public Market getMarketByProduct(Long productId) {
        log.info("Find market by product with id:" + productId);
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(Product::getMarket).orElse(null);
    }

    public void addProductToMarket(Long productId, Long marketId) {
        log.info("Add product with id: " + productId + " to market with id: " + marketId);
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<Market> marketOptional = marketRepository.findById(marketId);
        if (productOptional.isPresent() && marketOptional.isPresent()) {
            Product product = productOptional.get();
            Market market = marketOptional.get();
            product.setMarket(market);
            market.getProducts().add(product);
            productRepository.save(product);
            marketRepository.save(market);
        }
    }

    public List<Product> filterProducts(String name, Integer price) {
        log.info("Filter product by name/price");
        if (name != null && price != null) {
            return productRepository.findByNameAndPrice(name, price);
        } else if (name != null) {
            return productRepository.findByName(name);
        } else if (price != null) {
            return productRepository.findByPrice(price);
        } else {
            return productRepository.findAll();
        }
    }
}
