package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;
import pizzeria.entity.cooks.Cook;
import pizzeria.entity.cooks.CookVersion;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pizzeria {
    private static Pizzeria instance;

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

    // default values
    private Pizzeria() {
        this.cooks = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.isOpen = true;
        this.cookVersion = CookVersion.PART_MAKING;
        this.minSecondsForPizza = 10;
        this.differentPizzaAmount = 10;
    }

    public static Pizzeria getInstance() {
        if (instance == null) {
            instance = new Pizzeria();
        }
        return instance;
    }

}
