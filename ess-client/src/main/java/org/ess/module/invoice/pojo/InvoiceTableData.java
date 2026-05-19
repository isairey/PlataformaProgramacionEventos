package org.ess.module.invoice.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class InvoiceTableData {

    private String name;
    private double cost;
    private String startDate;
    private String endDate;
}
