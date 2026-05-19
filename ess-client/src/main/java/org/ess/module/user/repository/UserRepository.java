package org.ess.module.user.repository;

import org.ess.module.user.model.UserModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Repository interface for user-related operations.
 */
public interface UserRepository {

    /**
     * Retrieves a list of users.
     *
     * @return a Call object containing a list of UserModel objects
     */

    @GET("users")
    Call<List<UserModel>> get();

    /**
     * Posts a new user.
     *
     * @param user the user model to be posted
     * @return a Call object containing the posted UserModel object
     */

    @POST("users")
    Call<UserModel> post(@Body UserModel user);

    /**
     * Deletes a user.
     *
     * @param id the ID of the user to be deleted
     * @return a Call object indicating whether the deletion was successful
     */

    @DELETE("users/{id}")
    Call<Boolean> delete(@Path("id") int id);
}
