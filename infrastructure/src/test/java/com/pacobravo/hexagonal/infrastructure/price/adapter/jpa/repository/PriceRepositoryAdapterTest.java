package com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.repository;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.infrastructure.ObjectDataFactory;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import com.pacobravo.hexagonal.infrastructure.price.adapter.jpa.PriceJpaRepository;
import com.pacobravo.hexagonal.infrastructure.price.adapter.mapper.PricePersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.BRAND_ID;
import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.DATE_14_10AM;
import static com.pacobravo.hexagonal.infrastructure.InfrastructureTestConstant.PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest extends ObjectDataFactory {
    
    @Mock
    private PriceJpaRepository priceJpaRepository;

    @Mock
    private PricePersistenceMapper pricePersistenceMapper;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void givenValidProductBrandAndDate_whenFindApplicablePrices_thenReturnMappedPricesList() throws IOException {
        // Given
        PriceEntity priceEntity = createPriceEntity();
        Price expectedPrice = createPrice();

        when(priceJpaRepository.findApplicablePrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID))
                .thenReturn(List.of(priceEntity));
        when(pricePersistenceMapper.mapToDomain(priceEntity)).thenReturn(expectedPrice);

        // When
        List<Price> result = priceRepositoryAdapter.findApplicablePrices(PRODUCT_ID, BRAND_ID, DATE_14_10AM);
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedPrice, result.get(0));
        verify(priceJpaRepository, times(1)).findApplicablePrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID);
        verify(pricePersistenceMapper, times(1)).mapToDomain(priceEntity);

    }

    @Test
    void givenInvalidProductBrandAndDate_whenFindApplicablePrices_thenReturnEmptyPriceList() {
        // Given
        when(priceJpaRepository.findApplicablePrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID)).thenReturn(Collections.emptyList());

        // When
        List<Price> result = priceRepositoryAdapter.findApplicablePrices(PRODUCT_ID, BRAND_ID, DATE_14_10AM);

        // Then
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(priceJpaRepository, times(1)).findApplicablePrice(DATE_14_10AM, PRODUCT_ID, BRAND_ID);
        verify(pricePersistenceMapper, never()).mapToDomain(any(PriceEntity.class));
    }
}
