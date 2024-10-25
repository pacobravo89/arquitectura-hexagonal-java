package com.pacobravo.hexagonal.application.price.handler;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceResponse;

import java.time.LocalDateTime;

public interface PriceHandler {
    PriceResponse getPrices(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}
