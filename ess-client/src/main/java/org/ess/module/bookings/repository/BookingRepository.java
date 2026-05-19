package org.ess.module.bookings.repository;

import org.ess.module.bookings.model.BookingRequest;
import org.ess.module.bookings.model.BookingResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface BookingRepository {

    @GET("/api/bookings")
    Call<List<BookingResponse>> get(@QueryMap Map<String, String> options);

    @POST("/api/bookings")
    Call<BookingResponse> post(@Body BookingRequest bookingModel);

    @GET("/api/bookings/{id}")
    Call<BookingResponse> get(@Path("id") long id);

    @PUT("/api/bookings/{id}")
    Call<BookingResponse> put(@Path("id") long id, @Body BookingRequest bookingModel);

    @DELETE("/api/bookings/{id}")
    Call<Boolean> delete(@Path("id") long id);

}
