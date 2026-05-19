package org.ess.module.event.repository;

import org.ess.module.event.model.EventModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EventRepository {

    @GET("/api/events/{id}")
    Call<Boolean> get(@Path("id") int id);

    @PUT("/api/events/{id}")
    Call<EventModel> put(@Path("id") long id, @Body EventModel assetModel);

    @DELETE("/api/events/{id}")
    Call<Boolean> delete(@Path("id") long id);

    @GET("/api/events")
    Call<List<EventModel>> get();

    @POST("/api/events")
    Call<EventModel> post(@Body EventModel assetModel);
}
