package org.ess.module.bookings.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.ess.app.PaymentStatus;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.event.model.EventModel;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class BookingResponse {
    private long id;
    private EventModel event;
    private AssetModel asset;
    private double price;
    private String startDate;
    private String endDate;
    private PaymentStatus paymentStatus;
    private String updatedAt;
    private String createdAt;
}
