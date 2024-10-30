package com.pacobravo.hexagonal.infrastructure.price.adapter.mapper;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.infrastructure.ObjectDataFactory;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PricePersistenceMapperTest extends ObjectDataFactory {

    private final PricePersistenceMapper mapper = Mappers.getMapper(PricePersistenceMapper.class);

    @Test
    void givenPriceEntity_whenMapToDomain_thenReturnsPrice() throws IOException {
        // Given
        PriceEntity priceEntity = createPriceEntity();

        // When
        Price price = mapper.mapToDomain(priceEntity);

        // Then
        assertNotNull(price);
        assertEquals(priceEntity.getId(), price.getId());
        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getProductId(), price.getProductId());
        assertEquals(priceEntity.getPriceList(), price.getPriceList());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(priceEntity.getCurrency(), price.getCurrency());
        assertEquals(priceEntity.getPrice(), price.getPrice());
        assertEquals(priceEntity.getPriority(), price.getPriority());
    }

    @Test
    void givenNullEntity_whenMapToDomain_thenReturnsNull() {
        // When
        Price price = mapper.mapToDomain(null);

        // Then
        assertNull(price);
    }
}

