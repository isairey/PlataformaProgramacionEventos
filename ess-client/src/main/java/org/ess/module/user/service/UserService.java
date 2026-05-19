package org.ess.module.user.service;

import org.ess.app.common.HttpClient;
import org.ess.module.user.model.UserModel;
import org.ess.module.user.repository.UserRepository;
import retrofit2.Callback;

import java.util.List;

/**
 * Service class for handling user-related operations.
 */
public class UserService {

    /**
     * Retrieves a list of users.
     *
     * @param callback the callback to handle the response containing a list of UserModel objects
     */

    public void get(Callback<List<UserModel>> callback) {
        HttpClient.use(UserRepository.class).get().enqueue(callback);
    }

    /**
     * Posts a new user.
     *
     * @param userModel the user model to be posted
     * @param callback the callback to handle the response containing the posted UserModel object
     */

    public void post(UserModel userModel, Callback<UserModel> callback) {
        HttpClient.use(UserRepository.class).post(userModel).enqueue(callback);
    }

    /**
     * Deletes a user.
     *
     * @param userModel the user model to be deleted
     * @param callback the callback to handle the response indicating whether the deletion was successful
     */

    public void delete(UserModel userModel, Callback<Boolean> callback) {
        HttpClient.use(UserRepository.class).delete(userModel.getId()).enqueue(callback);
    }
}
