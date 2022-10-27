package pizzeria.entity.cooks;

import pizzeria.entity.*;

import java.util.List;

public abstract class Cook {

    protected static Pizzeria pizzeria = new Pizzeria();
    List<Pizza> pizzas; //pizzas - піци в одному замовленні
    protected CookState state;

    public abstract void takeBreak();

    public abstract void takeTask();

    public abstract void finishTask();
}
