package org.ess.module.bookings.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.PaymentStatus;
import org.ess.app.common.FormMode;
import org.ess.module.bookings.event.BookingEvent;
import org.ess.module.bookings.model.BookingRequest;
import org.ess.app.window.View;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.bookings.service.BookingService;
import org.ess.module.event.model.EventModel;
import org.ess.module.invoice.request.CreateInvoiceRequest;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import org.ess.module.invoice.service.InvoiceService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class BookingTableController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected Button fxGenerateInvoice;

    @FXML
    protected TableView<BookingResponse> fxBookingTableLayout;

    private final BookingService bookingService = new BookingService();
    private final InvoiceService invoiceService = new InvoiceService();

    private final ObservableList<BookingResponse> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());
    private static final ObservableList<BookingResponse> selectedBookingList = FXCollections.observableArrayList(new ArrayList<>());

    protected static final Logger logger = LogManager.getLogger(BookingTableController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<BookingResponse, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<BookingResponse, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEndDate()));

        TableColumn<BookingResponse, String> paymentStatusColumn = new TableColumn<>("Payment Status");
        paymentStatusColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentStatus().name()));

        TableColumn<BookingResponse, String> updateAtColumn = new TableColumn<>("Update At");
        updateAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUpdatedAt()));

        TableColumn<BookingResponse, String> createAtColumn = new TableColumn<>("Create At");
        createAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));
