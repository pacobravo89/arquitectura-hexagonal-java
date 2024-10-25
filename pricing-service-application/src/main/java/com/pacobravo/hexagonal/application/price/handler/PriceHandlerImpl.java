package com.pacobravo.hexagonal.application.price.handler;

import com.pacobravo.hexagonal.application.price.mapper.PriceDtoMapper;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceResponse;
import com.pacobravo.hexagonal.domain.price.service.PriceService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceHandlerImpl implements PriceHandler {

    private final PriceService priceService;
    private final PriceDtoMapper priceDtoMapper;

    public PriceHandlerImpl(PriceService priceService, PriceDtoMapper priceDtoMapper) {
        this.priceService = priceService;
        this.priceDtoMapper = priceDtoMapper;
    }

    public PriceResponse getPrices(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate){
        return priceDtoMapper.toDto(priceService.getPrices(productId, brandId, startDate, endDate));
    }
}
