package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import static com.example.sample.Player.*;


public class DragonTigerControler {
    User user;

    @FXML
    private Label winnerLabel;

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

    @FXML
    private Label winnerLose;

    @FXML
    private Label halfLabel;

    public DragonTigerControler(User user) {
        this.user = user;
    }

    public void alertError(String error, String mes) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(mes);
        alert.showAndWait().get();
    }

    public static boolean isNumeric(String str) throws IllegalArgumentException { //Check if in field is a number
        boolean isNumber = false;
        if (str != null && !str.equals("")) {
            isNumber = true;
            char chars[] = str.toCharArray();
            for (int d = 0; d < chars.length; d++) {
                isNumber &= Character.isDigit(chars[d]);
                if (!isNumber)
                    break;
            }
        }
        return isNumber;
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
            alertError("Error", "You field is empty");
        } else if (!isNumeric(str)) {
            alertError("", "Something goes wrong");
        } else {
            int bet = Integer.parseInt(str);
            if (bet == 0) {
                alertError("", "You can't bet with this value");
            } else if (bet <= user.getBalance()) {
                Randomize(bet, player);
            } else {
                alertError("Insufficient money", "You don't have enough money to do this operation");//don't have enough money
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
            this.winnerLose.setText("EXTRA WIN x15!");
            user.setBalance(user.getBalance() + (bet * 15)); //set win
        } else if (yt < yd && player == DRAGON) {//Dragon Win
            this.winnerLose.setText("YOU WIN! x2");
            user.setBalance(user.getBalance() + bet ); //set win
        } else if (yt == yd && player == TIE) {//TIE
            this.winnerLose.setText("YOU WIN! x6");
            user.setBalance(user.getBalance() + bet * 5); //set win
        } else if (yt > yd && player == TIGER) {//Tiger Win
            this.winnerLose.setText("YOU WIN! x2");
            user.setBalance(user.getBalance() + bet ); //set win
        } else if(yt == yd && player == DRAGON) { //Refund bet/2 if bet on DRAGON AND IT'S TIE
            this.winnerLose.setText("Refund half of bet");
            user.setBalance(user.getBalance() - (bet/2));
        }else if(yt == yd && player == TIGER) { //Refund bet/2 if bet on TIGER AND IT'S TIE
            this.halfLabel.setText("Refund half of bet");
            user.setBalance(user.getBalance() - (bet/2));
        }else {
            this.winnerLose.setText("YOU LOSE!");
            user.setBalance(user.getBalance() - bet); //set win
        } if (yt < yd){
            this.winnerLabel.setText("DRAGON WINS!");
        } else if (yt > yd){
            this.winnerLabel.setText("TIGER WINS!");
        } else if (yt == yd){
            this.winnerLabel.setText("IT'S TIE!");
        } else if (yt == yd && xd == xt) {
            this.winnerLabel.setText("IT'S EXTRA TIE!");
        }
        this.balance.setText(String.valueOf(user.getBalance()));//show balance in game
        saveuser(); //save data
    }


    private void saveuser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.saveuser(user);
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
        stage.setResizable(false);
        stage.showAndWait();
    }

}







