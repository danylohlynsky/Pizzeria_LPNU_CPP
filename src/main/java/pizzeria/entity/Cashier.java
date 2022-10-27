package pizzeria.entity;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Cashier extends Thread {
    private List<Customer> customers;

    // should be singleton
    private final static Pizzeria pizzeria = new Pizzeria();

    public void nextCustomer(Customer customer) throws InterruptedException {
        synchronized (pizzeria) {
            pizzeria.getQueue().add(customer.getOrder());
            System.out.println(pizzeria.getQueue().size() + " orders in the queue");
        }
        customers.remove(customer);
    }

    @Override
    public void run() {
        if (!customers.isEmpty()) {
            try {
                nextCustomer(customers.get(0));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run();
    }
}