package org.ess.app.auth;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

public interface AuthRepository {

    @POST("/login")
    Call<Map<String, String>> post(@Query("username") String username, @Query("password") String password);
}
