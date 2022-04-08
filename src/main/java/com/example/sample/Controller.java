package com.example.sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {
    User user;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginLoginButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private Label message;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    public static void alertError(String error, String mes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(mes);
        alert.showAndWait().get();
    }

    @FXML
    void initialize() {
        loginLoginButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
            } else {
                alertError("error","Login or/and Password fields is empty");
            }
        });

        loginSignUpButton.setOnAction(event -> {
            openNewScene("signUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        user = dbHandler.getUser(loginText);

        if (user != null && user.getPassword().equals(loginPassword)) {
            openNewScenelobby("app.fxml");
        } else {
            this.message.setText("Incorect login or password");
            Shake userLoginAnimation = new Shake(loginField);
            Shake userPasswordAnimation = new Shake(passwordField);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
            //incorect password dialog window
        }
    }

    public void openNewScenelobby(String window) {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            SignUpController signUpController = new SignUpController();
            HomeController homeController = new HomeController(user);
            loader.setController(homeController);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void openNewScene(String window) {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            SignUpController signUpController = new SignUpController();
            HomeController homeController = new HomeController(user);
            loader.setController(signUpController);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

}
