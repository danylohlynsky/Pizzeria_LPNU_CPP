package com.example.pizzeria_ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pizzeria.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private TableView<String> table;
//    @FXML
//    private TableColumn<Order, String> customerColumn;
    @FXML
    private TableColumn<Order, String> orderColumn;
//    @FXML
//    private TableColumn<?> stateColumn;


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
        cookMode.getItems().addAll("Fullstack", "Team");
        cookMode.setValue("Fullstack");

        orderColumn = new TableColumn<>("Order");

        List<Order> orders = getOrderList();
        List<String> pizzaTitlesList = orders.stream()
                .map(Order::getPizzas)
                .flatMap(List::stream)
                .map(Pizza::getPizzaSettings)
                .map(PizzaSettings::getTitle)
                .toList();

        String pizzaTitles = String.join(", ", pizzaTitlesList);
        table.getItems().addAll(pizzaTitlesList);
    }

    ObservableList<Order> getOrderList() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.add(new Order(
                List.of(
                        new Pizza(new PizzaSettings("TestPizza 1", 30)),
                        new Pizza(new PizzaSettings("TestPizza 2", 20))
                )
        ));
                orders.add(new Order(
                List.of(
                        new Pizza(new PizzaSettings("TestPizza 3", 50)),
                        new Pizza(new PizzaSettings("TestPizza 4", 40))
                )
        )

        );
        return orders;
    }
}