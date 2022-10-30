package pizzeria;

import pizzeria.entity.*;
import pizzeria.entity.cooks.BakerCook;
import pizzeria.entity.cooks.DoughCook;
import pizzeria.entity.cooks.ToppingCook;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pizzeria pizzeria = Pizzeria.getInstance();
        pizzeria.getCooks().add(new BakerCook());
        pizzeria.getCooks().add(new BakerCook());
        pizzeria.getCooks().add(new ToppingCook());
        pizzeria.getCooks().add(new ToppingCook());
        pizzeria.getCooks().add(new DoughCook());
        pizzeria.getCooks().add(new DoughCook());

        // should be changed with creating Pizzeria settings functionality
        int cashiersAmount = 2;

        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < cashiersAmount; i++) {

            // test objects
            List<Customer> customers = new ArrayList<>(List.of(
                    new Customer(new Table(true),
                            new Order(List.of(new Pizza(new PizzaSettings("First settings", 10)),
                                    new Pizza(new PizzaSettings("Second settings", 5))))),
                    new Customer(new Table(true), new Order(List.of(new Pizza(new PizzaSettings("First settings", 10)),
                            new Pizza(new PizzaSettings("Second settings", 5)))))));

            cashiers.add(new Cashier(customers));
        }
        cashiers.forEach(Cashier::start);
    }
}