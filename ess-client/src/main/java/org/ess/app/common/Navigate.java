package org.ess.app.common;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.ess.EssApplication;

import java.io.IOException;
import java.util.Map;

public class Navigate {

    /**
     * Retrieve the application primary stage
     *
     * @return {@link Stage}
     */
    public static Stage getPrimaryStage() {

        return (Stage) Stage.getWindows();
    }

    public static Stage getParentStage() {

        return (Stage) Stage.getWindows().getFirst();
    }

    /**
     * Route the application to a scene
     *
     * @param layout The name of the layout
     */
    public static void root(Stage stage, String layout) {

        try {

            // Inflate the toDashboard layout
            Scene scene = FXMLInflater.inflate(EssApplication.class.getResource(layout));

            //
            stage.setTitle("event Scheduling System");

            // Assign a new scene on the primary stage
            stage.setScene(scene);

            // Display the newly assigned scene
            stage.show();

            scene.getWindow().setHeight(600);
            scene.getWindow().setWidth(600);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * Open a new window with the specified layout.
     *
     * @param layout The name of the layout file to load
     */
    public static void to(String layout) {
        Stage stage = getParentStage();
        stage.getScene().setRoot(FXMLInflater.inflateParent(layout));
        stage.show();
    }

    /**
     * Open a new window with the specified layout.
     *
     * @param layout The name of the layout file to load
     */
    public static void root(String layout, double width, double height) {
        Stage stage = getParentStage();
        stage.getScene().setRoot(FXMLInflater.inflateParent(layout));
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }

    /**
     * Open a new window with the specified name and layout.
     *
     * @param name   The name of the window
     * @param layout The name of the layout file to load
     */
    public static void toWindow(String name, String layout) {
        Parent parent = FXMLInflater.inflateParent(layout);
        Stage stage = new Stage();
        stage.initOwner(Stage.getWindows().getLast());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setScene(new Scene(parent, 600, 600));
        stage.show();
        parent.requestFocus();
    }

    public static void toWindow(String name, String layout, Map<String, Object> data) {
        Parent parent = FXMLInflater.inflateParent(layout);
        Stage stage = new Stage();
        stage.initOwner(Stage.getWindows().getLast());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setUserData(data);
        stage.setScene(new Scene(parent, 600, 600));
        stage.show();
        parent.requestFocus();
    }

    public static void toWindow(String name, String layout, int width, int height) {
        Parent parent = FXMLInflater.inflateParent(layout);
        Stage stage = new Stage();
        stage.initOwner(Stage.getWindows().getLast());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setScene(new Scene(parent, width, height));
        stage.show();
        parent.requestFocus();
    }

    public static void toWindow(String name, String layout, Map<String, Object> userData, int width, int height) {
        Parent parent = FXMLInflater.inflateParent(layout);
        Stage stage = new Stage();
        stage.initOwner(Stage.getWindows().getLast());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setUserData(userData);
        stage.setScene(new Scene(parent, width, height));
        stage.show();
        parent.requestFocus();
    }

    /**
     * Open a new window with the specified name, layout, and controller.
     *
     * @param name       The name of the window
     * @param layout     The name of the layout file to load
     * @param controller The controller object to bind to the layout
     */
    public static Stage toWindow(String name, String layout, Object controller) {
        Parent parent = FXMLInflater.inflateParent(layout, controller);
        Stage stage = new Stage();
        stage.initOwner(getParentStage());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(name);
        stage.setScene(new Scene(parent, 600, 600));
        stage.show();
        parent.requestFocus();

        return stage;
    }

    /**
     * Route the application to a scene
     *
     * @param name   The name of the scene
     * @param layout The name of the layout
     */
    public static void root(String name, String layout) {

        try {

            // Retrieve the primary stage
            Stage primaryStage = getPrimaryStage();

            // Inflate the toDashboard layout
            Scene scene = FXMLInflater.inflate(EssApplication.class.getResource(layout));

            //
            primaryStage.setTitle(name);

            // Assign a new scene on the primary stage
            primaryStage.setScene(scene);

            // Display the newly assigned scene
//            primaryStage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}
