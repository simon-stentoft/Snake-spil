package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Controller c = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        apple();
       // FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sample.fxml"));
        VBox root = new VBox();
        Canvas canvas = new Canvas(c.getWidth()*c.getCornerSize(),c.getHeight()*c.getCornerSize());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTick = 0;
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }
                if (now - lastTick > 1000000000 / c.getSpeed()) {
                    lastTick = now;
                }
            }
        } .start();

        Scene scene = new Scene(root,c.getWidth()*c.getCornerSize(),c.getHeight()*c.getCornerSize());
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

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

        //Adding parts of the snake
        c.getSnake().add(new Corner(c.getWidth()/2,c.getHeight()/2));
        c.getSnake().add(new Corner(c.getWidth()/2,c.getHeight()/2));
        c.getSnake().add(new Corner(c.getWidth()/2,c.getHeight()/2));
    }


    //Apples
    public static void apple() {
        Controller c = new Controller();
        while (true) {
            c.setAppleX(c.getRand().nextInt(c.getWidth()));
            c.setAppleY(c.getRand().nextInt(c.getHeight()));

            for (Corner corner : c.getSnake()) {
                if (corner.getX() == c.getAppleX() && corner.getY() == c.getAppleY()) {
                    continue;
                }
            }
            c.setSpeed(c.getSpeed()+1);
            break;
        }
    }

    public static void tick(GraphicsContext gc) {
        Controller c = new Controller();
        if (c.getGameOver()) {
            return;
        }
        for (int i = c.getSnake().size() - 1; i >= 1; i--) {
            c.getSnake().get(i).setX(c.);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
