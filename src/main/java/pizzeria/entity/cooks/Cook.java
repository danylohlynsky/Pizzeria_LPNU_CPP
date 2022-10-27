package pizzeria.entity.cooks;

import pizzeria.entity.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Cook {

    protected static Pizzeria pizzeria = new Pizzeria();
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

    public abstract void takeBreak();

    public abstract void takeTask();

    public abstract TimerTask finishTask();
}
