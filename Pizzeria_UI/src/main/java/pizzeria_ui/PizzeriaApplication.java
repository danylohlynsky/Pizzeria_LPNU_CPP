package pizzeria_ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PizzeriaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // CooksApplication cooksApplication = new CooksApplication(4, 1);
        // while(true){
        // cooksApplication.start(stage);
        // new Thread(new Runnable() {
        // @Override
        // public void run() {
        // while(true) {
        // cooksApplication.updateCooks();
        // }
        // }
        // }
        // }).start();

        // }

        FXMLLoader fxmlLoader = new FXMLLoader(PizzeriaApplication.class.getResource("cooks-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Pizzeria");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}