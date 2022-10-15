package pizzeria.entity.cooks;

public abstract class Cook {
    protected CookState state;

    public abstract void takeBreak();

    public abstract void takeTask();

    public abstract void finishTask();
}
