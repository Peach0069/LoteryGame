package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import static com.example.sample.Player.*;


public class DragonTigerControler {

    User user;

    @FXML
    private TextField betField;

    @FXML
    private ImageView DragonImageView;

    @FXML
    private ImageView TigerImageView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label balance;

    @FXML
    private AnchorPane DragonCard;

    @FXML
    private AnchorPane TigerCard;

    @FXML
    private Button DragonBet;

    @FXML
    private Button TieBet;

    @FXML
    private Button TigerBet;

    @FXML
    private Button backButton;

    public DragonTigerControler(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        this.balance.setText(String.valueOf(user.getBalance()));

        TigerBet.setOnAction(event -> justPlay(TIGER));
        TieBet.setOnAction(event -> justPlay(TIE));
        DragonBet.setOnAction(event -> justPlay(DRAGON));

        backButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
            openNewScenelobby("app.fxml");
        });
    }

    private void justPlay(Player player) {
        String str = (betField.getText());
        if (str.isBlank()) { // check if field is blank
            System.out.println("error");
        } else {
            int bet = Integer.parseInt(str);
            if (bet < user.getBalance()) {
                Randomize(bet, player);
            } else {
                openNewScene("DialogMessage.fxml");
            }
        }
    }

    public void Randomize(int bet, Player player) {
        Random random = new Random();
        int xt = random.nextInt(4) + 1;
        int yt = random.nextInt(13) + 1;
        int xd = random.nextInt(4) + 1;
        int yd = random.nextInt(13) + 1;

        //Randomize for Tiger
        InputStream streamT = null;
        try {
            streamT = new FileInputStream("D:\\Sample\\src\\main\\java\\cards\\" + yt + "_" + xt + ".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image imageT = new Image(streamT);
        TigerImageView.setImage(imageT);

        //Randomize for dragon
        InputStream streamD = null;
        try {
            streamD = new FileInputStream("D:\\Sample\\src\\main\\java\\cards\\" + yd + "_" + xd + ".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image imageD = new Image(streamD);
        DragonImageView.setImage(imageD);

        if (yt == yd && xd == xt && player == TIE) {
            user.setBalance(user.getBalance() + (bet * 50)); //set win
            System.out.println("It's extra tie x50");
        } else if (yt < yd && player == DRAGON) {
            user.setBalance(user.getBalance() + (bet * 2)); //set win
            System.out.println("Dragon Wins x2");
        } else if (yt == yd && player == TIE) {
            user.setBalance(user.getBalance() + (bet * 11)); //set win
            System.out.println("It's tie x11");
        } else if (yt > yd && player == TIGER) {
            user.setBalance(user.getBalance() + (bet * 2)); //set win
            System.out.println("Tiger Wins x2");
        } else {
            user.setBalance(user.getBalance() - bet); //set win
            System.out.println("You lose");
        }
        this.balance.setText(String.valueOf(user.getBalance()));//show balance in game
        saveuser(); //save data
    }

    private void saveuser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.saveuser(user);
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

    public void openNewScenelobby(String window) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            HomeController homeController = new HomeController(user);
            loader.setController(homeController);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}







