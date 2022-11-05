module com.example.pizzeria_ui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires Pizzeria;

    opens com.example.pizzeria_ui to javafx.fxml;
    exports com.example.pizzeria_ui;

}