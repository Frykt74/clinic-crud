package com.clinik.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    @ManyToOne
    @JoinColumn
    private Doctor doctor;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
