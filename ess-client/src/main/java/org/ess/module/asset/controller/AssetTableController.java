package org.ess.module.asset.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.FormMode;
import org.ess.app.common.TabType;
import org.ess.app.controller.TabController;
import org.ess.module.asset.event.AssetEvent;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.service.AssetService;
import org.ess.app.window.View;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.*;

public class AssetTableController extends TabController implements Initializable {

    @FXML
    TableView<AssetModel> fxAssetTableLayout;

    private final AssetService assetService = new AssetService();

    protected static final Logger logger = LogManager.getLogger(AssetTableController.class);

    private final ObservableList<AssetModel> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    private void get() {
        logger.info("Get");
        assetService.get(new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<List<AssetModel>> call, @NotNull Response<List<AssetModel>> response) {
                logger.info("Get Success {} {}", response.code(), response.message());

                userModelObservableList.clear();
                userModelObservableList.addAll(Optional.ofNullable(response.body()).orElse(new ArrayList<>()));
                fxAssetTableLayout.setItems(userModelObservableList);
            }

            @Override
            public void onFailure(@NotNull Call<List<AssetModel>> call, @NotNull Throwable throwable) {
                logger.info("Get");
                logger.error(throwable.getMessage());
            }
        });
    }

    @Override
    public void initialize(Tab tab) {
        get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<AssetModel, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        TableColumn<AssetModel, String> usernameColumn = new TableColumn<>("Name");
        usernameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));

        TableColumn<AssetModel, String> firstNameColumn = new TableColumn<>("Category");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCategory()));

        TableColumn<AssetModel, Integer> lastNameColumn = new TableColumn<>("Quantity");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        TableColumn<AssetModel, String> roleColumn = new TableColumn<>("Condition");
        roleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCondition()));

        TableColumn<AssetModel, String> dobColumn = new TableColumn<>("Status");
        dobColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatus()));

        TableColumn<AssetModel, String> updateAtColumn = new TableColumn<>("Update At");
        updateAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUpdatedAt()));

        TableColumn<AssetModel, String> createAtColumn = new TableColumn<>("Create At");
        createAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));

        TableColumn<AssetModel, HBox> deleteStaff = getAssetActionsTableColumn();

        fxAssetTableLayout
                .getColumns()
                .addAll(idColumn, usernameColumn, firstNameColumn, lastNameColumn, roleColumn, dobColumn, updateAtColumn, createAtColumn, deleteStaff);

        setTabType(TabType.ASSET);
        Platform.runLater(this);
        get();

        AssetEvent.subscribe(AssetEvent.Type.CREATE, type -> get());
        AssetEvent.subscribe(AssetEvent.Type.UPDATE, type -> get());
        AssetEvent.subscribe(AssetEvent.Type.DELETE, type -> get());
    }

    private static @NotNull TableColumn<AssetModel, HBox> getAssetActionsTableColumn() {
        TableColumn<AssetModel, HBox> deleteStaff = new TableColumn<>("Actions");

        deleteStaff.setCellValueFactory(cellData -> {
            var hBox = new HBox();
            var editBtn = new Button("Edit");
            var deleteBtn = new Button("Delete");
            HBox.setHgrow(editBtn, Priority.ALWAYS);
            HBox.setHgrow(deleteBtn, Priority.ALWAYS);
            editBtn.setOnMouseClicked(event -> View.assetFormWindow(Map.of("mode", FormMode.EDIT.name(), "assetModel", cellData.getValue())));
            deleteBtn.setOnMouseClicked(event -> View.assetDeleteConfirmationWindow(Map.of("id", cellData.getValue().getId())));
            hBox.getChildren().add(editBtn);
            hBox.getChildren().add(deleteBtn);
            hBox.setSpacing(10);
            hBox.styleProperty().set("-fx-alignment: center");

            return new SimpleObjectProperty<>(hBox);
        });
        return deleteStaff;
    }
}
