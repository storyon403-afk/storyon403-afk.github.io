package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.main.RunApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

public class Login_controller {

    public void login(ActionEvent event){
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("/com/example/studentmanagementsystem/fxmls/student-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setTitle("学生管理系统");

            Image icon = new Image(getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/Nagano_Hara_Meixu.png"));
            stage.getIcons().add(icon);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
