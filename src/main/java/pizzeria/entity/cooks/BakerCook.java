package pizzeria.entity.cooks;

import pizzeria.entity.Order;

import java.util.List;
import java.util.Random;

public class BakerCook extends Cook {

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
        // завершення
        pizzeria.getQueue().remove(pizzas); // видалення з черги виконане замовлення

        takeBreak(); // запит на перерву
    }
}
