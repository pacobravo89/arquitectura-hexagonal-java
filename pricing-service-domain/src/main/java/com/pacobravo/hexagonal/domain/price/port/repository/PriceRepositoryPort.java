package com.pacobravo.hexagonal.domain.price.port.repository;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findPrices(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}
