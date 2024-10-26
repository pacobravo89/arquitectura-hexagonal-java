package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pacobravo.hexagonal.infrastructure.price.rest.deserializer.LocalDateTimeDeserializer;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Data
public class PriceIn {

    private Long productId;
    private Long brandId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime applicationDate;
}
