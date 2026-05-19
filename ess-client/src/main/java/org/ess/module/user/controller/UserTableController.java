package org.ess.module.user.controller;

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
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.TabType;
import org.ess.app.controller.TabController;
import org.ess.module.user.repository.UserFakeRepository;
import org.ess.module.user.Event.UserEvent;
import org.ess.module.user.model.UserModel;
import org.ess.module.user.service.UserService;
import org.ess.app.window.View;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserTableController extends TabController implements Initializable {

    @FXML
    TableView<UserModel> fxUserTableLayout;

    private final UserService userService = new UserService();

    private final ObservableList<UserModel> userModelObservableList = FXCollections.observableArrayList(new ArrayList<>());

    protected static final Logger logger = LogManager.getLogger(UserTableController.class);


    @Override
    public void initialize(Tab tab) {
        logger.info("User Tab Selected");

        req();

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Initialize");

        TableColumn<UserModel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        TableColumn<UserModel, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUsername()));

        TableColumn<UserModel, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFirstName()));

        TableColumn<UserModel, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLastName()));

        TableColumn<UserModel, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));

        TableColumn<UserModel, String> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateOfBirth()));

        TableColumn<UserModel, String> updateAtColumn = new TableColumn<>("Update At");
        updateAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUpdatedAt()));

        TableColumn<UserModel, String> createAtColumn = new TableColumn<>("Create At");
        createAtColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCreatedAt()));

        TableColumn<UserModel, HBox> deleteStaff = getUserActionsTableColumn();

        fxUserTableLayout
                .getColumns()
                .addAll(idColumn, usernameColumn, firstNameColumn, lastNameColumn, roleColumn, dobColumn, updateAtColumn, createAtColumn, deleteStaff);

        req();

        setTabType(TabType.USER);
        Platform.runLater(this);

        UserEvent.subscribe(UserEvent.Type.CREATE, e -> {
            logger.info("User Created");
        });
    }

    private static @NotNull TableColumn<UserModel, HBox> getUserActionsTableColumn() {
        TableColumn<UserModel, HBox> deleteStaff = new TableColumn<>("Actions");

        deleteStaff.setCellValueFactory(cellData -> {
            var hBox = new HBox();
            var editBtn = new Button("Edit");
            var deleteBtn = new Button("Delete");
            editBtn.setOnMouseClicked(event -> View.userFormWindow());
            deleteBtn.setOnMouseClicked(event -> View.userDeleteConfirmationWindow());
            hBox.getChildren().add(editBtn);
            hBox.getChildren().add(deleteBtn);
            hBox.setSpacing(10);
            hBox.styleProperty().set("-fx-alignment: center");

            return new SimpleObjectProperty<>(hBox);
        });
        return deleteStaff;
    }

    private void req() {

        userModelObservableList.clear();
        userModelObservableList.addAll(UserFakeRepository.getUsers(50));
        fxUserTableLayout.setItems(userModelObservableList);
    }


}
