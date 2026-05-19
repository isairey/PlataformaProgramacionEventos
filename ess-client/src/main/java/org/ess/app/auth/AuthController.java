package org.ess.app.auth;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ess.app.common.Data;
import org.ess.app.common.Navigate;
import org.jetbrains.annotations.NotNull;
import org.ess.app.model.User;
import org.ess.app.model.AuthModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The LoginController class is responsible for handling the login functionality of the application.
 */
public class AuthController implements Initializable {

    protected static final Logger logger = LogManager.getLogger(AuthController.class);

    @FXML
    private VBox fxIdFieldContainer;

    @FXML
    protected TextField fxUsername;

    @FXML
    protected VBox fxPasswordContainer;

    @FXML
    protected PasswordField fxPassword;

    @FXML
    protected Button fxSubmitButton;

    @FXML
    VBox fxSubmitButtonContainer;

    protected AuthModel authModel;

    UserService userService = new UserService();

    public AuthController() {
        authModel = new AuthModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logger.info("Initialize");
    }

    @FXML
    private void onClickSignIn(MouseEvent event) {
        logger.info("onClickSignIn()");

//        var isValidEmail = this.email(fxIdFieldContainer, fxUsername, "Not a valid email address");
//        var isValidPassword = this.validatePassword();
//
//        if (!isValidEmail || !isValidPassword) return;

        var user = new User().setEmail(fxUsername.getText()).setPassword(fxPassword.getText());
        this.userService.auth(user, new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<Map<String, String>> call, @NotNull Response<Map<String, String>> response) {
                Data.token = Optional.ofNullable(response.body()).orElse(new HashMap<>()).get("token");
                logger.info("Success: {}", response.body());
                Platform.runLater(() -> Navigate.root("dashboard.fxml", 800, 600));
            }

            @Override
            public void onFailure(@NotNull Call<Map<String, String>> call, @NotNull Throwable throwable) {

            }
        });
    }

    /**
     * Invoke when the user enter their id number
     *
     * @param keyEvent {@link KeyEvent}
     */
    @FXML
    private void onEnterIdNumber(KeyEvent keyEvent) {

//        if (validateIdNumber()) authModel.setIdNumber(fxUsername.getText());
    }

    /**
     * Validate the id number
     *
     * @return {@link Boolean}
     */
    protected boolean validateIdNumber() {

        final boolean isEmpty = fxUsername.getText().isEmpty();

        final String errorMessage = isEmpty ? "Id number is required" : "Id number should be 6 or more characters";

        return textFieldValidator(fxIdFieldContainer, fxUsername, 7, errorMessage);
    }

    /**
     * Invoke when the user enter their password
     *
     * @param keyEvent {@link KeyEvent}
     */
    @FXML
    private void onEnterPassword(KeyEvent keyEvent) {

//        if (validatePassword()) authModel.setPassword(fxPassword.getText());

    }

    /**
     * Validate the password
     *
     * @return {@link Boolean}
     */
    protected boolean validatePassword() {

        final boolean isEmpty = fxPassword.getText().length() == 0;

        final String errorMessage = isEmpty ? "Password is required" : "Password should be 4 or more characters";

        return textFieldValidator(fxPasswordContainer, fxPassword, 4, errorMessage);
    }

    protected boolean email(VBox container, TextField textField, String errorMessage) {
        // Regular expression for email validation
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(textField.getText());

        var match = matcher.matches();

        if (match) container.getChildren().add(1, getErrorMessage(errorMessage));

        // Perform the matching and return the result
        return match;
    }

    /**
     * Validate text field
     *
     * @param container  {@link VBox}
     * @param textField  {@link TextField}
     * @param charLength {@link Integer}
     * @return {@link Boolean}
     */
    protected boolean textFieldValidator(VBox container, TextField textField, int charLength, String errorMessage) {

        boolean success = false;

        if (textField.getText().length() < charLength) {

            if (container.getChildren().size() > 1) container.getChildren().remove(1);
            container.getChildren().add(1, getErrorMessage(errorMessage));

        } else {

            if (container.getChildren().size() > 1) container.getChildren().remove(1);

            success = true;
        }

        return success;
    }

    /**
     * Retrieves an error message label with the specified message text.
     *
     * @param message The error message text.
     * @return The error message label.
     */
    public Label getErrorMessage(String message) {
        final Label label = new Label(message);
        label.setStyle("-fx-text-fill: red");

        return label;
    }

}
