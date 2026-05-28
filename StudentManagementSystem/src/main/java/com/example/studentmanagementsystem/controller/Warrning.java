package com.example.studentmanagementsystem.controller;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class Warrning {

    public Warrning(String warrings){
        this.warrning(warrings);
    }
    private void warrning(String warrnings) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warrning!");
        InputStream inputStream = getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/icon.jpg");
        ImageView icon = null;
        if (inputStream != null) {
            icon = new ImageView(new Image(inputStream));
            icon.setFitHeight(16);
            icon.setFitWidth(16);
        } else {
            System.out.println("Warning: icon.jpg not found!");
        }
        if (icon != null) {
            alert.getDialogPane().setGraphic(icon);
        }


        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/Nagano_Hara_Meixu_Warrning.png")));

        alert.getDialogPane().setGraphic(icon);
        alert.setHeaderText("Warrning!!!");
        alert.setContentText(warrnings);

        alert.showAndWait();
    }
}
