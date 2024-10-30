package com.pacobravo.hexagonal.application.price.mapper;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceApplicationMapper {

    PriceQueryResponse mapToDto(Price entity);
}
