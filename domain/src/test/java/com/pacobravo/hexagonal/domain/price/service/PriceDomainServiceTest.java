package com.pacobravo.hexagonal.domain.price.service;

import com.pacobravo.hexagonal.domain.price.ObjectDataFactory;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.model.exception.ResourceNotFoundException;
import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.pacobravo.hexagonal.domain.price.DomainTestConstant.BRAND_ID;
import static com.pacobravo.hexagonal.domain.price.DomainTestConstant.DATE_14_10AM;
import static com.pacobravo.hexagonal.domain.price.DomainTestConstant.PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceDomainServiceTest extends ObjectDataFactory {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceDomainService priceDomainService;

    @Test
    void givenPricesWithDifferentPriorities_whenGetHighestPriorityPrice_thenReturnPriceWithHighestPriority() throws IOException {
        // Given
        Price lowPriorityPrice = createPrice();

        Price highPriorityPrice = createPrice2();

        List<Price> pricesList = Arrays.asList(lowPriorityPrice, highPriorityPrice);

        when(priceRepositoryPort.findApplicablePrices(PRODUCT_ID, BRAND_ID, DATE_14_10AM)).thenReturn(pricesList);

        // When
        Price result = priceDomainService.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getPriority());
    }

    @Test
    void givenNoPricesFound_whenGetHighestPriorityPrice_thenThrowResourceNotFoundException() {
        // Given
        when(priceRepositoryPort.findApplicablePrices(PRODUCT_ID, BRAND_ID, DATE_14_10AM)).thenReturn(Collections.emptyList());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                priceDomainService.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM)
        );

        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    void givenSinglePriceFound_whenGetHighestPriorityPrice_thenReturnTheOnlyPrice() throws IOException {
        // Given
        Price onlyPrice = createPrice();

        when(priceRepositoryPort.findApplicablePrices(PRODUCT_ID, BRAND_ID, DATE_14_10AM)).thenReturn(List.of(onlyPrice));

        // When
        Price result = priceDomainService.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getPriority());
    }
}
