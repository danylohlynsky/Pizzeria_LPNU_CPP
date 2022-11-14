package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PizzaSettings {
    // Sum of ratios should be equal to 1.0
    private static final Double PIZZA_RATIO_DOUGH = 0.3D;
    private static final Double PIZZA_RATIO_TOPPING = 0.3D;
    private static final Double PIZZA_RATIO_BAKE = 0.4D;
    private String title;
    private int preparingTimeInSeconds;

    public long getTimeInMillisForDough() {
        return (long) (PIZZA_RATIO_DOUGH * preparingTimeInSeconds * 1000);
    }

    public long getTimeInMillisForTopping() {
        return (long) (PIZZA_RATIO_TOPPING * preparingTimeInSeconds * 1000);
    }

    public long getTimeInMillisForBake() {
        return (long) (PIZZA_RATIO_BAKE * preparingTimeInSeconds * 1000);
    }

    public long getTimeInMillisForFullstack() {
        return preparingTimeInSeconds * 1000L;
    }
}
