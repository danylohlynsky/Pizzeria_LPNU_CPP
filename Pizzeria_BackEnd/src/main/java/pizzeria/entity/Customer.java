package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Getter
public class Customer {
    private Table table;
    private Order order;

    public void chooseCashier(List<Cashier> allCashiers) {
        var cashier = allCashiers.stream().min(Comparator.comparing(Cashier::getCustomersAmount)).orElseThrow();
        cashier.addCustomer(this);
    }

    public void eat() {
        table.setTableState(TableState.CUSTOMER_EATING);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        table.setTableState(TableState.EMPTY);
    }
}
