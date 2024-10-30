package com.pacobravo.hexagonal.application.price.handler;

import com.pacobravo.hexagonal.application.price.ObjectDataFactory;
import com.pacobravo.hexagonal.application.price.mapper.PriceApplicationMapper;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.domain.price.model.exception.ResourceNotFoundException;
import com.pacobravo.hexagonal.domain.price.service.PriceDomainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.pacobravo.hexagonal.application.price.ApplicationTestConstant.BRAND_ID;
import static com.pacobravo.hexagonal.application.price.ApplicationTestConstant.DATE_14_10AM;
import static com.pacobravo.hexagonal.application.price.ApplicationTestConstant.PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceQueryHandlerImplTest extends ObjectDataFactory {

    @Mock
    private PriceDomainService priceDomainService;

    @Mock
    private PriceApplicationMapper priceApplicationMapper;

    @InjectMocks
    private PriceQueryHandlerImpl priceQueryHandler;

    @Test
    void givenPriceExists_whenGetHighestPriorityPrice_thenReturnMappedPriceQueryResponse() throws IOException {
        // Given
        Price price = createPrice();
        PriceQueryResponse priceQueryResponse = createPriceQueryResponse();
        when(priceDomainService.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM)).thenReturn(price);
        when(priceApplicationMapper.mapToDto(price)).thenReturn(priceQueryResponse);

        // When
        PriceQueryResponse result = priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);

        // Then
        assertNotNull(result);
        assertEquals(priceQueryResponse, result);
        assertEquals(priceQueryResponse.getPrice(), result.getPrice());
        verify(priceDomainService, times(1)).getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);
        verify(priceApplicationMapper, times(1)).mapToDto(price);
    }

    @Test
    void givenPriceNotFound_whenGetHighestPriorityPrice_thenThrowResourceNotFoundException() {
        // Given
        when(priceDomainService.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM)).thenThrow(new ResourceNotFoundException("Product not found"));

        // When / Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM)
        );

        assertNotNull(exception);
        assertEquals("Product not found", exception.getMessage());
        verify(priceDomainService, times(1)).getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);
        verify(priceApplicationMapper, never()).mapToDto(any());
    }
}
