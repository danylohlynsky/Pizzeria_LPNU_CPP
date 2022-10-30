package pizzeria.entity.cooks;

public class FullStackCook extends Cook {
    @Override
    public void takeTask() {
        pizza = pizzeria.getDoughQueue().getPizzaAndRemoveFromQueue();

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
        pizza.pizzaReady();
        state = CookState.AVAILABLE;
        System.out.println(
                Thread.currentThread().getId() + " Fullstack has done | " + pizza.getPizzaSettings().getTitle());
        takeBreak();
        takeTask();
    }
}
