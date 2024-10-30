package com.pacobravo.hexagonal.infrastructure;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class InfrastructureTestConstant {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;

    public static final String DATE_14_10AM_STRING = "2020-06-14T10:00:00";
    public static final LocalDateTime DATE_14_10AM = LocalDateTime.parse(DATE_14_10AM_STRING, DATE_FORMATTER);
}
