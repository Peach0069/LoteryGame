package com.example.sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LuckyNumberButton;

    @FXML
    private Button TigerAndDragonButton;


    @FXML
    void initialize() {
        //TigerAndDragon open button
        TigerAndDragonButton.setOnAction(event -> {
            openNewScene("DragonTiger.fxml");
        });
        //LuckyNumber open button
        LuckyNumberButton.setOnAction(event -> {
            openNewScene("LuckyNumber.fxml");
        });

    }
    public void openNewScene(String window) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }
    }

