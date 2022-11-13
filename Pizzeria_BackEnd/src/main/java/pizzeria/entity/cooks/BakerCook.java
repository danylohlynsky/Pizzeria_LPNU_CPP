package pizzeria.entity.cooks;

public class BakerCook extends Cook {
    @Override
    public void takeTask() {
        pizza = pizzeria.getBakeQueue().getPizzaAndRemoveFromQueue();
        logger.log(String.format("BakerCook start cook %s", pizza.getPizzaSettings().getTitle()));
        if (pizza != null) {
            state = CookState.BUSY;
            System.out.println(
                    Thread.currentThread().getId() + " Baker take task | " + pizza.getPizzaSettings().getTitle());
            timer.schedule(getTimerTask(), pizza.getPizzaSettings().getTimeInMillisForBake());
        } else {
            takeTask();
        }
    }

    @Override
    public void finishTask() {
        logger.log(String.format("BakerCook finish cook %s", pizza.getPizzaSettings().getTitle()));
        pizza.pizzaReady();
        state = CookState.AVAILABLE;
        System.out.println(Thread.currentThread().getId() + " Baker has done | " + pizza.getPizzaSettings().getTitle());
        takeBreak();
        takeTask();
    }
}
