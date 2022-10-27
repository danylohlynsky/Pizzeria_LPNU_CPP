package pizzeria;

import pizzeria.entity.Cashier;
import pizzeria.entity.Pizzeria;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        // should be changed with creating Pizzeria settings functionality
        int cashiersAmount = 5;

        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < cashiersAmount; i++) {
            cashiers.add(new Cashier());
        }
        cashiers.forEach(Cashier::start);
    }
}