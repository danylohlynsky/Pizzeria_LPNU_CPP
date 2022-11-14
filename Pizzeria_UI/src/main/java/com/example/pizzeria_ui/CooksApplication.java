package com.example.pizzeria_ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzeria.entity.Pizzeria;
import pizzeria.entity.cooks.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

enum AllCookStates {FULLSTACK_READY, FULLSTACK_BREAK, FULLSTACK_BUSY,
                    DOUGH_READY, DOUGH_BREAK, DOUGH_BUSY,
    TOPPING_READY,TOPPING_BREAK, TOPPING_BUSY,
     BAKER_READY, BAKER_BREAK, BAKER_BUSY}
public class CooksApplication {

    private HashMap<AllCookStates, Image> images;
    @FXML
    private GridPane gridPane;

    public CooksApplication(int cooksAmount, int cookMode) throws IOException {
        gridPane = new GridPane();
        setGridSize(800, 400);
        this.images = new HashMap<>();
        setImages(cooksAmount);
        initialize(cookMode, cooksAmount);
    }

    private void setImages(int cooksAmount) throws IOException {
        double h = gridPane.getMaxHeight();
        double w = gridPane.getMaxWidth();
        double imgWidth = cooksAmount <= 5 ? w/cooksAmount : w/5;
        double imgHeight = cooksAmount <= 5 ? h : h/2;

        images.put(AllCookStates.FULLSTACK_READY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/fullstack_ready.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.FULLSTACK_BUSY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/fullstack_busy.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.FULLSTACK_BREAK, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/fullstack_break.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.DOUGH_READY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.DOUGH_BREAK, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.DOUGH_BUSY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/dough_busy.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.TOPPING_BUSY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/topping_busy.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.TOPPING_READY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.TOPPING_BREAK, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.BAKER_BREAK, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/baker_busy.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.BAKER_READY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/baker_ready.png"),
                imgWidth, imgHeight, true, true));
        images.put(AllCookStates.BAKER_BUSY, new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/baker_break.png"),
                imgWidth, imgHeight, true, true));
    }

    private void setGridSize(int w, int h){
        gridPane.setMinSize(w, h);
        gridPane.setMaxSize(w, h);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
    }


    public void start(Stage stage) throws IOException {

            Scene scene = new Scene(gridPane);
            stage.setTitle("Pizzeria");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while(true) {
//                        updateCooks();
//
//                    }
//                }
//            }).start();

    }

    public void updateCooks(){

        var cooks = Pizzeria.getInstance().getCooks();
        int j = 0;
        int k = 0;

        for(Cook cook : cooks ){

            var c = cook.getClass();

            var image = new ImageView();
            if (c == FullStackCook.class) {
                if(cook.getCookState() == CookState.AVAILABLE){
                    image = new ImageView(images.get(AllCookStates.FULLSTACK_READY));
                } else if(cook.getCookState() == CookState.BUSY){
                    image = new ImageView(images.get(AllCookStates.FULLSTACK_BUSY));
                }else if(cook.getCookState() == CookState.OUT){
                    image = new ImageView(images.get(AllCookStates.FULLSTACK_BREAK));
                }
            }else if (c == DoughCook.class) {
                if(cook.getCookState() == CookState.AVAILABLE){
                    image = new ImageView(images.get(AllCookStates.DOUGH_BUSY));
                } else if(cook.getCookState() == CookState.BUSY){
                    image = new ImageView(images.get(AllCookStates.DOUGH_BUSY));
                }else if(cook.getCookState() == CookState.OUT){
                    image = new ImageView(images.get(AllCookStates.DOUGH_BREAK));
                }
            }else if (c == ToppingCook.class) {
                if(cook.getCookState() == CookState.AVAILABLE){
                    image = new ImageView(images.get(AllCookStates.TOPPING_READY));
                } else if(cook.getCookState() == CookState.BUSY){
                    image = new ImageView(images.get(AllCookStates.TOPPING_BUSY));
                }else if(cook.getCookState() == CookState.OUT){
                    image = new ImageView(images.get(AllCookStates.TOPPING_BREAK));
                }
            }else if (c == BakerCook.class) {
                if(cook.getCookState() == CookState.AVAILABLE){
                    image = new ImageView(images.get(AllCookStates.BAKER_READY));
                } else if(cook.getCookState() == CookState.BUSY){
                    image = new ImageView(images.get(AllCookStates.BAKER_BUSY));
                }else if(cook.getCookState() == CookState.OUT){
                    image = new ImageView(images.get(AllCookStates.BAKER_BREAK));
                }
            }

            gridPane.add(image, j, k, 1, 1);
            j++;

            if(j == 5){
                    k = 1;
                    j = 0;
                }
        }
    }

    public void initialize(int cookMode, int cooksAmount){
        int minSecondsForPizza = 1;
        int differentPizzaAmount = 3;
        int tablesAmount = 4;
        int cashiersAmount = 4;

        Pizzeria.getInstance().start(minSecondsForPizza, differentPizzaAmount, tablesAmount, cashiersAmount, cookMode,
                cooksAmount);
    }
 

}