package com.pacobravo.hexagonal.domain.price.service;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.List;

public class PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price getPrices(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Price> pricesList = priceRepositoryPort.findPrices(productId,brandId,startDate,endDate);
        return pricesList.stream().findFirst().orElse(null);
    }
}
