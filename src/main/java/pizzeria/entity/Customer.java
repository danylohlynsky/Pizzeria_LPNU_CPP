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
}
