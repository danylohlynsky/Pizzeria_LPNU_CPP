package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;
import pizzeria.entity.cooks.*;

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

    //default values
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

    //should be changed
    public static Pizzeria getInstance() {
        if (instance == null) {
            instance = new Pizzeria();
        }
        return instance;
    }


//    private Pizzeria(List<Cook> cooks,List<Cashier> cashiers,
//                     List<Table> tables, List<Customer> customers,
//                     List<Order> queue, List<PizzaSettings> menu,
//                     boolean isOpen, CookVersion cookVersion,
//                     int minSecondsForPizza, int differentPizzaAmount) {
//        this.cooks = cooks;
//        this.cashiers = cashiers;
//        this.tables = tables;
//        this.customers = customers;
//        this.queue = queue;
//        this.menu = menu;
//        this.isOpen = isOpen;
//        this.cookVersion = cookVersion;
//        this.minSecondsForPizza = minSecondsForPizza;
//        this.differentPizzaAmount = differentPizzaAmount;
//    }
//
//
//
//    public static Pizzeria getInstance(List<Cook> cooks, List<Cashier> cashiers, List<Table> tables,
//                                       List<Customer> customers, List<Order> queue,
//                                       List<PizzaSettings> menu, boolean isOpen,
//                                       CookVersion cookVersion, int minSecondsForPizza,
//                                       int differentPizzaAmount) {
//        if (instance == null) {
//            instance = new Pizzeria(cooks,cashiers, tables, customers, queue, menu,
//                    isOpen, cookVersion, minSecondsForPizza, differentPizzaAmount);
//        }
//        return instance;
//    }


}
