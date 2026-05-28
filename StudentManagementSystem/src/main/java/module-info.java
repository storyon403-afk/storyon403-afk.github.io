module com.example.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.studentmanagementsystem.main;
    opens com.example.studentmanagementsystem.main to javafx.fxml;

    exports com.example.studentmanagementsystem.utils;
    opens com.example.studentmanagementsystem.utils to javafx.fxml;

    exports com.example.studentmanagementsystem.services;
    opens com.example.studentmanagementsystem.services to javafx.fxml;

    exports com.example.studentmanagementsystem.controller;
    opens com.example.studentmanagementsystem.controller to javafx.fxml;
}