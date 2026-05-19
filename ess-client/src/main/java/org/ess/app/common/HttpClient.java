package org.ess.app.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;

public class HttpClient {

    protected static final Logger logger = LogManager.getLogger(HttpClient.class);


    public static <T> T use(final Class<T> repository) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public @NotNull Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder request = chain.request().newBuilder();

                        if (Data.token != null) request.addHeader("Authorization", Data.token);

                        return chain.proceed(request.build());
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public @NotNull Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());

                        // Log or inspect the response
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
                            logger.info("Response Code: {}", response.code());
                            assert response.body() != null;
                            logger.info("Response Body: {}", responseBody.string());
                        } else {
                            logger.info("Request Failed: {} {}", response.code(), response.message());
                        }

                        return response;
                    }
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(repository);
    }
}

