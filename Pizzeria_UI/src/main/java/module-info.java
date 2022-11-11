module Pizzeria_UI {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires Pizzeria_BackEnd;

    opens pizzeria_ui to javafx.fxml;

    exports pizzeria_ui;
}