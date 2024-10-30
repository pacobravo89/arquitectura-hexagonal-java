package com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.repository;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.PriceJpaRepository;
import com.pacobravo.hexagonal.infrastructure.price.adapter.mapper.PricePersistenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository priceJpaRepository;
    private final PricePersistenceMapper pricePersistenceMapper;

    public PriceRepositoryAdapter(PriceJpaRepository priceJpaRepository, PricePersistenceMapper pricePersistenceMapper) {
        this.priceJpaRepository = priceJpaRepository;
        this.pricePersistenceMapper = pricePersistenceMapper;
    }

    @Override
    public List<Price> findApplicablePrices(Long productId, Long brandId, LocalDateTime applicationDate) {
        log.info("Executing findApplicablePrices with productId={}, brandId={}, applicationDate={}",
                productId, brandId, applicationDate);

        List<PriceEntity> pricesEntities = priceJpaRepository.findApplicablePrice(applicationDate, productId, brandId);

        log.debug("Found {} price entities for productId={}, brandId={}, applicationDate={}",
                pricesEntities.size(), productId, brandId, applicationDate);

        List<Price> prices = pricesEntities.stream()
                .map(pricePersistenceMapper::mapToDomain)
                .collect(Collectors.toList());

        log.info("Mapped {} price entities to domain model for productId={}, brandId={}",
                prices.size(), productId, brandId);

        return prices;
    }
}
