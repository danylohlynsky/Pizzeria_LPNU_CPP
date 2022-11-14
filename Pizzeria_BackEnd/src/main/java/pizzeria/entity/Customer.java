package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Getter
public class Customer {
    private static final Random RANDOM = new Random();
    private Table table;
    private Order order;
    private String name;

    public Customer() {
        this.name = String.valueOf(RANDOM.nextInt(100000));
    }

    public Customer(Table table, Order order) {
        this();
        this.table = table;
        this.order = order;
    }

    public void chooseCashier(List<Cashier> allCashiers) {
        var cashier = allCashiers.stream().min(Comparator.comparing(Cashier::getCustomersAmount)).orElseThrow();
        cashier.addCustomer(this);
    }

    public void eat() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        table.setAvailable(Boolean.TRUE);
    }

    public String getName() {
        return name;
    }
}
