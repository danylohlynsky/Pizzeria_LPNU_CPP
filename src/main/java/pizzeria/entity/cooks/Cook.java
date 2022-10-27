package pizzeria.entity.cooks;

import pizzeria.entity.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public abstract class Cook {

    protected static Pizzeria pizzeria = Pizzeria.getInstance();
    List<Pizza> pizzas; // pizzas - піци в одному замовленні
    protected CookState state;

    // protected int time = pizzeria.getMinSecondsForPizza();
    protected Timer timer = new Timer();
    protected TimerTask finish = new TimerTask() {
        @Override
        public void run() {
            finishTask();
        }
    };
    protected int time = 3; // час для відлагодження програми

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
