package org.ess.module.asset.controller;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.FormMode;
import org.ess.module.asset.event.AssetEvent;
import org.ess.module.asset.model.AssetModel;
import org.ess.module.asset.service.AssetService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AssetFormController implements Initializable {

    @FXML
    protected VBox fxRoot;

    @FXML
    protected TextField fxAssetName;

    @FXML
    protected ComboBox<String> fxAssetCategory;

    @FXML
    protected ComboBox<Integer> fxAssetQuantity;

    @FXML
    protected ComboBox<String> fxAssetStatus;

    @FXML
    protected ComboBox<String> fxAssetCondition;

    @FXML
    protected TextField fxAssetPrice;

    private final AssetService assetService = new AssetService();

    protected static final Logger logger = LogManager.getLogger(AssetFormController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
        fxAssetQuantity.getItems().addAll(1, 2, 3, 4, 5);
        fxAssetCategory.getItems().addAll("Electronics", "Furniture", "Stationary", "Others");
        fxAssetStatus.getItems().addAll("Available", "Not Available");
        fxAssetCondition.getItems().addAll("New", "Old");

        Platform.runLater(() -> {
            if (getAssetFormMode().equals(FormMode.EDIT)) {
                AssetModel assetModel = getAssetModelFromData();
                fxAssetName.setText(assetModel.getName());
                fxAssetCategory.setValue(assetModel.getCategory());
                fxAssetQuantity.setValue(assetModel.getQuantity());
                fxAssetStatus.setValue(assetModel.getStatus());
                fxAssetCondition.setValue(assetModel.getCondition());
                fxAssetPrice.setText(String.valueOf(assetModel.getPrice()));
            }
        });
    }

    @FXML
    void onCancelAssetForm(MouseEvent event) {
        logger.info("Cancel Asset Form");
        Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
    }

    @FXML
    void onSubmitAssetForm(MouseEvent event) {
        logger.info("onSubmitAssetForm");

        if (getAssetFormMode().equals(FormMode.CREATE)) {
            createAsset();
        } else {
            updateAsset();
        }
    }

    private void updateAsset() {
        assetService.put(getAssetModel().setId(getAssetModelFromData().getId()), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssetModel> call, @NotNull Response<AssetModel> response) {
                logger.info("Update Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.UPDATE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<AssetModel> call, @NotNull Throwable throwable) {
                logger.error(throwable.getMessage());
            }
        });

        AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
    }

    private void createAsset() {
        assetService.post(getAssetModel(), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AssetModel> call, @NotNull Response<AssetModel> response) {
                logger.info("Create Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<AssetModel> call, @NotNull Throwable throwable) {
                logger.error(throwable.getMessage());
            }
        });

        AssetEvent.subject.onNext(AssetEvent.Type.CREATE);
    }

    private AssetModel getAssetModel() {
        AssetModel assetModel = new AssetModel();
        assetModel.setName(fxAssetName.getText());
        assetModel.setCategory(fxAssetCategory.getValue());
        assetModel.setQuantity(fxAssetQuantity.getValue());
        assetModel.setStatus(fxAssetStatus.getValue());
        assetModel.setCondition(fxAssetCondition.getValue());
        assetModel.setPrice(Double.parseDouble(fxAssetPrice.getText()));
        return assetModel;
    }

    private FormMode getAssetFormMode() {

        if (getData().get("mode").toString().equals(FormMode.EDIT.name())) {
            return FormMode.EDIT;
        }

        return FormMode.CREATE;
    }

    private AssetModel getAssetModelFromData() {
        var assetModel = (AssetModel) getData().get("assetModel");

        logger.info("Asset Model For Editing: {}", new Gson().toJson(assetModel));

        return assetModel;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getData() {
        return (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();
    }
}
