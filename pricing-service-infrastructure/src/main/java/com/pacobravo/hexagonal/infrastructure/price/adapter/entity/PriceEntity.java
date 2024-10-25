package com.pacobravo.hexagonal.infrastructure.price.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRICE_LIST")
    private int priceList;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "PRIORITY")
    private int priority;
}
