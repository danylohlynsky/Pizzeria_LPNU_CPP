package pizzeria.entity;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Oleh Hembarovskyi
 *
 * @link oleh.hembarovskyi.pz.2020@lpnu.ua
 *
 * @since 29/10/2022
 **/
public class PizzaQueue {
    private final Queue<Pizza> queue;
    private final Lock lock;
    private final Condition condition;

    public PizzaQueue() {
        this.queue = new PriorityQueue<>();
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void addPizzaToQueue(Pizza pizza) {
        if (lock.tryLock()) {
            queue.add(pizza);
            condition.signal();
            lock.unlock();
        }
    }

    public Pizza getPizzaAndRemoveFromQueue() {
        lock.lock();
        Pizza pizza = queue.poll();

        while (pizza == null) {
            try {
                System.out.println(Thread.currentThread().getId() + " wait for pizza to do");
                condition.await();
                pizza = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock.unlock();

        return pizza;
    }
}
