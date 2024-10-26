package com.pacobravo.hexagonal.infrastructure.price.adapter.jpa;

import com.pacobravo.hexagonal.infrastructure.price.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceSpringJpaAdapterRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrice(@Param("applicationDate") LocalDateTime applicationDate,
                                              @Param("productId") Long productId,
                                              @Param("brandId") Long brandId);
}
