package cs3560hw2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CS3560Hw2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        AdminPanel panel = AdminPanel.getInstance();
        VBox vbox = panel.getVBox();
        Scene scene = new Scene(vbox, 300, 250);
        
        primaryStage.setTitle("Admin Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
