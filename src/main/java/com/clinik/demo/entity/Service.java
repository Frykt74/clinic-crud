package com.clinik.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Doctor doctor;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
}
