package com.ess.essserver.module.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDTO {

    @NotNull(message = "event ID is required")
    private Long eventId;

    @NotNull(message = "Asset ID is required")
    private Long assetId;

    @NotNull(message = "Start time is required")
    private LocalDate startDate;

    @NotNull(message = "End time is required")
    private LocalDate endDate;
}