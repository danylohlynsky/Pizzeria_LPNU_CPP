package pizzeria.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PizzeriaManager {
    private final Pizzeria pizzeria = Pizzeria.getInstance();

    public void generateClient() {
        while (true) {
            if (pizzeria.isOpen() && pizzeria.getTables().stream()
                    .anyMatch(table -> table.getTableState().equals(TableState.EMPTY))) {
                Table table = pizzeria.getTables().stream().filter(t -> t.getTableState().equals(TableState.EMPTY))
                        .findFirst().get();
                table.setTableState(TableState.CUSTOMER_WAITING);
                Customer customer = new Customer(table, generateOrder());
                customer.chooseCashier(pizzeria.getCashiers());
                customer.getOrder().setCustomer(customer);
                pizzeria.getCustomers().add(customer);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Order generateOrder() {
        int pizzasAmount = new Random().nextInt(1, pizzeria.getDifferentPizzaAmount() + 1);
        List<Pizza> pizzas = new ArrayList<>();
        for (int i = 0; i < pizzasAmount; i++) {
            pizzas.add(new Pizza(pizzeria.getMenu().get(new Random().nextInt(pizzeria.getMenu().size()))));
        }
        return new Order(pizzas);
    }
}
