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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    //Variables
    private int width = 20;
    private int height = 20;
    private int appleX = 0;
    private int appleY = 0;
    private int speed = 3;
    private int cornerSize = 25;
    private List<Corner> snake = new ArrayList<>();
    private Dir direction = Dir.left;
    private boolean gameOver = false;
    private Random rand = new Random();

    public enum Dir {
        left, right, up, down
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sample.fxml"));

        apple();

        VBox root = new VBox();
        Canvas canvas = new Canvas(width * cornerSize, height * cornerSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }
                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    tick(gc);
                }
            }
        }.start();

        Scene scene = new Scene(root, width * cornerSize, height * cornerSize);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.W) {
                direction = Dir.up;
            }
            if (keyEvent.getCode() == KeyCode.A) {
                direction = Dir.left;
            }
            if (keyEvent.getCode() == KeyCode.D) {
                direction = Dir.right;
            }
            if (keyEvent.getCode() == KeyCode.S) {
                direction = Dir.down;
            }
        });

        //Adding parts of the snake
        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));
        snake.add(new Corner(width / 2, height / 2));
    }

    public void apple() {
        start: while (true) {
            appleX = rand.nextInt(width);
            appleY = rand.nextInt(height);

            for (Corner corner : snake) {
                if (corner.x == appleX && corner.y == appleY) {
                    continue start;
                }
            }
            speed++;
            break;
        }
    }

    public void tick(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("",50));
            gc.fillText("You lost",100,250);
            return;
        }
        for (int i = snake.size() - 1; i >= 1; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        //
        switch(direction) {
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snake.get(0).y++;
                if (snake.get(0).y > height) {
                    gameOver = true;
                }
                break;
            case left:
                snake.get(0).x--;
                if (snake.get(0).x < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snake.get(0).x++;
                if (snake.get(0).y > width) {
                    gameOver = true;
                }
                break;
        }

        //Eating apples
        if (appleX == snake.get(0).x && appleY == snake.get(0).y) {
            snake.add(new Corner(-1,-1));
            apple();
        }//Destroy/end game
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                gameOver = true;
            }
        }

        //Background
        gc.setFill(Color.AQUA);
        gc.fillRect(0,0,width*cornerSize,height*cornerSize);

        //Painting snake
        for (Corner c: snake) {
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 1, cornerSize - 1);
            gc.setFill(Color.BLUE);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 2, cornerSize - 2);
        }

        //Making food appear
        Color cc = Color.RED;
        gc.setFill(cc);
        gc.fillOval(appleX * cornerSize, appleY * cornerSize, cornerSize, cornerSize);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class Corner {
        private int x;
        private int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCornerSize() {
        return cornerSize;
    }

    public void setCornerSize(int cornerSize) {
        this.cornerSize = cornerSize;
    }

    public List<Corner> getSnake() {
        return snake;
    }

    public void setSnake(List<Corner> snake) {
        this.snake = snake;
    }

    public Dir getDirection() {
        return direction;
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}





















