package com.pacobravo.hexagonal.infrastructure.price.rest.mapper;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.infrastructure.ObjectDataFactory;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PriceQueryResponseMapperTest extends ObjectDataFactory {

    private final PriceQueryResponseMapper mapper = Mappers.getMapper(PriceQueryResponseMapper.class);

    @Test
    void givenPriceQueryResponse_whenMapperToDto_thenReturnsPriceQueryResponseDTO() throws IOException {
        // Given
        PriceQueryResponse priceEntity = createPriceQueryResponse();

        // When
        PriceQueryResponseDTO price = mapper.mapperToDto(priceEntity);

        // Then
        assertNotNull(price);
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
    void givenNullEntity_whenMapperToDto_thenReturnsNull() {
        // When
        PriceQueryResponseDTO price = mapper.mapperToDto(null);

        // Then
        assertNull(price);
    }
}
