package org.ess.app.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.FormMode;
import org.ess.app.window.View;

import java.util.Map;

public class DashboardController {

    public static final Logger logger = LogManager.getLogger(DashboardController.class);

    @FXML
    void onClickCreateAsset(MouseEvent event) {
        logger.info("Create Asset");

        View.assetFormWindow(Map.of("mode", FormMode.CREATE));
    }

    @FXML
    void onClickCreateEvent(MouseEvent event) {
        logger.info("Create Event");

        View.eventFormWindow(Map.of("mode", FormMode.CREATE));
    }
}
