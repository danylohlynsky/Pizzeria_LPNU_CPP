package pizzeria.entity.cooks;

import pizzeria.entity.Order;

import java.util.List;
import java.util.TimerTask;

public class DoughCook extends Cook {
    @Override
    public void takeBreak() {

    }

    @Override
    public void takeTask() {
        List<Order> listOrders = pizzeria.getQueue();
        if (listOrders.size() > 0)
            pizzas = listOrders.get(0).getPizzas();
        state = CookState.valueOf("BUSY");
        System.out.println("DoughCook take task");

        timer.schedule(finish, 3L * time * 100);

    }

    @Override
    public TimerTask finishTask() {
        state = CookState.valueOf("AVAILABLE");
        System.out.println("DoughCook has done");
        new ToppingCook().takeTask();
        return null;
    }
}
