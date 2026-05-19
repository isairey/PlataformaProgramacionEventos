package com.ess.essserver.module.invoice;

import com.ess.essserver.module.booking.BookingEntity;
import com.ess.essserver.module.event.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    List<InvoiceEntity> findAllByEvent(EventEntity event);

    Optional<InvoiceEntity> findByEventAndBookingsIn(EventEntity event, Collection<List<BookingEntity>> bookings);
}
