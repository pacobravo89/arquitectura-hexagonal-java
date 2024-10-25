package com.pacobravo.hexagonal.infrastructure.beanconfiguration;

import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;
import com.pacobravo.hexagonal.domain.price.service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceBean {

    @Bean
    public PriceService priceService(PriceRepositoryPort priceRepositoryPort) {
        return new PriceService(priceRepositoryPort);
    }
}
