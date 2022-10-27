package pizzeria.entity.cooks;

import pizzeria.entity.Order;
import java.util.List;

public class BakerCook extends Cook {

    @Override
    public void takeTask() {
        state = CookState.valueOf("BUSY");
        System.out.println("Baker take task");

        timer.schedule(finish, 3 * time * 100);

    }

    @Override
    public void finishTask() {
        pizzeria.getQueue().remove(pizzas);
        state = CookState.valueOf("AVAILABLE");
        System.out.println("Pizza is already done!!!");
    }
}
