package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import com.pacobravo.hexagonal.application.price.handler.PriceHandler;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceHandler priceHandler;

    public PriceController(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    @Operation(summary = "Consulta el precio de un producto filtrando por marca y hora")
    @GetMapping()
    public ResponseEntity<PriceResponse> getPrice(@RequestParam("applicationDate")
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                                  @RequestParam("productId") Long productId,
                                                  @RequestParam("brandId") Long brandId) {
        PriceResponse price = priceHandler.getPrices(productId, brandId, applicationDate);

        if (price == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(price);
    }
}
