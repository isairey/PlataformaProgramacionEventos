package org.ess.app.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.TabType;

import java.util.Objects;

@Setter
public abstract class TabController implements ChangeListener<Tab>, Runnable {

    protected static final Logger logger = LogManager.getLogger(TabController.class);

    protected TabType tabType;

    public TabController() {}

    public TabController(TabType tabType) {
        this.tabType = tabType;
    }

    public abstract void initialize(Tab tab);

    @Override
    public void changed(ObservableValue<? extends Tab> observableValue, Tab previousTab, Tab currentTab) {
        logger.info("Tab Changed: Previous {} Current {}", previousTab.getId(), currentTab.getId());

        if (Objects.equals(tabType.value(), currentTab.getId()))
            initialize(currentTab);
    }

    @Override
    public void run() {
        logger.info("Initialize: TabId {}", tabType.value());

        ((TabPane) Stage.getWindows().getFirst().getScene().lookup("#fxDashabordTabPane")).getSelectionModel().selectedItemProperty().addListener(this);
    }

}