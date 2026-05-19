package com.ess.essserver.module.invoice;

import com.ess.essserver.module.booking.BookingResponseDTO;
import com.ess.essserver.module.event.EventResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class InvoiceResponseDTO {

    private Long id;
    private EventResponseDTO event;
    private List<BookingResponseDTO> bookings;
    private BigDecimal amount;
    private String paymentStatus;
    private LocalDateTime issuedOn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}