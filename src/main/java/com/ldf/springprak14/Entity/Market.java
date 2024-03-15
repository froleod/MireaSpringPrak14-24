package com.ldf.springprak14.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="markets")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;


    public Market(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Market() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
