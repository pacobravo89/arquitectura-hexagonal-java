package com.pacobravo.hexagonal.web.controller;

import com.pacobravo.hexagonal.application.price.handler.PriceQueryHandler;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.infrastructure.price.rest.controller.PriceController;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import com.pacobravo.hexagonal.infrastructure.price.rest.mapper.PriceQueryResponseMapper;
import com.pacobravo.hexagonal.web.ObjectDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.List;

import static com.pacobravo.hexagonal.web.WebTestConstant.BRAND_ID;
import static com.pacobravo.hexagonal.web.WebTestConstant.BRAND_ID_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_10AM;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_10AM_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_16PM;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_16PM_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_21PM;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_14_21PM_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_15_10AM;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_15_10AM_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_15_21PM;
import static com.pacobravo.hexagonal.web.WebTestConstant.DATE_15_21PM_String;
import static com.pacobravo.hexagonal.web.WebTestConstant.NAME_APPLICATION_DATE;
import static com.pacobravo.hexagonal.web.WebTestConstant.NAME_BRAND_ID;
import static com.pacobravo.hexagonal.web.WebTestConstant.NAME_PRODUCT_ID;
import static com.pacobravo.hexagonal.web.WebTestConstant.PRODUCT_ID;
import static com.pacobravo.hexagonal.web.WebTestConstant.PRODUCT_ID_STRING;
import static com.pacobravo.hexagonal.web.WebTestConstant.URL_PRICES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PriceController.class, PriceQueryHandler.class, PriceQueryResponseMapper.class})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class PriceControllerIntegrationTest extends ObjectDataFactory {

    private MockMvc mockMvc;

    @MockBean
    private PriceQueryHandler priceQueryHandler;

    @MockBean
    private PriceQueryResponseMapper priceQueryResponseMapper;

    private List<PriceQueryResponse> priceQueryResponseList;
    private List<PriceQueryResponseDTO> priceQueryResponseDTOList;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new PriceController(priceQueryHandler, priceQueryResponseMapper)).build();

        priceQueryResponseList = createPriceQueryResponseList();
        priceQueryResponseDTOList = createPriceQueryResponseDTOList();
    }

    @Test
    void givenRequestAt14_10AM_whenFindProductPrice_thenReturnPriceList1AndPrice3550() throws Exception {
        // Given
        when(priceQueryResponseMapper.mapperToDto(any())).thenReturn(priceQueryResponseDTOList.get(0));
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_10AM))
                .thenReturn(priceQueryResponseList.get(0));

        // When & Then
        mockMvc.perform(get(URL_PRICES)
                        .param(NAME_APPLICATION_DATE, DATE_14_10AM_STRING)
                        .param(NAME_PRODUCT_ID, PRODUCT_ID_STRING)
                        .param(NAME_BRAND_ID, BRAND_ID_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void givenRequestAt14_16PM_whenFindProductPrice_thenReturnPriceList2AndPrice2545() throws Exception {
        // Given
        when(priceQueryResponseMapper.mapperToDto(any())).thenReturn(priceQueryResponseDTOList.get(1));
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_16PM))
                .thenReturn(priceQueryResponseList.get(1));

        // When & Then
        mockMvc.perform(get(URL_PRICES)
                        .param(NAME_APPLICATION_DATE, DATE_14_16PM_STRING)
                        .param(NAME_PRODUCT_ID, PRODUCT_ID_STRING)
                        .param(NAME_BRAND_ID, BRAND_ID_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void givenRequestAt14_21PM_whenFindProductPrice_thenReturnPriceList1AndPrice3550() throws Exception {
        // Given
        when(priceQueryResponseMapper.mapperToDto(any())).thenReturn(priceQueryResponseDTOList.get(0));
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_14_21PM))
                .thenReturn(priceQueryResponseList.get(0));

        // When & Then
        mockMvc.perform(get(URL_PRICES)
                        .param(NAME_APPLICATION_DATE, DATE_14_21PM_STRING)
                        .param(NAME_PRODUCT_ID, PRODUCT_ID_STRING)
                        .param(NAME_BRAND_ID, BRAND_ID_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void givenRequestAt15_10AM_whenFindProductPrice_thenReturnPriceList3AndPrice3050() throws Exception {
        // Given
        when(priceQueryResponseMapper.mapperToDto(any())).thenReturn(priceQueryResponseDTOList.get(2));
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_15_10AM))
                .thenReturn(priceQueryResponseList.get(2));

        // When & Then
        mockMvc.perform(get(URL_PRICES)
                        .param(NAME_APPLICATION_DATE, DATE_15_10AM_STRING)
                        .param(NAME_PRODUCT_ID, PRODUCT_ID_STRING)
                        .param(NAME_BRAND_ID, BRAND_ID_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void givenRequestAt16_21PM_whenFindProductPrice_thenReturnPriceList4AndPrice3895() throws Exception {
        // Given
        when(priceQueryResponseMapper.mapperToDto(any())).thenReturn(priceQueryResponseDTOList.get(3));
        when(priceQueryHandler.getHighestPriorityPrice(PRODUCT_ID, BRAND_ID, DATE_15_21PM))
                .thenReturn(priceQueryResponseList.get(3));

        // When & Then
        mockMvc.perform(get(URL_PRICES)
                        .param(NAME_APPLICATION_DATE, DATE_15_21PM_String)
                        .param(NAME_PRODUCT_ID, PRODUCT_ID_STRING)
                        .param(NAME_BRAND_ID, BRAND_ID_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
