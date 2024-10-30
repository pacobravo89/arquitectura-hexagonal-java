package com.pacobravo.hexagonal.application.price;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ApplicationTestConstant {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;

    public static final LocalDateTime DATE_14_10AM = LocalDateTime.parse("2020-06-14T10:00:00", DATE_FORMATTER);
}
