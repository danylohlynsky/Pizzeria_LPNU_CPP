package pizzeria.entity;

import java.util.Scanner;

/**
 * @author Oleh Hembarovskyi
 *
 * @link oleh.hembarovskyi@embrox.com
 *
 * @since 02/11/2022
 **/
public class ConsoleDispatcher implements Dispatcher {
    @Override
    public void start() {
        // TASK 1: CREATING PIZZERIA
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter min seconds for pizza:");
        int minSecondsForPizza = scanner.nextInt();

        System.out.println("enter different pizza amount:");
        int differentPizzaAmount = scanner.nextInt();

        System.out.println("enter tables amount:");
        int tablesAmount = scanner.nextInt();

        System.out.println("enter cashier amount:");
        int cashiersAmount = scanner.nextInt();

        System.out.println("enter cook mode:\n0 - full stack cooks\n1 - not full stack cooks (min. 3)");
        int cookMode = scanner.nextInt();

        System.out.println("enter cooks amount:");
        int cooksAmount = scanner.nextInt();

        Pizzeria.getInstance().start(minSecondsForPizza, differentPizzaAmount, tablesAmount, cashiersAmount, cookMode,
                cooksAmount);
    }
}
