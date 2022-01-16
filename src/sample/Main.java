package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

        Controller c = new Controller();
        //Controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W) {
                c.setDirection(Controller.Dir.up);
            }
            if (keyEvent.getCode() == KeyCode.A) {
                c.setDirection(Controller.Dir.left);
            }
            if (keyEvent.getCode() == KeyCode.D) {
                c.setDirection(Controller.Dir.right);
            }
            if (keyEvent.getCode() == KeyCode.S) {
                c.setDirection(Controller.Dir.down);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
