package com.example.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private RadioButton signUpRadioButtonFemale;

    @FXML
    private RadioButton signUpRadioButtonMale;

    @FXML
    private TextField signUpCountry;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastName;

    @FXML
    void initialize() {

        signUpButton.setOnAction(event -> {
            if (signUpNewUser()) {
                openNewScene("hello-view.fxml");
            }
        });

    }

    private boolean signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String firstName = signUpFirstName.getText();
        String lastName = signUpLastName.getText();
        String userName = loginField.getText();
        String password = passwordField.getText();
        String location = signUpCountry.getText();
        String gender = "";
        if (signUpRadioButtonMale.isSelected()) {
            gender = "Male";
        }
        if (signUpRadioButtonFemale.isSelected()) {
            gender = "Female";
        }
        User user = dbHandler.getUser(userName);
        if (firstName.isBlank() || lastName.isBlank() || userName.isBlank() || password.isBlank() ||
                location.isBlank() || gender.isBlank() || user != null) {
            new Shake(signUpFirstName).playAnimation();
            new Shake(signUpLastName).playAnimation();
            new Shake(loginField).playAnimation();
            new Shake(passwordField).playAnimation();
            new Shake(signUpCountry).playAnimation();
            return false;
        }  else {
            user = new User(firstName, lastName, userName, password, location, gender, 10000);
            dbHandler.signUpUser(user);
            return true;
        }
    }

    public void openNewScene(String window) {
        signUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage dialog = new Stage();
        dialog.setScene(new Scene(root));
        dialog.show();
    }
}
