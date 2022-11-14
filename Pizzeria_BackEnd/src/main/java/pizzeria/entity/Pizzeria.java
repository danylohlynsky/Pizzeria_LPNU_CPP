package pizzeria.entity;

import lombok.Getter;
import lombok.Setter;
import pizzeria.entity.cooks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Pizzeria {
    private static Pizzeria instance;

    private List<Cook> cooks;

    private List<Table> tables;
    private List<Customer> customers;
    private List<Order> queue;
    private List<Cashier> cashiers;

    private PizzaQueue doughQueue;
    private PizzaQueue toppingQueue;
    private PizzaQueue bakeQueue;

    private List<PizzaSettings> menu;

    private boolean isOpen;

    private CookVersion cookVersion;

    private int minSecondsForPizza;
    private int differentPizzaAmount;

    // default values
    private Pizzeria() {
        this.cooks = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.isOpen = true;
        this.cookVersion = CookVersion.PART_MAKING;
        this.minSecondsForPizza = 10;
        this.differentPizzaAmount = 10;
        this.doughQueue = new PizzaQueue();
        this.toppingQueue = new PizzaQueue();
        this.bakeQueue = new PizzaQueue();
    }

    public void start(int minSecondsForPizza, int differentPizzaAmount, int tablesAmount, int cashiersAmount,
            int cookMode, int cooksAmount) {
        PizzeriaManager pizzeriaManager = new PizzeriaManager();

        this.setMinSecondsForPizza(minSecondsForPizza);

        this.setDifferentPizzaAmount(differentPizzaAmount);
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < tablesAmount; i++) {
            tables.add(new Table(TableState.EMPTY));
        }
        this.setTables(tables);

        List<Cashier> cashiers = new ArrayList<>();
        for (int i = 0; i < cashiersAmount; i++) {
            Cashier cashier = new Cashier();
            cashiers.add(cashier);
            cashier.start();
        }
        this.setCashiers(cashiers);

        if (cookMode == 0) {
            List<Cook> cooks = new ArrayList<>();
            for (int i = 0; i < cooksAmount; i++) {
                cooks.add(new FullStackCook());
            }

            this.setCooks(cooks);
        } else if (cookMode == 1) {
            Random rand = new Random();
            if (cooksAmount >= 3) {
                List<Cook> cooks = new ArrayList<>();

                int bakerCookAmount = rand.nextInt(1, cooksAmount - 1);
                for (int i = 0; i < bakerCookAmount; i++) {
                    cooks.add(new BakerCook());
                }

                int doughCookAmount = rand.nextInt(1, cooksAmount - bakerCookAmount);
                for (int i = 0; i < doughCookAmount; i++) {
                    cooks.add(new DoughCook());
                }

                int toppingCookAmount = cooksAmount - bakerCookAmount - doughCookAmount;
                for (int i = 0; i < toppingCookAmount; i++) {
                    cooks.add(new ToppingCook());
                }

                this.setCooks(cooks);
            } else {
                System.out.println("there must be at least 3 cooks!");
            }
        } else {
            System.out.println("oh no...");
        }

        // TASK 3: CLIENTS GENERATION

        List<PizzaSettings> menu = new ArrayList<>();
        menu.add(new PizzaSettings("4 сири", 40));
        menu.add(new PizzaSettings("Карбонара", 30));
        menu.add(new PizzaSettings("Кальцоне", 35));
        menu.add(new PizzaSettings("Маргарита", 37));
        menu.add(new PizzaSettings("Пепероні", 32));
        menu.add(new PizzaSettings("Гавайська", 31));
        this.setMenu(menu);

        new Thread(pizzeriaManager::generateClient).start();
    }

    public synchronized static Pizzeria getInstance() {
        if (instance == null) {
            instance = new Pizzeria();
        }

        return instance;
    }
}
