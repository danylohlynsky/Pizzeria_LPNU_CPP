package pizzeria;

import pizzeria.entity.*;
import pizzeria.entity.cooks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TASK 1: CREATING PIZZERIA
        Scanner scanner = new Scanner(System.in);

        PizzeriaManager pizzeriaManager = new PizzeriaManager();
        Pizzeria pizzeria = Pizzeria.getInstance();

        System.out.println("enter min seconds for pizza:");
        int minSecondsForPizza = scanner.nextInt();
        pizzeria.setMinSecondsForPizza(minSecondsForPizza);

        System.out.println("enter different pizza amount:");
        int differentPizzaAmount = scanner.nextInt();
        pizzeria.setDifferentPizzaAmount(differentPizzaAmount);

        System.out.println("enter tables amount:");
        int tablesAmount = scanner.nextInt();
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < tablesAmount; i++) {
            tables.add(new Table(true));
        }
        pizzeria.setTables(tables);

        System.out.println("enter cashier amount:");
        int cashiersAmount = scanner.nextInt();
        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < cashiersAmount; i++) {
            cashiers.add(null);
        }
        pizzeria.setCashiers(cashiers);

        System.out.println("enter cook mode:\n0 - full stack cooks\n1 - not full stack cooks (min. 3)");

        int cookMode = scanner.nextInt();
        System.out.println("enter cooks amount:");
        int cooksAmount = scanner.nextInt();

        if (cookMode == 0) {
            List<Cook> cooks = new ArrayList<>();
            for (int i = 0; i < cooksAmount; i++) {
                cooks.add(new FullStackCook());
            }

            pizzeria.setCooks(cooks);
        }

        else if (cookMode == 1) {
            Random rand = new Random();
            if (cooksAmount >= 3) {
                List<Cook> cooks = new ArrayList<>();

                int bakerCookAmount = rand.nextInt(1, cooksAmount - 1);
                for (int i = 0; i < bakerCookAmount; i++) {
                    cooks.add(new BakerCook());
                }

                // System.out.println(bakerCookAmount);

                int doughCookAmount = rand.nextInt(1, cooksAmount - bakerCookAmount);
                for (int i = 0; i < cooksAmount; i++) {
                    cooks.add(new DoughCook());
                }

                // System.out.println(doughCookAmount);

                int toppingCookAmount = cooksAmount - bakerCookAmount - doughCookAmount;
                List<Cook> toppingCooks = new ArrayList<>();
                for (int i = 0; i < cooksAmount; i++) {
                    cooks.add(new ToppingCook());
                }

                // System.out.println(toppingCookAmount);

                pizzeria.setCooks(cooks);
            }

            else {
                System.out.println("there must be at least 3 cooks!");
            }
        }

        else {
            System.out.println("oh no...");
        }

        // TASK 3: CLIENTS GENERATION

        List<PizzaSettings> menu = new ArrayList<>();
        menu.add(new PizzaSettings("4 сири", 40));
        menu.add(new PizzaSettings("Карбонара", 30));
        menu.add(new PizzaSettings("Кальцоне", 35));
        menu.add(new PizzaSettings("Маргарита", 37));
        menu.add(new PizzaSettings("Пепероні", 32));
        menu.add(new PizzaSettings("Гавайська", 31));
        pizzeria.setMenu(menu);

        pizzeriaManager.generateClient();

        // List<Cook> cooks = new ArrayList<>();
        // for (int i = 0; i < cooksAmount; i++) {
        // cooks.add();
        // }

        // // should be changed with creating Pizzeria settings functionality
        // int cashiersAmount = 2;
        //
        // List<Cashier> cashiers = new ArrayList<>();
        // for (int i = 0; i < cashiersAmount; i++) {
        //
        // // test objects
        // List<Customer> customers = new ArrayList<>(List.of(
        // new Customer(new Table(true),
        // new Order(List.of(new Pizza(new PizzaSettings("First settings", 10)),
        // new Pizza(new PizzaSettings("Second settings", 5))))),
        // new Customer(new Table(true), new Order(List.of(new Pizza(new PizzaSettings("First settings", 10)),
        // new Pizza(new PizzaSettings("Second settings", 5)))))));
        //
        // cashiers.add(new Cashier(customers));
        // }
        // cashiers.forEach(Cashier::start);
        // new DoughCook().takeTask();
    }
}