package com.example.studentmanagementsystem.controller;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class Prompt {
    public Prompt(String prompts){
        this.prompt(prompts);
    }
    private void prompt(String prompts) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Prompt");
        InputStream inputStream = getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/icon.jpg");

        ImageView icon = new ImageView(new Image(inputStream));
        icon.setFitHeight(16);
        icon.setFitWidth(16);

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/studentmanagementsystem/photos/Nagano_Hara_Meixu.png")));

        alert.getDialogPane().setGraphic(icon);
        alert.setHeaderText("Pay attention!");
        alert.setContentText(prompts);

        alert.showAndWait();
    }
}
