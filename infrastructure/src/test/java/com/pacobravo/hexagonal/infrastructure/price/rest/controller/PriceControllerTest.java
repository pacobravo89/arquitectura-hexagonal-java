package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import com.pacobravo.hexagonal.application.price.handler.PriceQueryHandler;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.model.exception.ResourceNotFoundException;
import com.pacobravo.hexagonal.infrastructure.ObjectDataFactory;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import com.pacobravo.hexagonal.infrastructure.price.rest.mapper.PriceQueryResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.BRAND_ID;
import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.DATE_14_10AM;
import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest extends ObjectDataFactory {

    @Mock
    private PriceQueryHandler priceQueryHandler;

    @Mock
    private PriceQueryResponseMapper priceQueryResponseMapper;

    @InjectMocks
    private PriceController priceController;

    @Test
    void givenPriceFound_whenFindProductPrice_thenReturnOkStatusWithPrice() throws IOException {
        // given
        PriceQueryResponse priceQueryResponse = createPriceQueryResponse();
        PriceQueryResponseDTO priceQueryResponseDTO = createPriceQueryResponseDTO();

        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM))
                .thenReturn(priceQueryResponse);
        when(priceQueryResponseMapper.mapperToDto(priceQueryResponse)).thenReturn(priceQueryResponseDTO);

        // when
        ResponseEntity<PriceQueryResponseDTO> response = priceController.findProductPrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID);

        // then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(priceQueryHandler, times(1)).getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);
        verify(priceQueryResponseMapper, times(1)).mapperToDto(priceQueryResponse);

    }

    @Test
    void givenPriceNotFound_whenFindProductPrice_thenThrowResourceNotFoundException() {
        // given
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM))
                .thenThrow(new ResourceNotFoundException("Not Found"));

        // when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                priceController.findProductPrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID)
        );

        // then
        assertNotNull(exception);
        assertEquals("Not Found", exception.getMessage());

        verify(priceQueryHandler, times(1)).getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM);
        verify(priceQueryResponseMapper, never()).mapperToDto(any());
    }
}
