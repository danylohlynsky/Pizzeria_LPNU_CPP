package pizzeria.entity;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Cashier extends Thread {
    private List<Customer> customers;

    // should be singleton
    private final static Pizzeria pizzeria = Pizzeria.getInstance();

    public void nextCustomer(Customer customer) throws InterruptedException {
        synchronized (pizzeria) {
            pizzeria.getQueue().add(customer.getOrder());
            // fixme: may be should be changed (add all pizzas to dough queue), but may be it's fine =)
            customer.getOrder().getPizzas().forEach(pizzeria.getDoughQueue()::addPizzaToQueue);
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

    public int getCustomersAmount() {
        return customers.size();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
