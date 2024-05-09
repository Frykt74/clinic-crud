package com.clinic.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service")
public class Favor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service")
    private Long idService;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Doctor doctor;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
}
