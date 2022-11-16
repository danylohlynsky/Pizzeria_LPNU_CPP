package pizzeria_ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Pizzeria;
import pizzeria.entity.Table;

import java.util.*;

public class TableController {
    private final GridPane tablesGrid;
    private final List<Map<ImageView, Table>> imageViews = new ArrayList<>();
    private final Image emptyTable;
    private final Image eatingTable;
    private final Image waitingTable;
    private final Image noneTable;
    private final int tileSize;
    private final int columnCount;
    private final int rowCount;

    public TableController(GridPane tablesGrid) {
        this.tablesGrid = tablesGrid;

        emptyTable = new Image("table_empty.jpg");
        eatingTable = new Image("table_eating.jpg");
        waitingTable = new Image("table_waiting.jpg");
        noneTable = new Image("none_table.png");

        int sizeWidth = (int) tablesGrid.getPrefWidth();
        int sizeHeight = (int) tablesGrid.getPrefHeight();
        int tileWidth = sizeWidth / tablesGrid.getColumnCount();
        int tileHeight = sizeHeight / tablesGrid.getRowCount();
        tileSize = Math.min(tileWidth, tileHeight);

        columnCount = tablesGrid.getColumnCount();
        rowCount = tablesGrid.getRowCount();

        for (int i = 0; i < columnCount; i++) {
            Map<ImageView, Table> column = new HashMap<>();

            for (int j = 0; j < rowCount; j++) {
                ImageView imageView = new ImageView();
                imageView.setImage(noneTable);
                imageView.setFitHeight(tileSize);
                imageView.setFitWidth(tileSize);
                tablesGrid.add(imageView, i, j);
                column.put(imageView, null);
            }

            imageViews.add(column);
            tablesGrid.getColumnConstraints().get(i).setPrefWidth(tileSize);
        }

        for (int i = 0; i < rowCount; i++) {
            tablesGrid.getRowConstraints().get(i).setPercentHeight(tileSize);
        }
    }

    public void init() {
        int tablesCounter = 0;
        int tablesAmount = Pizzeria.getInstance().getTables().size();

        for (int i = 0; i < columnCount; i++) {
            Map<ImageView, Table> column = imageViews.get(i);
            Iterator<ImageView> imageViewIterator = column.keySet().iterator();

            for (int j = 0; j < rowCount; j++) {
                if (tablesAmount <= tablesCounter || !imageViewIterator.hasNext()) {
                    break;
                }

                ImageView imageView = imageViewIterator.next();
                imageView.setImage(noneTable);
                imageView.setFitHeight(tileSize);
                imageView.setFitWidth(tileSize);
                column.put(imageView, Pizzeria.getInstance().getTables().get(tablesCounter));
                tablesCounter++;
            }
        }
    }

    public void update() {
        for (Map<ImageView, Table> viewsTables : imageViews) {
            for (var viewTable : viewsTables.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() != null)
                    .toList()) {
                switch (viewTable.getValue().getTableState()) {
                    case EMPTY -> viewTable.getKey().setImage(emptyTable);
                    case CUSTOMER_EATING -> viewTable.getKey().setImage(eatingTable);
                    case CUSTOMER_WAITING -> viewTable.getKey().setImage(waitingTable);
                }
            }
        }
    }
}
