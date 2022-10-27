package pizzeria.entity.cooks;

import pizzeria.entity.Pizza;

import java.util.List;
import java.util.Random;

public class ToppingCook extends Cook {

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
        // приготування pizzas

        finishTask();
    }

    @Override
    public void finishTask() {
        //передати іншому таску


        takeBreak();
    }
}
