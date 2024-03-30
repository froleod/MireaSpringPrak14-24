package com.ldf.springprak14.Service;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Repo.MarketRepository;
import com.ldf.springprak14.Repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MarketRepository marketRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Market getMarketByProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(Product::getMarket).orElse(null);
    }

    public void addProductToMarket(Long productId, Long marketId) {
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

    // Метод для фильтрации продуктов по имени и цене
    public List<Product> filterProducts(String name, Integer price) {
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
