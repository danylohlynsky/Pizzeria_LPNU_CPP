package pizzeria_ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Cashier;
import pizzeria.entity.Pizzeria;

import java.util.ArrayList;
import java.util.List;

public class QueueController {
    private final GridPane queueGrid;
    private Image clientImage;
    private Image emptyClientImage;
    private Image cashierImage;
    private Image emptyCashierImage;
    private List<List<ImageView>> queues;

    public QueueController(GridPane queueGrid) {
        this.queueGrid = queueGrid;
        init();
    }

    private void init() {
        clientImage = new Image("client.jpg");
        emptyClientImage = new Image("empty_client.jpg");
        cashierImage = new Image("cashier.jpg");
        emptyCashierImage = new Image("empty_cashier.jpg");

        int sizeWidth = (int) queueGrid.getPrefWidth();
        int sizeHeight = (int) queueGrid.getPrefHeight();
        int tileWidth = sizeWidth / queueGrid.getColumnCount();
        int tileHeight = sizeHeight / queueGrid.getRowCount();
        int tileSize = Math.min(tileWidth, tileHeight);

        queues = new ArrayList<>();
        for (int i = 0; i < queueGrid.getColumnCount(); i++) {
            List<ImageView> column = new ArrayList<>();

            for (int j = 0; j < queueGrid.getRowCount(); j++) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(tileSize);
                imageView.setFitWidth(tileSize);
                queueGrid.add(imageView, i, j);
                column.add(imageView);
            }

            queues.add(column);
            queueGrid.getColumnConstraints().get(i).setPrefWidth(tileSize);
        }

        for (int i = 0; i < queueGrid.getRowCount(); i++) {
            queueGrid.getRowConstraints().get(i).setPercentHeight(tileSize);
        }
    }

    public void update() {
        List<Cashier> cashiers = Pizzeria.getInstance().getCashiers();

        for (int i = 0; i < queues.size(); i++) {
            List<ImageView> queue = queues.get(i);
            int customersInQueue = 0;

            if (cashiers.size() > i) {
                customersInQueue = cashiers.get(i).getCustomersAmount();
                queue.get(0).setImage(cashierImage);
            } else {
                queue.get(0).setImage(emptyCashierImage);
            }

            for (int j = 1; j < queue.size(); j++) {
                if (j < customersInQueue + 1) {
                    queue.get(j).setImage(clientImage);
                } else {
                    queue.get(j).setImage(emptyClientImage);
                }
            }
        }
    }
}