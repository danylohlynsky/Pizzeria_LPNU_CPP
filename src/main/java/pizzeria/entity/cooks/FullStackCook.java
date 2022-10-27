package pizzeria.entity.cooks;

import pizzeria.entity.Order;
import java.util.List;

public class FullStackCook extends Cook {

    @Override
    public void takeTask() {
        List<Order> listOrders = pizzeria.getQueue();
        if (listOrders.size() > 0)
            pizzas = listOrders.get(0).getPizzas();
        state = CookState.valueOf("BUSY");

        System.out.println("FullStackCook take task");

        timer.schedule(finish, time * 1000);

    }

    @Override
    public void finishTask() {
        pizzeria.getQueue().remove(pizzas);
        state = CookState.valueOf("AVAILABLE");

        System.out.println("Pizza is already done, by FullStack!!!");
        takeBreak();
    }
}
