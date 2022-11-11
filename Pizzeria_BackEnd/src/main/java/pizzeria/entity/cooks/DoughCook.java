package pizzeria.entity.cooks;

public class DoughCook extends Cook {
    public DoughCook() {
        super();
    }

    @Override
    public void takeTask() {
        pizza = pizzeria.getDoughQueue().getPizzaAndRemoveFromQueue();

        if (pizza != null) {
            state = CookState.BUSY;
            System.out.println(
                    Thread.currentThread().getId() + " DoughCook take task | " + pizza.getPizzaSettings().getTitle());
            timer.schedule(getTimerTask(), pizza.getPizzaSettings().getTimeInMillisForDough());
        } else {
            takeTask();
        }
    }

    @Override
    public void finishTask() {
        pizzeria.getToppingQueue().addPizzaToQueue(pizza);
        state = CookState.AVAILABLE;
        System.out.println(
                Thread.currentThread().getId() + " DoughCook has done | " + pizza.getPizzaSettings().getTitle());
        takeBreak();
        takeTask();
    }
}
