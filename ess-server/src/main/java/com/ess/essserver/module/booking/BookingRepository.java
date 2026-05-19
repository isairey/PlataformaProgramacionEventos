package com.ess.essserver.module.booking;

import com.ess.essserver.app.PaymentStatus;
import com.ess.essserver.module.asset.AssetEntity;
import com.ess.essserver.module.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    Optional<BookingEntity> findByEvent(EventEntity event);

    List<BookingEntity> findAllByEvent(EventEntity event);

    List<BookingEntity> findAllByEventAndPaymentStatus(EventEntity event, PaymentStatus paymentStatus);

    List<BookingEntity> findAllByIdInAndEventAndPaymentStatus(List<Long> ids, EventEntity event, PaymentStatus paymentStatus);

    List<BookingEntity> findAllByAssetAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(AssetEntity asset, LocalDate startTime, LocalDate endTime);

    List<BookingEntity> findAllByIdNotAndAssetAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(Long id, AssetEntity asset, LocalDate startTime, LocalDate endTime);
}