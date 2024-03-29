package com.ldf.springprak14.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="market_id")
    @JsonIgnore // для исключения циклической ссылки
    private Market market;

}
