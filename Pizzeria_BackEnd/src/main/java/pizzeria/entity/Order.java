package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Setter
@Getter
public class Order {
    public List<Pizza> pizzas;
    private Customer customer;
    private CountDownLatch countDownLatch;
    private OrderState state;

    public Order(List<Pizza> pizzas) {
        pizzas.forEach(pizza -> pizza.setOrder(this));
        this.pizzas = pizzas;
        this.countDownLatch = new CountDownLatch(pizzas.size());
        this.state = OrderState.IN_QUEUE;

        new Thread(() -> {
            try {
                countDownLatch.await();
                state = OrderState.READY;
                customer.eat();
                System.out.println("------- Order fulfilled -------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void pizzaReady() {
        countDownLatch.countDown();
    }

    public String getCustomer() {
        return Optional.ofNullable(customer).map(Customer::getName).orElse("");
    }

    public String getOrder() {
        var pizzaTitlesList = pizzas.stream().map(Pizza::getPizzaSettings).map(PizzaSettings::getTitle).toList();

        return String.join(", ", pizzaTitlesList);
    }
}
