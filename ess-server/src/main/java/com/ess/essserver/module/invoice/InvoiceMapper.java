package com.ess.essserver.module.invoice;

import com.ess.essserver.module.booking.BookingEntity;
import com.ess.essserver.module.booking.BookingMapper;
import com.ess.essserver.module.event.EventEntity;
import com.ess.essserver.module.event.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

    private final BookingMapper bookingMapper;
    private final EventMapper eventMapper;

    public InvoiceEntity toEntity(EventEntity event, List<BookingEntity> booking, BigDecimal amount, String paymentStatus, LocalDateTime issuedOn) {
        return InvoiceEntity.builder()
                .event(event)
                .bookings(booking)
                .amount(amount)
                .paymentStatus(paymentStatus)
                .issuedOn(issuedOn)
                .build();
    }

    public InvoiceResponseDTO toResponseDTO(InvoiceEntity entity) {
        return InvoiceResponseDTO.builder()
                .id(entity.getId())
                .event(eventMapper.toEventResponseDTO(entity.getEvent()))
                .bookings(entity.getBookings().stream().map(bookingMapper::toResponseDTO).toList())
                .amount(entity.getAmount())
                .paymentStatus(entity.getPaymentStatus())
                .issuedOn(entity.getIssuedOn())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
