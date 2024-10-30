package com.pacobravo.hexagonal.infrastructure.price.adapter.mapper;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricePersistenceMapper {

    Price mapToDomain(PriceEntity entity);
}
