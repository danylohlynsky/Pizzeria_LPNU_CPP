package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import pizzeria.entity.Order;
import pizzeria.entity.Pizzeria;

import java.util.Objects;

public class Configuration {
    @FXML
    private Spinner<Integer> minSecondsForPizza;
    @FXML
    private Spinner<Integer> differentPizzaAmount;
    @FXML
    private Spinner<Integer> tablesAmount;
    @FXML
    private Spinner<Integer> cashiersAmount;
    @FXML
    private Spinner<Integer> cooksAmount;
    @FXML
    private ChoiceBox<String> cookMode;
    @FXML
    private GridPane queueGrid;
    @FXML
    private TableView<Order> table;
    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane tablesGrid;

    private TableController tableController;

    @FXML
    protected void onStartClick() {
        int minSecondsForPizza = this.minSecondsForPizza.getValue();
        int differentPizzaAmount = this.differentPizzaAmount.getValue();
        int tablesAmount = this.tablesAmount.getValue();
        int cashiersAmount = this.cashiersAmount.getValue();
        int cookMode = Objects.equals(this.cookMode.getValue(), "Fullstack") ? 0 : 1;
        int cooksAmount = this.cooksAmount.getValue();

        Pizzeria.getInstance().start(minSecondsForPizza, differentPizzaAmount, tablesAmount, cashiersAmount, cookMode,
                cooksAmount);
        tableController.init();
    }

    public void initPanel() {
        minSecondsForPizza.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        differentPizzaAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        tablesAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 21));
        cashiersAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4));
        cooksAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        cookMode.getItems().addAll("Team", "Fullstack");
        cookMode.setValue("Fullstack");
    }

    @FXML
    public void initialize() {
        initPanel();
        QueueController queueController = new QueueController(queueGrid);
        OrderListController orderListController = new OrderListController(table);
        CooksController cooksController = new CooksController(gridPane);
        tableController = new TableController(tablesGrid);

        new Thread(() -> {
            while (true) {
                queueController.update();
                orderListController.update();
                cooksController.update();
                tableController.update();
            }
        }).start();
    }
}