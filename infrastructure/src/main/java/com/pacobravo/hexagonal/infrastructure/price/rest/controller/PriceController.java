package com.pacobravo.hexagonal.infrastructure.price.rest.controller;

import com.pacobravo.hexagonal.application.price.handler.PriceQueryHandler;
import com.pacobravo.hexagonal.domain.price.model.dto.PriceQueryResponse;
import com.pacobravo.hexagonal.infrastructure.exception.model.ErrorDetails;
import com.pacobravo.hexagonal.infrastructure.price.rest.dto.PriceQueryResponseDTO;
import com.pacobravo.hexagonal.infrastructure.price.rest.mapper.PriceQueryResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceQueryHandler priceQueryHandler;
    private final PriceQueryResponseMapper priceQueryResponseMapper;

    public PriceController(PriceQueryHandler priceQueryHandler, PriceQueryResponseMapper priceQueryResponseMapper) {
        this.priceQueryHandler = priceQueryHandler;
        this.priceQueryResponseMapper = priceQueryResponseMapper;
    }

    @Operation(summary = "Query the price of a product filtered by brand and time.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = PriceQueryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    })
    @GetMapping()
    public ResponseEntity<PriceQueryResponseDTO> findProductPrice(@RequestParam("applicationDate")
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                                                  @RequestParam("productId") Long productId,
                                                                  @RequestParam("brandId") Long brandId) {
        log.info("Received request to findProductPrice with applicationDate={}, productId={}, brandId={}",
                applicationDate, productId, brandId);

        try {
            PriceQueryResponse price = priceQueryHandler.getHighestPriorityPrice(productId, brandId, applicationDate);
            PriceQueryResponseDTO priceQueryResponseDTO = priceQueryResponseMapper.mapperToDto(price);

            log.info("findProductPrice successful for productId={}, brandId={}. Returning response: {}", productId, brandId, priceQueryResponseDTO);

            return ResponseEntity.ok(priceQueryResponseDTO);
        } catch (Exception e) {
            log.error("Unexpected error occurred while querying findProductPrice for productId={}, brandId={}, applicationDate={}",
                    productId, brandId, applicationDate, e);
            throw e;
        }
    }
}
