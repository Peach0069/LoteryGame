package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
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
import javafx.stage.Stage;

public class LuckyNumberController {

    User user;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField BetField;

    @FXML
    private Label balance;

    @FXML
    private Button playButton;

    @FXML
    private Button BackButton;

    @FXML
    private ImageView firstNumber;

    @FXML
    private ImageView secondNumber;

    @FXML
    private ImageView thirdNumber;
    Stage stage;

    public LuckyNumberController(User user) {
        this.user = user;
    }

    @FXML
    void initialize() {
        this.balance.setText(String.valueOf(user.getBalance()));

        playButton.setOnAction(event -> {
            String str = (BetField.getText());
            int bet = Integer.parseInt(str);
            if (bet <= user.getBalance() && bet != 0) {
                try {
                    luckyNumberRandom(bet);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                openNewScene("DialogMessage.fxml");
                System.out.println("no money");
            }
        });
        BackButton.setOnAction(actionEvent -> {
            stage = (Stage) BackButton.getScene().getWindow();
            stage.close();
            openNewScenelobby("app.fxml");
        });
    }

    public void luckyNumberRandom(int bet) throws FileNotFoundException {
        Random random = new Random();
        int x1 = random.nextInt(9)+1;
        int x2 = random.nextInt(9)+1;
        int x3 = random.nextInt(9)+1;
        //ball 1
        InputStream stream1 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x1 + ".PNG");
        Image image1 = new Image(stream1);

        //ball 2
        InputStream stream2 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x2 + ".PNG");
        Image image2 = new Image(stream2);

        //ball 3
        InputStream stream3 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x3 + ".PNG");
        Image image3 = new Image(stream3);

        firstNumber.setImage(image1);
        secondNumber.setImage(image2);
        thirdNumber.setImage(image3);

        if (x1 == x2 && x1 == x3) {//check if three are equal
            user.setBalance(user.getBalance() + (bet * 2));
            System.out.println("you win x2 1=2");
            this.balance.setText(String.valueOf(user.getBalance()));
            saveuser();
        } else if (x1 == x2 || x1 == x3 || x2 == x3) {//check if a pairs are equal
            user.setBalance(user.getBalance() + (bet * 2));
            System.out.println("you win x2 ");
            this.balance.setText(String.valueOf(user.getBalance()));
            saveuser();
        }   else {
            user.setBalance(user.getBalance() - bet);
            System.out.println("you lose");
            this.balance.setText(String.valueOf(user.getBalance()));
            saveuser();
        }
    }

    private void saveuser() {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.saveuser(user);
    }

    public void openNewScenelobby(String window) {
        BackButton.getScene().getWindow().hide();

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
        stage.showAndWait();
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