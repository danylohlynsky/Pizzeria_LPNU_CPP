package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Pizza {
    private PizzaSettings pizzaSettings;
    private LocalDateTime start;
    private PizzaState state;

    public Pizza(PizzaSettings pizzaSettings) {
        this.pizzaSettings = pizzaSettings;
    }
}
