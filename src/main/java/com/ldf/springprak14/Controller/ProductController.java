package com.ldf.springprak14.Controller;

import com.ldf.springprak14.Entity.Product;
import com.ldf.springprak14.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    private List<Product> products = new ArrayList<>();

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

    @DeleteMapping("/delete/{index}")
    public void deleteProduct(@PathVariable Long index){
        productService.deleteProduct(index);
    }
}
