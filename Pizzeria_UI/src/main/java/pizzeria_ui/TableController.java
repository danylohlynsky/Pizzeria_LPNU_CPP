package pizzeria_ui;

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
    private final GridPane tablesGrid;
    private final List<Map<ImageView, Table>> imageViews = new ArrayList<>();
    private Image emptyTable;
    private Image eatingTable;
    private Image waitingTable;

    public TableController(GridPane tablesGrid) {
        this.tablesGrid = tablesGrid;
    }

    public void init() {
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
        int tablesAmount = Pizzeria.getInstance().getTables().size();
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
    }

    public void update() {
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
