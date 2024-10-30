package com.pacobravo.hexagonal.domain.price.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceQueryResponse {

    private Long brandId;
    private Long productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String currency;
    private Double price;
    private int priority;
}
