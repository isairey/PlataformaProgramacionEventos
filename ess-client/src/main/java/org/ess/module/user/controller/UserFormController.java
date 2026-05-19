package org.ess.module.user.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.module.user.Event.UserEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    protected static final Logger logger = LogManager.getLogger(UserFormController.class);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");
    }

    @FXML
    void onClickCreate(MouseEvent event){
        logger.info("Create User");

        UserEvent.subject.onNext(UserEvent.Type.CREATE);
    }
}
