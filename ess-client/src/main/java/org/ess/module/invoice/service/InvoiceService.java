package org.ess.module.invoice.service;

import org.ess.app.common.HttpClient;
import org.ess.module.invoice.repository.InvoiceRepository;
import org.ess.module.invoice.request.CreateInvoiceRequest;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import retrofit2.Callback;

import java.util.List;

public class InvoiceService {

    public void get(Callback<List<CreateInvoiceResponse>> callback) {
        HttpClient.use(InvoiceRepository.class).get().enqueue(callback);
    }

    public void get(long id, Callback<CreateInvoiceResponse> callback) {
        HttpClient.use(InvoiceRepository.class).get(id).enqueue(callback);
    }

    public void post(CreateInvoiceRequest createInvoiceRequest, Callback<CreateInvoiceResponse> callback) {
        HttpClient.use(InvoiceRepository.class).post(createInvoiceRequest).enqueue(callback);
    }
}
