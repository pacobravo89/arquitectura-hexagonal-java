package com.pacobravo.hexagonal.web;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ObjectDataFactory {

    private static final JsonObjectCreator jsonObjectCreator = new JsonObjectCreator();

    public List<PriceQueryResponse> createPriceQueryResponseList() throws IOException {
        return jsonObjectCreator.createListFromJson("price_query_response_list.json", PriceQueryResponse.class);
    }

    public List<PriceQueryResponseDTO> createPriceQueryResponseDTOList() throws IOException {
        return jsonObjectCreator.createListFromJson("price_query_response_dto_list.json", PriceQueryResponseDTO.class);
    }
}

