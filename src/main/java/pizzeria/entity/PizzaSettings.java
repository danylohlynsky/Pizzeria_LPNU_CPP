package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PizzaSettings {
    private String title;
    private int preparingTime;
}
