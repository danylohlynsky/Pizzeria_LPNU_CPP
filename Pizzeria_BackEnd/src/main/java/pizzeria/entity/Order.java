package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Setter
@Getter
public class Order {
    private List<Pizza> pizzas;
    private Customer customer;
    private CountDownLatch countDownLatch;

    public Order(List<Pizza> pizzas) {
        pizzas.forEach(pizza -> pizza.setOrder(this));
        this.pizzas = pizzas;
        this.countDownLatch = new CountDownLatch(pizzas.size());

        new Thread(() -> {
            try {
                countDownLatch.await();
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
}
