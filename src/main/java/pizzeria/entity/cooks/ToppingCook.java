package pizzeria.entity.cooks;

import pizzeria.entity.Pizza;

import java.util.List;
import java.util.Random;

public class ToppingCook extends Cook {

    @Override
    public void takeTask() {
        // приготування pizzas

        finishTask();
    }

    @Override
    public void finishTask() {
        // передати іншому таску

        takeBreak();
    }
}
