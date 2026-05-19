package org.ess.module.bookings.service;

import org.ess.app.common.HttpClient;
import org.ess.module.bookings.model.BookingRequest;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.bookings.repository.BookingRepository;
import retrofit2.Callback;

import java.util.List;
import java.util.Map;

public class BookingService {

    public void get(Map<String, String> options, Callback<List<BookingResponse>> callback) {
        HttpClient.use(BookingRepository.class).get(options).enqueue(callback);
    }

    public void post(BookingRequest bookingModel, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).post(bookingModel).enqueue(callback);
    }

    public void get(long id, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).get(id).enqueue(callback);
    }

    public void put(BookingRequest bookingModel, Callback<BookingResponse> callback) {
        HttpClient.use(BookingRepository.class).put(bookingModel.getId(), bookingModel).enqueue(callback);
    }

    public void delete(long id, Callback<Boolean> callback) {
        HttpClient.use(BookingRepository.class).delete(id).enqueue(callback);
    }
}
