package pizzeria_ui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    }
}