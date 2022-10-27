package pizzeria.entity.cooks;

import pizzeria.entity.*;

import java.util.List;
import java.util.Random;
import java.time.Instant;

import java.util.TimerTask;

public class FullStackCook extends Cook {

    @Override
    public void takeTask() {
        List<Order> listOrders = pizzeria.getQueue();
        if (listOrders.size() > 0)
            pizzas = listOrders.get(0).getPizzas();

        // приготування pizzas

        finishTask();
    }

    @Override
    public void finishTask() {
        // завершення замовлення
        pizzeria.getQueue().remove(pizzas); // видалення з черги виконане замовлення

        takeBreak(); // запит на перерву
    }
}
