package com.pacobravo.hexagonal.application.price.handler;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;

import java.time.LocalDateTime;

public interface PriceQueryHandler {
    PriceQueryResponse getHighestPriorityPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
