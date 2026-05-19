package com.ess.essserver.module.booking;

import com.ess.essserver.app.PaymentStatus;
import com.ess.essserver.module.asset.AssetResponseDTO;
import com.ess.essserver.module.event.EventResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponseDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentStatus paymentStatus;
    private EventResponseDTO event;
    private AssetResponseDTO asset;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}