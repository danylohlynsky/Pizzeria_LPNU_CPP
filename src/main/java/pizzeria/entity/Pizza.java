package pizzeria.entity;

import lombok.NonNull;

import java.time.LocalDateTime;

public class Pizza implements Comparable<Pizza> {
    private final PizzaSettings pizzaSettings;
    private LocalDateTime start;
    private PizzaState state;
    private Order order;

    public Pizza(@NonNull PizzaSettings pizzaSettings) {
        this.pizzaSettings = pizzaSettings;
    }

    public void pizzaReady() {
        order.pizzaReady();
    }

    @Override
    public int compareTo(Pizza o) {
        return 0;
    }

    public PizzaSettings getPizzaSettings() {
        return pizzaSettings;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public PizzaState getState() {
        return state;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
