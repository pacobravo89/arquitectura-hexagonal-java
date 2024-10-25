package com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.repository;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.PriceSpringJpaAdapterRepository;
import com.pacobravo.hexagonal.infrastructure.price.adapter.mapper.PriceDboMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceH2Repository implements PriceRepositoryPort {

    private final PriceSpringJpaAdapterRepository priceSpringJpaAdapterRepository;
    private final PriceDboMapper priceDboMapper;

    public PriceH2Repository(PriceSpringJpaAdapterRepository priceSpringJpaAdapterRepository, PriceDboMapper priceDboMapper) {
        this.priceSpringJpaAdapterRepository = priceSpringJpaAdapterRepository;
        this.priceDboMapper = priceDboMapper;
    }

    @Override
    public List<Price> findPrices(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate) {
        List<PriceEntity> pricesEntities = priceSpringJpaAdapterRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(productId, brandId, startDate, endDate);
        return pricesEntities.stream()
                .map(priceDboMapper::toDomain)
                .collect(Collectors.toList());
    }
}
