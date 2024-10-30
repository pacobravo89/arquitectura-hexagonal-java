package com.pacobravo.hexagonal.application.price.handler;

import com.pacobravo.hexagonal.application.price.mapper.PriceApplicationMapper;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.service.PriceDomainService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceQueryHandlerImpl implements PriceQueryHandler {

    private final PriceDomainService priceDomainService;
    private final PriceApplicationMapper priceApplicationMapper;

    public PriceQueryHandlerImpl(PriceDomainService priceDomainService, PriceApplicationMapper priceApplicationMapper) {
        this.priceDomainService = priceDomainService;
        this.priceApplicationMapper = priceApplicationMapper;
    }

    public PriceQueryResponse getHighestPriorityPrice(Long productId, Long brandId, LocalDateTime applicationDate){
        return priceApplicationMapper.mapToDto(priceDomainService.getHighestPriorityPrice(productId, brandId, applicationDate));
    }
}
