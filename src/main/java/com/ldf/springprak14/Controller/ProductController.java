package com.ldf.springprak14.Controller;

import com.ldf.springprak14.Entity.Market;
import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{productId}")
    public Optional<Product> getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

    @DeleteMapping("/delete/{index}")
    public void deleteProduct(@PathVariable Long index){
        productService.deleteProduct(index);
    }

    @PostMapping("/{productId}/addToMarket/{marketId}")
    public void addProductToMarket(@PathVariable Long productId, @PathVariable Long marketId) {
        productService.addProductToMarket(productId, marketId);
    }
    @GetMapping("/{productId}/market")
    public Market getMarketByProduct(@PathVariable Long productId) {
        return productService.getMarketByProduct(productId);
    }

    @GetMapping("/filter")
    public List filterProducts(@RequestParam(required = false) String name, @RequestParam(required = false) Integer price){
        return productService.filterProducts(name, price);
    }
}
