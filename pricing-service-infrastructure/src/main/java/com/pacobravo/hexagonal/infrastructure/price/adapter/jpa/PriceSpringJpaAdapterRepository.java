package com.pacobravo.hexagonal.infrastructure.price.adapter.jpa;

import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceSpringJpaAdapterRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);
}
