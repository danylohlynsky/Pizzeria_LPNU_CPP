package pizzeria.entity.cooks;

import pizzeria.entity.*;

import java.util.List;
import java.util.Random;

public abstract class Cook {

    protected static Pizzeria pizzeria = new Pizzeria();
    List<Pizza> pizzas; // pizzas - піци в одному замовленні
    protected CookState state;

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

    public abstract void takeTask();

    public abstract void finishTask();
}
