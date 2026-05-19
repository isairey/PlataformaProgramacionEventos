package org.ess.module.invoice.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.ess.app.PaymentStatus;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.event.model.EventModel;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class CreateInvoiceResponse {

    private long id;

    private EventModel event;

    private List<BookingResponse> bookings;

    private double amount;

    private PaymentStatus paymentStatus;

    private String issuedOn;

    private String createdAt;

    private String updatedAt;
}
