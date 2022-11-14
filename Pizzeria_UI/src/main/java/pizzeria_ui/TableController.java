package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pizzeria.entity.Pizzeria;
import pizzeria.entity.Table;

import java.io.IOException;
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

    List<Map<ImageView, Table>> imageViews = new ArrayList<>();

    // List<ImageView> queue;


    @FXML
    public void initialize() {

        List<Table> testTables = new ArrayList<>();
        testTables.add(new Table(true));
        testTables.add(new Table(true));
        testTables.add(new Table(true));
        testTables.add(new Table(true));
        testTables.add(new Table(true));
        testTables.add(new Table(true));
        testTables.add(new Table(false));
        testTables.add(new Table(false));
        testTables.add(new Table(false));
        testTables.add(new Table(false));
        testTables.add(new Table(false));
        testTables.add(new Table(false));
        Pizzeria.getInstance().setTables(testTables);


        emptyTable = new Image("empty_table.jpg");
        eatingTable = new Image("eating_table.jpeg");
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
    }
    public void draw() {
        List<Table> tables = Pizzeria.getInstance().getTables();

        for (int i = 0; i < imageViews.size(); i++) {
            Map<ImageView, Table> viewsTables = imageViews.get(i);
            for(var viewTable : viewsTables.entrySet()) {
                if (viewTable.getValue().isAvailable()) {
                    viewTable.getKey().setImage(emptyTable);
                } else {
                    viewTable.getKey().setImage(eatingTable);
                }
            }
        }
    }
}
