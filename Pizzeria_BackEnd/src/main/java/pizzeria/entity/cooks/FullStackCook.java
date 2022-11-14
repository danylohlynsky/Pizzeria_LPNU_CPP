package pizzeria.entity.cooks;

public class FullStackCook extends Cook {
    @Override
    public void takeTask() {
        pizza = pizzeria.getDoughQueue().getPizzaAndRemoveFromQueue();
        logger.log(String.format("Fullstack start cook %s", pizza.getPizzaSettings().getTitle()));
        if (pizza != null) {
            state = CookState.BUSY;
            System.out.println(
                    Thread.currentThread().getId() + " Fullstack take task | " + pizza.getPizzaSettings().getTitle());
            timer.schedule(getTimerTask(), pizza.getPizzaSettings().getTimeInMillisForFullstack());
        } else {
            takeTask();
        }
    }

    @Override
    public void finishTask() {
        logger.log(String.format("Fullstack finish cook %s", pizza.getPizzaSettings().getTitle()));
        pizza.pizzaReady();
        state = CookState.AVAILABLE;
        System.out.println(
                Thread.currentThread().getId() + " Fullstack has done | " + pizza.getPizzaSettings().getTitle());
        takeBreak();
        takeTask();
    }
}
