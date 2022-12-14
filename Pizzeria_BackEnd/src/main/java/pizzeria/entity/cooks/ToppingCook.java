package pizzeria.entity.cooks;

public class ToppingCook extends Cook {
    @Override
    public void takeTask() {
        pizza = pizzeria.getToppingQueue().getPizzaAndRemoveFromQueue();
        logger.log(String.format("ToppingCook start cook %s", pizza.getPizzaSettings().getTitle()));
        if (pizza != null) {
            state = CookState.BUSY;
            System.out.println(Thread.currentThread().getId() + " Topping Cooker take task | "
                    + pizza.getPizzaSettings().getTitle());
            timer.schedule(getTimerTask(), pizza.getPizzaSettings().getTimeInMillisForTopping());
        } else {
            takeTask();
        }
    }

    @Override
    public void finishTask() {
        logger.log(String.format("ToppingCook handed over %s to bakerCook", pizza.getPizzaSettings().getTitle()));
        pizzeria.getBakeQueue().addPizzaToQueue(pizza);
        state = CookState.AVAILABLE;
        System.out.println(
                Thread.currentThread().getId() + " Topping Cooker has done | " + pizza.getPizzaSettings().getTitle());
        takeBreak();
        takeTask();
    }
}
