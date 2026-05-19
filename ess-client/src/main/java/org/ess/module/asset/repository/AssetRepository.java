package org.ess.module.asset.repository;

import org.ess.module.asset.model.AssetModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AssetRepository {

    @GET("/api/assets/{id}")
    Call<Boolean> get(@Path("id") int id);

    @PUT("/api/assets/{id}")
    Call<AssetModel> put(@Path("id") long id, @Body AssetModel assetModel);

    @DELETE("/api/assets/{id}")
    Call<Boolean> delete(@Path("id") long id);

    @GET("/api/assets")
    Call<List<AssetModel>> get();

    @POST("/api/assets")
    Call<AssetModel> post(@Body AssetModel assetModel);
}
