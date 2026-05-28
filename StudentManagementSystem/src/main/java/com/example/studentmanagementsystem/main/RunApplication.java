package com.example.studentmanagementsystem.main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RunApplication extends Application {
    @FXML
    private Label greet;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("/com/example/studentmanagementsystem/fxmls/login-view.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root,600,400);

        stage.setTitle("学生管理系统");

        Image icon = new Image(getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/Nagano_Hara_Meixu.png"));
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();

        System.out.println("图片是否存在：" + RunApplication.class.getResource("/com/example/studentmanagementsystem/photos/login.png") != null);

    }

    public static void main(String[] args) {
        launch();
    }
}
