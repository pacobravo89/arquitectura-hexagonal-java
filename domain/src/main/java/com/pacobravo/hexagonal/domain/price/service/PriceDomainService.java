package com.pacobravo.hexagonal.domain.price.service;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.model.exception.ResourceNotFoundException;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class PriceDomainService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceDomainService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Price getHighestPriorityPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        List<Price> pricesList = priceRepositoryPort.findApplicablePrices(productId, brandId, applicationDate);
        return pricesList.stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
