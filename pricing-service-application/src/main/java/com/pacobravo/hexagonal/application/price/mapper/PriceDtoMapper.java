package com.pacobravo.hexagonal.application.price.mapper;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceResponse;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PriceDtoMapper {

    public PriceResponse toDto(Price entity);
}
