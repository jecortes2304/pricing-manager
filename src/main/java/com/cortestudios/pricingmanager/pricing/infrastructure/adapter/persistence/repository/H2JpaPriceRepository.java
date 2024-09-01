package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.repository;

import com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.entity.PriceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface H2JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "SELECT p.* FROM prices p " +
            "WHERE p.brand_id = :brandId AND p.product_id = :productId " +
            "AND :applicationDate BETWEEN p.start_date AND p.end_date " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<PriceEntity> findPriceByProductIdAndBrandIdAndDate(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate
    );

    @Query(value = "SELECT p.* FROM prices p " +
            "WHERE p.brand_id = :brandId AND p.product_id = :productId " +
            "ORDER BY p.priority DESC",
            nativeQuery = true)
    Page<PriceEntity> findPricesByProductIdAndBrandId(
            @Param("productId") Long productId,
            @Param("brandId") Long brandId,
            Pageable pageable
    );

}
