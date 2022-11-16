package pizzeria_ui;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pizzeria.entity.Order;
import pizzeria.entity.Pizzeria;

import java.util.List;

/**
 * @author Oleh Hembarovskyi
 *
 * @link oleh.hembarovskyi@embrox.com
 *
 * @since 14/11/2022
 **/
public class OrderListController {
    private final TableView<Order> table;

    public OrderListController(TableView<Order> table) {
        this.table = table;
        init();
    }

    private void init() {
        TableColumn<Order, String> customerColumn = new TableColumn<>("Customer");
        customerColumn.setPrefWidth(100);
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));

        TableColumn<Order, String> pizzasColumn = new TableColumn<>("Order");
        pizzasColumn.setPrefWidth(300);
        pizzasColumn.setCellValueFactory(new PropertyValueFactory<>("order"));

        TableColumn<Order, String> stateColumn = new TableColumn<>("State");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        table.getColumns().addAll(customerColumn, pizzasColumn, stateColumn);
    }

    public void update() {
        List<Order> orders = Pizzeria.getInstance().getOrders();
        table.getItems().clear();
        table.getItems().addAll(orders);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
