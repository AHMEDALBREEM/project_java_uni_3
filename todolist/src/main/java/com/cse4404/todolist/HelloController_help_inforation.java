package com.cse4404.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


import java.sql.*;

public class HelloController_help_inforation {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_msgs";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ahmed@2026";

    @FXML
    private TextArea msg_;

    @FXML
    public void onbackclick(ActionEvent event) throws IOException {
        Button Button = (Button) event.getSource();
        Scene scene = Button.getParent().getScene();
        Stage stage = (Stage) scene.getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onsubmitclick(ActionEvent event) throws IOException,SQLException {
        if (msg_.getText() != "") {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "CREATE TABLE IF NOT EXISTS msgs_from_users ("
                        + "msg VARCHAR(255) NOT NULL" + ");";
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                String sql_ = "INSERT INTO msgs_from_users (msg) VALUES (?);";
                PreparedStatement statement = connection.prepareStatement(sql_);
                statement.setString(1, msg_.getText());
                statement.executeUpdate();
                statement.close();
                connection.close();
                onbackclick(event);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

