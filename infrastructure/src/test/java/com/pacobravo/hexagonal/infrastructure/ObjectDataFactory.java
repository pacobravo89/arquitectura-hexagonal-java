package com.pacobravo.hexagonal.infrastructure;

import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObjectDataFactory {

    private static final JsonObjectCreator jsonObjectCreator = new JsonObjectCreator();

    public Price createPrice() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price.json", Price.class);
    }

    public PriceEntity createPriceEntity() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price_entity.json", PriceEntity.class);
    }

    public PriceQueryResponse createPriceQueryResponse() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price_query_response.json", PriceQueryResponse.class);
    }

    public PriceQueryResponseDTO createPriceQueryResponseDTO() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price_query_response.json", PriceQueryResponseDTO.class);
    }
}

