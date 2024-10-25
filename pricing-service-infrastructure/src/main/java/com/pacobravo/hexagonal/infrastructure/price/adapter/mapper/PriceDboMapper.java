package com.pacobravo.hexagonal.infrastructure.price.adapter.mapper;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceDboMapper {

    public Price toDomain(PriceEntity entity){
        if(entity == null) return null;
        return new Price(entity.getId(), entity.getBrandId(), entity.getProductId(), entity.getPriceList(), entity.getStartDate(),entity.getEndDate(), entity.getCurrency(), entity.getPrice(), entity.getPriority());
    }
}
