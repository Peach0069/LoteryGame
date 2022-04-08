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

    public static void alertError(String error, String mes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(mes);
        alert.showAndWait().get();
    }

    public static void alertSucces(String succes, String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(succes);
        alert.setHeaderText(mess);
        alert.showAndWait().get();
    }

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
                location.isBlank() || gender.isBlank() ) {
            alertError("Error", "Complete all fields");
            new Shake(signUpFirstName).playAnimation();
            new Shake(signUpLastName).playAnimation();
            new Shake(loginField).playAnimation();
            new Shake(passwordField).playAnimation();
            new Shake(signUpCountry).playAnimation();
            return false;
        } else if (user != null) {
            alertError("Username", "Username is already registered");
        } else if (passCheck(password)) {
            alertSucces("Hi", "Register succesful");
            user = new User(firstName, lastName, userName, password, location, gender, 10000);
            dbHandler.signUpUser(user);
            return true;
        }
        return false;
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
        dialog.setResizable(false);
        dialog.setScene(new Scene(root));
        dialog.show();
    }

    static boolean passCheck(String password) {
        int passwordSpecialDigitCount = 0,
                passwordLengthCount = 0,
                passwordLower = 0;

        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (specialCharactersString.contains(Character.toString(ch))) {
                passwordSpecialDigitCount++;
            } else if (password.length() >= 8) {
                passwordLengthCount++;
            }
        }
        if (passwordLengthCount < 1) {
            alertError("Password", "Password required minim 8 characters");
            return false;
        } else if (passwordSpecialDigitCount < 1) {
            alertError("Password", "Password required minim one special character");
            return false;
        }
        return true;
    }



    }



