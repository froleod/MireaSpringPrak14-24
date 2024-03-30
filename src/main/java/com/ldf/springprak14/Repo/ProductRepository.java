package com.ldf.springprak14.Repo;

import com.ldf.springprak14.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameAndPrice(String name, Integer price);
    List<Product> findByPrice(Integer price);
    List<Product> findByName(String name);
}
