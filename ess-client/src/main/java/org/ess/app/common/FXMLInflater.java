package org.ess.app.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.ess.EssApplication;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utility class for inflating FXML files and creating Scene objects.
 */
public class FXMLInflater {

    /**
     * The layouts path attribute
     */
    public static final String LAYOUT_PATH = String.format("%s%s%s%s%s", "src", File.separator, "resources", File.separator, "layouts");

    /**
     * Inflate and bind a controller to the layout
     *
     * @param layoutName The file to load
     * @return {@link Scene}
     * @throws IOException IOException
     */
    public static Scene inflate(final String layoutName) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getLayoutURL(layoutName));

        return new Scene(fxmlLoader.load());
    }

    /**
     * Inflate and bind a controller to the layout
     *
     * @param layoutName The file to load
     * @param controller The controller to bindFxml
     * @return {@link Scene}
     * @throws IOException IOException
     */
    public static Scene inflate(final String layoutName, final Object controller) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getLayoutURL(layoutName));

        fxmlLoader.setController(controller);

        return new Scene(fxmlLoader.load());
    }

    /**
     * Inflates an FXML file and returns a Scene object.
     *
     * @param url The location of the FXML file to inflate
     * @return The Scene object created from the inflated FXML file
     * @throws IOException If an I/O error occurs during the inflation
     */
    public static Scene inflate(URL url) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Parent parent = fxmlLoader.load();

        return new Scene(parent);
    }

    /**
     * Inflate and load a JavaFX Parent object from an FXML layout file.
     *
     * @param layout The name of the FXML layout file to load.
     * @return The JavaFX Parent object loaded from the FXML layout file.
     * @throws RuntimeException If an I/O error occurs during loading.
     */
    public static Parent inflateParent(String layout) {

        try {
            return new FXMLLoader(EssApplication.class.getResource(layout)).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inflates and loads a JavaFX Parent object from an FXML layout file.
     *
     * @param layout The name of the FXML layout file to load.
     * @param controller The controller to bind to the layout.
     * @return The JavaFX Parent object loaded from the FXML layout file.
     * @throws RuntimeException If an I/O error occurs during loading.
     */
    public static Parent inflateParent(String layout, Object controller) {

        try {
            var fxml = new FXMLLoader(EssApplication.class.getResource(layout));
            fxml.setController(controller);

            return fxml.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve the layout url
     *
     * @param layoutName The name of the layout
     * @return {@link URL} The url object
     * @throws MalformedURLException throws exception
     */
    private static URL getLayoutURL(final String layoutName) throws MalformedURLException {

        File file = new File(String.format("%s%s%s%s", LAYOUT_PATH, File.separator, layoutName, ".fxml"));

        return file.toURI().toURL();
    }
}
