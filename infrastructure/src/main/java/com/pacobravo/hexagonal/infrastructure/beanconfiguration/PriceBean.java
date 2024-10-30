package com.pacobravo.hexagonal.infrastructure.beanconfiguration;

import com.pacobravo.hexagonal.domain.price.port.repository.PriceRepositoryPort;
import com.pacobravo.hexagonal.domain.price.service.PriceDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceBean {

    @Bean
    public PriceDomainService priceDomainService(PriceRepositoryPort priceRepositoryPort) {
        return new PriceDomainService(priceRepositoryPort);
    }
}
