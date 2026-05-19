package org.ess.module.asset.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.asset.event.AssetEvent;
import org.ess.module.asset.service.AssetService;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class AssetDeleteConfirmationController implements Initializable {

    @FXML
    protected VBox fxRoot;

    private final AssetService assetService = new AssetService();

    protected static final Logger logger = LogManager.getLogger(AssetDeleteConfirmationController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
    }

    @FXML
    @SuppressWarnings("unchecked")
    void onClickDeleteAsset(MouseEvent event) {
        logger.info("Delete Asset");

        Map<String, Object> data = (Map<String, Object>) fxRoot.getScene().getWindow().getUserData();

        assetService.delete((Long) data.get("id"), new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Boolean> call, @NotNull Response<Boolean> response) {
                logger.info("Delete Asset Success {} {}", response.code(), response.message());
                AssetEvent.subject.onNext(AssetEvent.Type.DELETE);
                Platform.runLater(() -> fxRoot.getScene().getWindow().hide());
            }

            @Override
            public void onFailure(@NotNull Call<Boolean> call, @NotNull Throwable throwable) {

            }
        });

    }

    @FXML
    void onClickCancelDeleteAsset(MouseEvent event) {
        logger.info("Delete Asset Cancelled");

        fxRoot.getScene().getWindow().hide();
    }

}
