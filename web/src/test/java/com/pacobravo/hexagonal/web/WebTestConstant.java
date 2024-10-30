package com.pacobravo.hexagonal.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class WebTestConstant {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static final Long PRODUCT_ID = 35455L;
    public static final String PRODUCT_ID_STRING = "35455";
    public static final Long BRAND_ID = 1L;
    public static final String BRAND_ID_STRING = "1";

    public static final String DATE_14_10AM_STRING = "2020-06-14T10:00:00";
    public static final LocalDateTime DATE_14_10AM = LocalDateTime.parse(DATE_14_10AM_STRING, DATE_FORMATTER);
    public static final String DATE_14_16PM_STRING = "2020-06-14T16:00:00";
    public static final LocalDateTime DATE_14_16PM = LocalDateTime.parse(DATE_14_16PM_STRING, DATE_FORMATTER);
    public static final String DATE_14_21PM_STRING = "2020-06-14T21:00:00";
    public static final LocalDateTime DATE_14_21PM = LocalDateTime.parse(DATE_14_21PM_STRING, DATE_FORMATTER);
    public static final String DATE_15_10AM_STRING = "2020-06-15T10:00:00";
    public static final LocalDateTime DATE_15_10AM = LocalDateTime.parse(DATE_15_10AM_STRING, DATE_FORMATTER);
    public static final String DATE_15_21PM_String = "2020-06-16T21:00:00";
    public static final LocalDateTime DATE_15_21PM = LocalDateTime.parse(DATE_15_21PM_String, DATE_FORMATTER);

    public static final String NAME_APPLICATION_DATE = "applicationDate";
    public static final String NAME_PRODUCT_ID = "productId";
    public static final String NAME_BRAND_ID = "brandId";
    public static final String URL_PRICES = "/prices";

}
