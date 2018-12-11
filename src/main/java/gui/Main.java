package gui;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui.fxml"));
        primaryStage.setTitle("HuluWa World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        //primaryStage.setFullScreen(true);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                GameController.shutdown();
                System.out.print("监听到窗口关闭");
                //GameController controller = fxmlLoader.getController();
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
