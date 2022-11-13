package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Cashier;
import pizzeria.entity.Pizzeria;

import java.util.ArrayList;
import java.util.List;

public class QueueController {
    @FXML
    private GridPane queueGrid;
    private Image clientImage;
    private Image emptyClientImage;
    private Image cashierImage;
    private Image emptyCashierImage;
    List<List<ImageView>> queues;

    @FXML
    public void initialize() {
        clientImage = new Image("client.png");
        emptyClientImage = new Image("empty_client.png");
        cashierImage = new Image("cashier.png");
        emptyCashierImage = new Image("empty_cashier.png");

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

        Pizzeria pizzeria = Pizzeria.getInstance();
        Pizzeria.getInstance().start(2, 3, 14, 3, 0, 3);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                draw(pizzeria.getCashiers());
            }
        }).start();
    }

    public void update() {
        draw(Pizzeria.getInstance().getCashiers());
    }

    private void draw(List<Cashier> cashiers) {
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