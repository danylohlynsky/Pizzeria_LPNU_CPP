package pizzeria.entity;

import java.util.ArrayList;
import java.util.List;

public class Cashier extends Thread {
    private final List<Customer> customers;

    public Cashier() {
        this.customers = new ArrayList<>();
    }

    private final static Pizzeria pizzeria = Pizzeria.getInstance();

    public void nextCustomer(Customer customer) throws InterruptedException {
        synchronized (pizzeria) {
            pizzeria.getQueue().add(customer.getOrder());
            // fixme: may be should be changed (add all pizzas to dough queue), but may be it's fine =)
            customer.getOrder().getPizzas().forEach(pizzeria.getDoughQueue()::addPizzaToQueue);
            customer.getOrder().setState(OrderState.PREPARING);
            customer.getTable().setTableState(TableState.CUSTOMER_WAITING);
            System.out.println(pizzeria.getQueue().size() + " orders in the queue");
        }
        customers.remove(customer);
    }

    private void startWork() {
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
        startWork();
    }

    @Override
    public void run() {
        startWork();
    }

    public int getCustomersAmount() {
        return customers.size();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
}
