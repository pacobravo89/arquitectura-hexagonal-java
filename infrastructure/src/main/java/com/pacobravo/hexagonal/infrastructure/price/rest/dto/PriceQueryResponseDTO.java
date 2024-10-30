package com.pacobravo.hexagonal.infrastructure.price.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Object that reflects the final price and applicable rate for a product from a chain within specific dates")
public class PriceQueryResponseDTO {

    @Schema(description = "Group brand identifier", example = "1")
    private Long brandId;

    @Schema(description = "Product code identifier", example = "35455")
    private Long productId;

    @Schema(description = "Price list identifier", example = "1")
    private int priceList;

    @Schema(description = "Start date for applying the indicated list price", example = "2020-06-14-00.00.00")
    private LocalDateTime startDate;

    @Schema(description = "End date for applying the indicated list price", example = "2020-12-31-23.59.59")
    private LocalDateTime endDate;

    @Schema(description = "ISO currency code", example = "EUR")
    private String currency;

    @Schema(description = "Final sale price", example = "35.50")
    private Double price;

    @Schema(description = "Price application disambiguator", example = "0")
    private int priority;
}
