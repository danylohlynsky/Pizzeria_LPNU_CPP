package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Order {
    List<Pizza> pizzas;

    Customer customer;


    public Order(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
