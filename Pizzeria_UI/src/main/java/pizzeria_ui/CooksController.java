package pizzeria_ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Pizzeria;
import pizzeria.entity.cooks.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CooksController {
    private HashMap<AllCookStates, Image> images;
    private List<List<ImageView>> imageViews;
    private final GridPane gridPane;

    public CooksController(GridPane gridPane) {
        this.gridPane = gridPane;
        init();
    }

    private void setImages() {

        try {
            images.put(AllCookStates.NONE,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/empty.jpg")));
            images.put(AllCookStates.FULLSTACK_READY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png")));
            images.put(AllCookStates.FULLSTACK_BUSY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/fullstack_busy.png")));
            images.put(AllCookStates.FULLSTACK_BREAK,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png")));
            images.put(AllCookStates.DOUGH_READY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png")));
            images.put(AllCookStates.DOUGH_BREAK,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png")));
            images.put(AllCookStates.DOUGH_BUSY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/dough_busy.png")));
            images.put(AllCookStates.TOPPING_BUSY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/topping_busy.png")));
            images.put(AllCookStates.TOPPING_READY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png")));
            images.put(AllCookStates.TOPPING_BREAK,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png")));
            images.put(AllCookStates.BAKER_BREAK,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/break.png")));
            images.put(AllCookStates.BAKER_READY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/ready.png")));
            images.put(AllCookStates.BAKER_BUSY,
                    new Image(new FileInputStream("Pizzeria_UI/src/main/resources/images/baker_busy.png")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        var cooks = Pizzeria.getInstance().getCooks();
        int j = 0;
        int k = 0;

        for (Cook cook : cooks) {

            var c = cook.getClass();

            Image image = null;
            if (c == FullStackCook.class) {
                if (cook.getCookState() == CookState.AVAILABLE) {
                    image = images.get(AllCookStates.FULLSTACK_READY);
                } else if (cook.getCookState() == CookState.BUSY) {
                    image = images.get(AllCookStates.FULLSTACK_BUSY);
                } else if (cook.getCookState() == CookState.OUT) {
                    image = images.get(AllCookStates.FULLSTACK_BREAK);
                }
            } else if (c == DoughCook.class) {
                if (cook.getCookState() == CookState.AVAILABLE) {
                    image = images.get(AllCookStates.DOUGH_BUSY);
                } else if (cook.getCookState() == CookState.BUSY) {
                    image = images.get(AllCookStates.DOUGH_BUSY);
                } else if (cook.getCookState() == CookState.OUT) {
                    image = images.get(AllCookStates.DOUGH_BREAK);
                }
            } else if (c == ToppingCook.class) {
                if (cook.getCookState() == CookState.AVAILABLE) {
                    image = images.get(AllCookStates.TOPPING_READY);
                } else if (cook.getCookState() == CookState.BUSY) {
                    image = images.get(AllCookStates.TOPPING_BUSY);
                } else if (cook.getCookState() == CookState.OUT) {
                    image = images.get(AllCookStates.TOPPING_BREAK);
                }
            } else if (c == BakerCook.class) {
                if (cook.getCookState() == CookState.AVAILABLE) {
                    image = images.get(AllCookStates.BAKER_READY);
                } else if (cook.getCookState() == CookState.BUSY) {
                    image = images.get(AllCookStates.BAKER_BUSY);
                } else if (cook.getCookState() == CookState.OUT) {
                    image = images.get(AllCookStates.BAKER_BREAK);
                }
            }

            imageViews.get(k).get(j).setImage(image);
            j++;

            if (j == 5) {
                k = 1;
                j = 0;
            }
        }
    }

    public void init() {
        this.images = new HashMap<>();
        setImages();

        imageViews = new ArrayList<>();

        int height = 500 / 2;
        int width = 500 / 5;
        int size = Math.min(height, width);
        for (int i = 0; i < 2; i++) {
            gridPane.getRowConstraints().get(i).setPrefHeight(size);

            List<ImageView> views = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                var view = new ImageView(images.get(AllCookStates.NONE));
                view.setFitHeight(size);
                view.setFitWidth(size);

                gridPane.add(view, j, i);
                views.add(view);
            }
            imageViews.add(views);
        }

        for (int i = 0; i < 5; i++) {
            gridPane.getColumnConstraints().get(i).setPrefWidth(size);

        }
    }

    public enum AllCookStates {
        NONE, FULLSTACK_READY, FULLSTACK_BREAK, FULLSTACK_BUSY, DOUGH_READY, DOUGH_BREAK, DOUGH_BUSY, TOPPING_READY,
        TOPPING_BREAK, TOPPING_BUSY, BAKER_READY, BAKER_BREAK, BAKER_BUSY
    }
}