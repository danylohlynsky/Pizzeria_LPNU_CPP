package pizzeria.entity.cooks;

import pizzeria.entity.Pizza;
import pizzeria.entity.Pizzeria;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Cook extends Thread {
    protected static Pizzeria pizzeria = Pizzeria.getInstance();
    protected CookState state = CookState.AVAILABLE; //default state
    protected Pizza pizza;
    // protected int time = pizzeria.getMinSecondsForPizza();
    protected Timer timer = new Timer();
    protected int time = 3; // час для відлагодження програми

    protected void takeBreak() {
        boolean haveBreak = new Random().nextInt(1, 10) == 5;

        if (haveBreak) {
            try {
                System.out.println(Thread.currentThread().getId() + " take brake");
                state = CookState.OUT;
                Thread.sleep(1000); // 1000 це 1 секунда (hate russians)
                state = CookState.AVAILABLE;
                System.out.println(Thread.currentThread().getId() + " finish brake");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    protected abstract void takeTask();

    protected abstract void finishTask();

    public TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                finishTask();
            }
        };
    }

    public CookState getCookState(){
        return this.state;
    }

    @Override
    public void run() {
        takeTask();
    }

    public Cook() {
        start();
    }
}
