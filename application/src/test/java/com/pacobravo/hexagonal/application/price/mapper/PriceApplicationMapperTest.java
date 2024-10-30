package com.pacobravo.hexagonal.application.price.mapper;

import com.pacobravo.hexagonal.application.price.ObjectDataFactory;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PriceApplicationMapperTest extends ObjectDataFactory {

    private final PriceApplicationMapper mapper = Mappers.getMapper(PriceApplicationMapper.class);

    @Test
    void givenPrice_whenMapToDto_thenReturnsPriceQueryResponse() throws IOException {
        // Given
        Price price = createPrice();

        // When
        PriceQueryResponse response = mapper.mapToDto(price);

        // Then
        assertNotNull(response);
        assertEquals(price.getProductId(), response.getProductId());
        assertEquals(price.getBrandId(), response.getBrandId());
        assertEquals(price.getPrice(), response.getPrice());
        assertEquals(price.getPriority(), response.getPriority());
        assertEquals(price.getStartDate(), response.getStartDate());
        assertEquals(price.getEndDate(), response.getEndDate());
    }

    @Test
    void givenNullEntity_whenMapToDto_thenReturnsNull() {
        // When
        PriceQueryResponse price = mapper.mapToDto(null);

        // Then
        assertNull(price);
    }
}
