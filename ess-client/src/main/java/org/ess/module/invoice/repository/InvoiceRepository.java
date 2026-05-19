package org.ess.module.invoice.repository;

import org.ess.module.invoice.request.CreateInvoiceRequest;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface InvoiceRepository {

    @GET("/api/invoices/{id}")
    Call<CreateInvoiceResponse> get(@Path("id") long id);

    @GET("/api/invoices")
    Call<List<CreateInvoiceResponse>> get();

    @POST("/api/invoices")
    Call<CreateInvoiceResponse> post(@Body CreateInvoiceRequest createInvoiceRequest);
}
