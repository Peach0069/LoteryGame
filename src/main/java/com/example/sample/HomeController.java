package com.example.sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HomeController {
    User user;


    @FXML
    private Label balance;

    @FXML
    private Button LuckyNumberButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button TigerAndDragonButton;

    @FXML
    public void logout(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Are you sure want to logout?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            openNewScene("hello-view.fxml");
        }
    }

    public HomeController(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        this.balance.setText(String.valueOf(user.getBalance()));

        //TigerAndDragon open button
        TigerAndDragonButton.setOnAction(event -> {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            openNewSceneDragon("DragonTiger.fxml");
        });
        //LuckyNumber open button
        LuckyNumberButton.setOnAction(event -> {
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
            openNewSceneLucky("LuckyNumber.fxml");
        });

    }
    public void openNewSceneDragon(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            DragonTigerControler dragonTigerControler = new DragonTigerControler(user);
            loader.setController(dragonTigerControler);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void openNewSceneLucky(String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            LuckyNumberController luckyNumberController = new LuckyNumberController(user);
            loader.setController(luckyNumberController);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void openNewScene(String window) {
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

