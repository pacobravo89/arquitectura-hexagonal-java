package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Data
public class PriceIn {

    private Long productId;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
