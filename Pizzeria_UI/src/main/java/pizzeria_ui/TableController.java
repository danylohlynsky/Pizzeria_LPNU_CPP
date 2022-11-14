package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Pizzeria;
import pizzeria.entity.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableController {
    private Pizzeria pizzeria = Pizzeria.getInstance();

    @FXML
    private GridPane tablesGrid;

    private Image emptyTable;
    private Image eatingTable;
    private Image waitingTable;

    List<Map<ImageView, Table>> imageViews = new ArrayList<>();

    @FXML
    public void initialize() {
        emptyTable = new Image("table_empty.jpg");
        eatingTable = new Image("table_eating.jpg");
        waitingTable = new Image("table_waiting.jpg");
        int sizeWidth = (int) tablesGrid.getPrefWidth();
        int sizeHeight = (int) tablesGrid.getPrefHeight();
        int tileWidth = sizeWidth / tablesGrid.getColumnCount();
        int tileHeight = sizeHeight / tablesGrid.getRowCount();
        int tileSize = Math.min(tileWidth, tileHeight);

        int columnCount = tablesGrid.getColumnCount();
        int rowCount = tablesGrid.getRowCount();

        int tablesCounter = 0;
        int tablesAmount = pizzeria.getTables().size();
        for (int i = 0; i < columnCount; i++) {
            Map<ImageView, Table> column = new HashMap<>();

            for (int j = 0; j < rowCount; j++) {
                if (tablesAmount <= tablesCounter) {
                    break;
                }

                ImageView imageView = new ImageView();
                imageView.setFitHeight(tileSize);
                imageView.setFitWidth(tileSize);
                tablesGrid.add(imageView, i, j);
                column.put(imageView, Pizzeria.getInstance().getTables().get(tablesCounter));
                tablesCounter++;
            }
            imageViews.add(column);
            tablesGrid.getColumnConstraints().get(i).setPrefWidth(tileSize);
        }

        for (int i = 0; i < rowCount; i++) {
            tablesGrid.getRowConstraints().get(i).setPercentHeight(tileSize);
        }
        draw();
        update();
    }

    public void update() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                draw();
            }
        }).start();
    }

    public void draw() {
        for (Map<ImageView, Table> viewsTables : imageViews) {
            for (var viewTable : viewsTables.entrySet()) {
                switch (viewTable.getValue().getTableState()) {
                case EMPTY -> viewTable.getKey().setImage(emptyTable);
                case CUSTOMER_EATING -> viewTable.getKey().setImage(eatingTable);
                case CUSTOMER_WAITING -> viewTable.getKey().setImage(waitingTable);
                }
            }
        }
    }
}
