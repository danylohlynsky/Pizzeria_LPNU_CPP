package pizzeria.entity.cooks;

import pizzeria.entity.Order;

import java.util.List;
import java.util.Random;

public class DoughCook extends Cook {
    @Override
    public void takeBreak() {
        boolean haveBreak = new Random().nextInt(1, 10) == 5 ? true : false;

        if (haveBreak) {
            try {
                state = CookState.OUT;
                Thread.sleep(1000); // 1000 это 1 секунда
                state = CookState.AVAILABLE;

            } catch (Exception ex) {
            }

        }
    }

    @Override
    public void takeTask() {
        List<Order> listOrders = pizzeria.getQueue();
        if (listOrders.size() > 0)
            pizzas = listOrders.get(0).getPizzas(); // бере замовлення з черги

        // приготування pizzas

        finishTask();
    }

    @Override
    public void finishTask() {
        //передати іншому таску


        takeBreak();
    }
}
