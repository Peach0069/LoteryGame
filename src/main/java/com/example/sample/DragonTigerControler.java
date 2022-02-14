package com.example.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class DragonTigerControler {

    @FXML
    private ImageView DragonImageView;

    @FXML
    private ImageView TigerImageView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Balance;

    @FXML
    private TextField BetField;

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


    public int TigerNumber, TieNumber, DragonNumber;
    boolean BetOnTiger, BetOnTie, BetOnDragon;

    @FXML

    void initialize() {


        TigerBet.setOnAction(event -> {
            Randomize();
            BetOnTiger = true;
        });

        TieBet.setOnAction(event -> {
            Randomize();
            BetOnTie = true;
        });

        DragonBet.setOnAction(event -> {
            Randomize();
            BetOnDragon = true;
        });


    }

    Random random = new Random();
    int colors = random.nextInt(4);
    int numbers = random.nextInt(13);
    int TigerValue, DragonValue;



    int xt,yt;// For tiger
    int xd,yd; //For Dragon

    public void Randomize(){
        Random random = new Random();
        xt= random.nextInt(4);
        yt= random.nextInt(13);
        xd= random.nextInt(4);
        yd= random.nextInt(13);
        xt++; //1-4  Random for Tiger and Dragon
        yt++;//1-13
        xd++; //1-4
        yd++;//1-13


            //Randomize for Tiger
        InputStream streamT = null;
        try {
            streamT = new FileInputStream("D:\\Sample\\src\\main\\java\\cards\\"+yt+"_"+xt+".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image imageT = new Image(streamT);



        //Randomize for dragon

        InputStream streamD = null;
        try {
            streamD = new FileInputStream("D:\\Sample\\src\\main\\java\\cards\\"+yd+"_"+xd+".PNG");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image imageD = new Image(streamD);



        DragonImageView.setImage(imageD);
        TigerImageView.setImage(imageT);

    }
}







