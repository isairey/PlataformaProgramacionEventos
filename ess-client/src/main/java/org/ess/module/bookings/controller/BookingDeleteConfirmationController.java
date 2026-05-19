package org.ess.module.bookings.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.bookings.event.BookingEvent;
import org.ess.module.bookings.service.BookingService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class BookingDeleteConfirmationController implements Initializable {

    @FXML
    protected VBox fxRoot;

    private final BookingService bookingService = new BookingService();

    private final Logger logger = LogManager.getLogger(BookingDeleteConfirmationController.class.getName());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
    }

    @FXML
    @SuppressWarnings("unchecked")
    void onClickDeleteBooking(MouseEvent event) {
        logger.info("Delete Booking");

        Map<String, Object> data = (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();

        bookingService.delete((Long) data.get("id"), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Boolean> call, @NotNull Response<Boolean> response) {
                logger.info("Delete Booking Success {} {}", response.code(), response.message());
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
                BookingEvent.subject.onNext(BookingEvent.Type.DELETE);
            }

            @Override
            public void onFailure(@NotNull Call<Boolean> call, @NotNull Throwable throwable) {
                logger.info("{} {}", throwable.getMessage(), throwable.getCause());
            }
        });
    }


    @FXML
    void onClickCancelDeleteBooking(MouseEvent event) {
        logger.info("Delete Booking Cancelled");

        fxRoot.getScene().getWindow().hide();
    }
}