//
//        fxBookingTableLayout.setRowFactory(row -> new TableRow<>() {
//            @Override
//            protected void updateItem(BookingRequest item, boolean empty) {
//                super.updateItem(item, empty);
////                setDisable(true);
//                if (item == null) {
////                    setStyle("");
//                } else {
////                    if (item.getStatus().equals("PENDING")) {
////                        setStyle("-fx-background-color: #ff0000;");
////                    } else {
////                        setStyle("");
////                    }
//                }
//            }
//        });

        fxBookingTableLayout
                .getColumns()
                .addAll(getCheckBox(), startDateColumn, endDateColumn, paymentStatusColumn, updateAtColumn, createAtColumn, getInvoiceColumn(), getEditColumn(), getDeleteColumn());

        Platform.runLater(() -> {
            fxBookingTableLayout.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
            fxBookingTableLayout.setPrefWidth(1); // Enables auto-sizing
            fxBookingTableLayout.setPrefWidth(1); // Enables auto-sizing
            fxBookingTableLayout.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                fxBookingTableLayout.refresh(); // Forces the table to re-render itself
            });

            requestBookingData();
        });

        if (selectedBookingList.isEmpty()) fxGenerateInvoice.setDisable(true);

        selectedBookingList.addListener((ListChangeListener<BookingResponse>) change -> {
            logger.info("Selected Bookings Change {}", selectedBookingList);

            fxGenerateInvoice.setDisable(false);

            if (selectedBookingList.isEmpty()) fxGenerateInvoice.setDisable(true);

            fxBookingTableLayout.refresh();
        });

        BookingEvent.subscribe(BookingEvent.Type.CREATE, event -> requestBookingData());
        BookingEvent.subscribe(BookingEvent.Type.UPDATE, event -> requestBookingData());
        BookingEvent.subscribe(BookingEvent.Type.DELETE, event -> requestBookingData());
    }

    @FXML
    void onClickCreateBooking(MouseEvent event) {
        logger.info("Create Booking");

        View.bookingFormWindow(Map.of("mode", FormMode.CREATE, "eventModel", getEventModelFromData()));
    }

    @FXML
    void onClickGenerateInvoice(MouseEvent event) {
        CreateInvoiceRequest request = CreateInvoiceRequest.builder()
                .eventId(getEventModelFromData().getId())
                .bookingIds(selectedBookingList.stream().map(BookingResponse::getId).toList()).build();

        logger.info("Generate Invoice Request: {}", new Gson().toJson(request));

        invoiceService.post(request, new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CreateInvoiceResponse> call, @NotNull Response<CreateInvoiceResponse> response) {
                logger.info("Create Invoice Response {} {}", response.code(), response.message());

                if (response.isSuccessful()) {
                    requestBookingData();
                    selectedBookingList.clear();
                }
            }

            @Override
            public void onFailure(@NotNull Call<CreateInvoiceResponse> call, @NotNull Throwable throwable) {
                logger.info("Create Invoice {}", throwable.getMessage());
            }
        });

    }

    private void requestBookingData() {
        var eventModel = getEventModelFromData();

        bookingService.get(Map.of("eventId", String.valueOf(eventModel.getId())), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<List<BookingResponse>> call, @NotNull Response<List<BookingResponse>> response) {
                logger.info("Booking Success {} {}", response.code(), response.message());

                if (response.isSuccessful() && response.body() != null) {
                    userModelObservableList.clear();
                    userModelObservableList.addAll(response.body());
                    fxBookingTableLayout.setItems(userModelObservableList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<BookingResponse>> call, @NotNull Throwable throwable) {
                logger.info("Booking Failure {}", throwable.getMessage());
            }
        });
    }

    private static @NotNull TableColumn<BookingResponse, CheckBox> getCheckBox() {
        TableColumn<BookingResponse, CheckBox> checkboxColumn = new TableColumn<>("Checked");
        checkboxColumn.setCellValueFactory(cellData -> {
            logger.info("setCellValueFactory CheckBox");

            var checkBox = new CheckBox();

            if (cellData.getValue().getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
                checkBox.setDisable(true);
            }

            if (selectedBookingList.contains(cellData.getValue())) {
                checkBox.setSelected(true);
            }

            checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                logger.info("CheckBox Clicked");
                if (selectedBookingList.contains(cellData.getValue())) {
                    selectedBookingList.remove(cellData.getValue());
                    checkBox.setSelected(false);
                } else {
                    checkBox.setSelected(true);
                    selectedBookingList.add(cellData.getValue());
                }
            });

            return new SimpleObjectProperty<>(checkBox);
        });

        return checkboxColumn;
    }

    private static @NotNull TableColumn<BookingResponse, Button> getInvoiceColumn() {
        TableColumn<BookingResponse, Button> deleteColumn = new TableColumn<>("Invoice");
        deleteColumn.setCellValueFactory(cellData -> {
            var button = new Button("Invoice");

            if (cellData.getValue().getPaymentStatus().equals(PaymentStatus.PENDING)) {
                button.setDisable(true);
            }

            button.setOnMouseClicked(event -> View.invoiceWindow(Map.of("id", cellData.getValue())));
            return new SimpleObjectProperty<>(button);
        });

        return deleteColumn;
    }

    private static @NotNull TableColumn<BookingResponse, Button> getEditColumn() {
        TableColumn<BookingResponse, Button> editColumn = new TableColumn<>("Edit");
        editColumn.setCellValueFactory(cellData -> {
            var button = new Button("Edit");
            if (cellData.getValue().getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
                button.setDisable(true);
            }
            button.setOnMouseClicked(event -> View.bookingFormWindow(Map.of(
                    "mode", FormMode.EDIT,
                    "bookingModel", cellData.getValue(),
                    "eventModel", cellData.getValue().getEvent()
            )));
            return new SimpleObjectProperty<>(button);
        });

        return editColumn;
    }

    private static @NotNull TableColumn<BookingResponse, Button> getDeleteColumn() {
        TableColumn<BookingResponse, Button> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellValueFactory(cellData -> {
            var button = new Button("Delete");
            if (cellData.getValue().getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
                button.setDisable(true);
            }
            button.setOnMouseClicked(event -> View.bookingDeleteConfirmationWindow(Map.of("id", cellData.getValue().getId())));
            return new SimpleObjectProperty<>(button);
        });

        return deleteColumn;
    }

    private EventModel getEventModelFromData() {
        var eventModel = (EventModel) getData().get("eventModel");

        logger.info("Event Model For Editing: {}", new Gson().toJson(eventModel));

        return eventModel;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getData() {
        return (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();
    }
}
