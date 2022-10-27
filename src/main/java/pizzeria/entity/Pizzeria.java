package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;
import pizzeria.entity.cooks.Cook;
import pizzeria.entity.cooks.CookVersion;

import java.util.List;

@Getter
@Setter
public class Pizzeria {
    private List<Cook> cooks;
    private List<Table> tables;
    private List<Customer> customers;
    private List<Order> queue;
    private List<Cashier> cashiers;

    private List<PizzaSettings> menu;

    private boolean isOpen;

    private CookVersion cookVersion;


    private int minSecondsForPizza;
    private int differentPizzaAmount;
}
