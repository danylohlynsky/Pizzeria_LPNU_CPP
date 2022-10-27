package pizzeria.entity.cooks;

import pizzeria.entity.Pizza;

import java.util.List;
import java.util.TimerTask;

public class ToppingCook extends Cook {
    private List<Pizza> queue;

    @Override
    public void takeBreak() {

    }

    @Override
    public void takeTask() {

        state = CookState.valueOf("BUSY");
        System.out.println("ToppingCook take task");

        timer.schedule(finish, 4L * time * 100);

    }

    @Override
    public TimerTask finishTask() {
        state = CookState.valueOf("AVAILABLE");
        System.out.println("ToppingCook done task");

        new BakerCook().takeTask();
        return null;
    }
}
