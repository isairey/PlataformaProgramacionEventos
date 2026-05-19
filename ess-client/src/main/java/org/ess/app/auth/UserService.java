package org.ess.app.auth;

import org.ess.app.model.User;
import org.ess.app.common.HttpClient;
import retrofit2.Callback;

import java.util.Map;

public class UserService {

    public void auth(User user, Callback<Map<String, String>> callback) {
        HttpClient.use(AuthRepository.class).post(user.getEmail(), user.getPassword()).enqueue(callback);
    }
}
