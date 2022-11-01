package pizzeria.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PizzeriaManager {
    private Pizzeria pizzeria =  Pizzeria.getInstance();

    public void generateClient() {
        if (pizzeria.isOpen() && pizzeria.getTables().stream().anyMatch(Table::isAvailable)) {
            Table table = pizzeria.getTables().stream().filter(Table::isAvailable).findFirst().get();
            table.setAvailable(false);
            pizzeria.getCustomers().add(new Customer(table, generateOrder()));
        }
    }

    private Order generateOrder() {
        int pizzasAmount = new Random().nextInt(1, 5);
        List<Pizza> pizzas = new ArrayList<>();
        for (int i = 0; i < pizzasAmount; i++) {
            pizzas.add(new Pizza(pizzeria.getMenu().get(new Random().nextInt(pizzeria.getMenu().size()))));
        }
        return new Order(pizzas);
    }
}
