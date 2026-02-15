package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import prime.core.Prime;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Prime prime = new Prime();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPrime(prime); // inject the Prime instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
