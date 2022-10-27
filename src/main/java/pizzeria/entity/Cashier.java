package pizzeria.entity;

import java.util.List;

public class Cashier extends Thread {
    private List<Customer> customers;

    public void nextCustomer() {
        System.out.println("Come here!");
    }


    @Override
    public void run() {
        nextCustomer();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}
