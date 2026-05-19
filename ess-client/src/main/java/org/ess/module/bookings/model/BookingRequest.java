package org.ess.module.bookings.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.ess.app.PaymentStatus;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class BookingRequest {
    private long id;
    private long eventId;
    private long assetId;
    private String startDate;
    private String endDate;
    private PaymentStatus paymentStatus;
    private String updatedAt;
    private String createdAt;
}
