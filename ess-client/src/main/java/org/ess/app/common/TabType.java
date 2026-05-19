package org.ess.app.common;

public enum TabType {
    USER("fxUserTab"),
    ASSET("fxAssetTab"),
    EVENT("fxEventTab"),
    BOOKING("fxBookingTab"),
    INVOICE("fxIInvoiceTab"),
    REPORT("fxReportTab");

    private final String name;

    TabType(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }
}
