package pizzeria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Getter
public class Customer {
    private Table table;
    private Order order;


    public void chooseCashier(List<Cashier> allCashiers){
        int minValue = allCashiers.stream()
                .mapToInt(Cashier::getCustomersAmount)
                .min()
                .orElse(-1);

        List<Cashier> minCustomersCashiers = allCashiers.stream()
                .filter(s -> s.getCustomersAmount() == minValue).toList();

        Cashier selectedCashier = minCustomersCashiers.get(
                new Random().nextInt(minCustomersCashiers.size()));

        selectedCashier.addCustomer(this);

    }
}
