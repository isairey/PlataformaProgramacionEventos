package com.ess.essserver.module.invoice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class InvoiceRequestDTO {

    @NotNull(message = "event ID is required")
    private Long eventId;

    private List<Long> bookingIds;
}
