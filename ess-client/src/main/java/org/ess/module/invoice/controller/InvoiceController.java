package org.ess.module.invoice.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.bookings.model.BookingResponse;
import org.ess.module.invoice.pojo.InvoiceTableData;
import org.ess.module.invoice.request.CreateInvoiceResponse;
import org.ess.module.invoice.service.InvoiceService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected Text fxInvoiceEventName;

    @FXML
    protected Text fxStartDate;

    @FXML
    protected Text fxEndDate;

    @FXML
    protected Text fxInvoiceTotal;

    @FXML
    protected TableView<InvoiceTableData> fxInvoiceTable;

    private final InvoiceService invoiceService = new InvoiceService();

    protected static final Logger logger = LogManager.getLogger(InvoiceController.class);

    private final ObservableList<InvoiceTableData> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableColumn<InvoiceTableData, String> itemStartDateColumn = new TableColumn<>("Start Date");
        itemStartDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<InvoiceTableData, String> itemEndDateColumn = new TableColumn<>("End Date");
        itemEndDateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStartDate()));

        TableColumn<InvoiceTableData, Double> itemQuantityColumn = new TableColumn<>("Quantity");
        itemQuantityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCost()));

        TableColumn<InvoiceTableData, Double> itemPriceColumn = new TableColumn<>("Price");
        itemPriceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCost()));

        fxInvoiceTable
                .getColumns()
                .addAll(itemStartDateColumn, itemEndDateColumn, itemQuantityColumn, itemPriceColumn);

        Platform.runLater(this::requestEvents);
        fxInvoiceTable.setItems(userModelObservableList);
    }

    void requestEvents() {
        var bookingModel = getBookingFromData();
        invoiceService.get(1, new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<CreateInvoiceResponse> call, @NotNull Response<CreateInvoiceResponse> response) {
                logger.info("Get Success {} {}", response.code(), response.message());

                if (response.isSuccessful() && response.body() != null) {
                    var invoiceResponse = response.body();
                    fxInvoiceEventName.setText(invoiceResponse.getEvent().getName());
                    fxStartDate.setText(invoiceResponse.getEvent().getStartDate());
                    fxEndDate.setText(invoiceResponse.getEvent().getEndDate());
                    fxInvoiceTotal.setText("$" + invoiceResponse.getAmount());
                    userModelObservableList.addAll(response.body().getBookings().stream().map(item ->
                            InvoiceTableData
                                    .builder()
                                    .name(item.getEvent().getName())
                                    .startDate(item.getStartDate())
                                    .endDate(item.getEndDate())
                                    .build()
                    ).toList());

                    fxInvoiceTable.setItems(userModelObservableList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<CreateInvoiceResponse> call, @NotNull Throwable throwable) {
                logger.error(throwable);
            }
        });
    }

    private BookingResponse getBookingFromData() {
        var bookingModel = (BookingResponse) getData().get("bookingModel");

        logger.info("Booking Model For Editing: {}", new Gson().toJson(bookingModel));

        return bookingModel;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getData() {
        return (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();
    }
}
