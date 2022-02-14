package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import Animation.Shake;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class LuckyNumberController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField betAmounthField;

    @FXML
    private Button playButton;

    @FXML
    private ImageView firstNumber;

    @FXML
    private ImageView secondNumber;

    @FXML
    private ImageView thirdNumber;




    @FXML
    void initialize() {


        playButton.setOnAction(event -> {
            String balance;
            String betAmounth = betAmounthField.getText().trim();
            if(betAmounth != ("") && betAmounth != (" ") ){
                LuckyNumberRandom();
                ;
            }else {
                System.out.println("BetField is empty!");
            }

            System.out.println("bet amount is "+betAmounth);
        });


    }

    int x1;
    int x2;
    int x3;

    public void LuckyNumberRandom() {
        Random random = new Random();
        x1 = random.nextInt(9);
        x2 = random.nextInt(9);
        x3 = random.nextInt(9);
        x1++;
        x2++;
        x3++;


        //ball 1
        InputStream stream1 = null;
        try {
            stream1 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x1 + ".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image1 = new Image(stream1);



        //ball 2
        InputStream stream2 = null;
        try {
            stream2 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x2 + ".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image2 = new Image(stream2);


        //ball 3
        InputStream stream3 = null;
        try {
            stream3 = new FileInputStream("D:\\Sample\\src\\main\\java\\Balls\\" + "Biliard" + "_" + x3 + ".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image3 = new Image(stream3);


        firstNumber.setImage(image1);
        secondNumber.setImage(image2);
        thirdNumber.setImage(image3);




    }
}