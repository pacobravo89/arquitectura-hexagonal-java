package com.pacobravo.hexagonal.infrastructure.price.rest.mapper;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceQueryResponseMapper {
    PriceQueryResponseDTO mapperToDto(PriceQueryResponse price);
}
