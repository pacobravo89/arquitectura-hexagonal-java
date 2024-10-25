package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import com.pacobravo.hexagonal.application.price.handler.PriceHandler;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceHandler priceHandler;

    public PriceController(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    @Operation(summary = "Consulta el precio de un producto filtrando por marca y hora")
    @PostMapping()
    public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceIn priceIn) {
        log.info("estoy aqui");
        PriceResponse price = priceHandler.getPrices(priceIn.getProductId(), priceIn.getBrandId(), priceIn.getStartDate(), priceIn.getEndDate());

        if (price == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(price);
    }
}
