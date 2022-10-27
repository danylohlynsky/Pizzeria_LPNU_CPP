package pizzeria.entity.cooks;

public class ToppingCook extends Cook {

    @Override
    public void takeTask() {

        state = CookState.valueOf("BUSY");
        System.out.println("ToppingCook take task");

        timer.schedule(finish, 4L * time * 100);

    }

    @Override
    public void finishTask() {
        state = CookState.valueOf("AVAILABLE");
        System.out.println("ToppingCook done task");
        takeBreak();
        new BakerCook().takeTask();
    }
}
