package sample;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection; //make final
    private Statement stmt;

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
}
