package sample;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;

public class Database {
    private Connection connection; //make final
    private Statement stmt;
    private volatile int highestScore;

    private final String url = "jdbc:sqlite:Scoreboard";

    Database() {
        connection = null; //move to final and add throws clause to database constructor
        stmt = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS HIGHSCOREBOARD(\n"
                    + " score INTEGER NOT NULL\n"
                    + ");";

            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addScore(int score) {
        String sql = "INSERT INTO HIGHSCOREBOARD(score) " +
                "VALUES ('"+ score +"')";

        stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            System.out.println("Score added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayHighestScore() {
        String sql = "SELECT MAX(score) AS highScore FROM HIGHSCOREBOARD ";

        stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                highestScore = resultSet.getInt("highScore");
                System.out.println(highestScore + "hi");
            }

            stmt.close();
            System.out.println("Selected highscore.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }
}
