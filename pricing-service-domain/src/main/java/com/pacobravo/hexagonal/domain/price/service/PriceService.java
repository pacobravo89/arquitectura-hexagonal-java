package com.pacobravo.hexagonal.domain.price.service;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price getPrices(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<Price> pricesList = priceRepositoryPort.findPrices(productId, brandId, applicationDate);
        return pricesList.stream().max(Comparator.comparing(Price::getPriority)).orElse(null);
    }
}
