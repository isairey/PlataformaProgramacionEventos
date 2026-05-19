package org.ess.module.invoice.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class CreateInvoiceRequest {

    private long eventId;

    private List<Long> bookingIds;
}
