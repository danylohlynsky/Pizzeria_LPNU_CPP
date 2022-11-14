package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pizzeria.entity.Order;
import pizzeria.entity.Pizzeria;

import java.util.List;
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
    private TableView<Order> table;


    @FXML
    protected void onStartClick() {
        int minSecondsForPizza = this.minSecondsForPizza.getValue();
        int differentPizzaAmount = this.differentPizzaAmount.getValue();
        int tablesAmount = this.tablesAmount.getValue();
        int cashiersAmount = this.cashiersAmount.getValue();
        int cookMode = Objects.equals(this.cookMode.getValue(), "Fullstack") ? 1 : 0;
        int cooksAmount = this.cooksAmount.getValue();

        Pizzeria.getInstance().start(minSecondsForPizza, differentPizzaAmount, tablesAmount, cashiersAmount, cookMode,
                cooksAmount);
    }

    @FXML
    public void initialize() {
        minSecondsForPizza.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        differentPizzaAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        tablesAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        cashiersAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        cooksAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10));
        cookMode.getItems().addAll("Team", "Fullstack");
        cookMode.setValue("Fullstack");
        Pizzeria pizzeria = Pizzeria.getInstance();

        TableColumn<Order, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Order, String> pizzasColumn = new TableColumn<>("Order");
        pizzasColumn.setCellValueFactory(new PropertyValueFactory<>("order"));

        TableColumn<Order, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        table.getColumns().addAll(customerColumn, pizzasColumn, stateColumn);


        new Thread(() -> {
            while (true) {
                List<Order> orders = pizzeria.getQueue();
                table.getItems().clear();
                table.getItems().addAll(orders);

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}