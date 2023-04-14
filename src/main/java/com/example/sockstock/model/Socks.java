package com.example.sockstock.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private Long cottonPart;

    @Column(name = "quantity")
    private Long quantity;

}
