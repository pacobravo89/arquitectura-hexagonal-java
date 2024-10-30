package com.pacobravo.hexagonal.domain.price;

import com.pacobravo.hexagonal.domain.price.model.entity.Price;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObjectDataFactory {

    private static final JsonObjectCreator jsonObjectCreator = new JsonObjectCreator();

    public Price createPrice() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price.json", Price.class);
    }

    public Price createPrice2() throws IOException {
        return jsonObjectCreator.createObjectFromJson("price_two.json", Price.class);
    }
}

