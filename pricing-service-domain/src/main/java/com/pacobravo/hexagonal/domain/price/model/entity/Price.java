package com.pacobravo.hexagonal.domain.price.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Price {

    private Long id;
    private Long brandId;
    private Long productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String currency;
    private Double price;
    private int priority;
}
