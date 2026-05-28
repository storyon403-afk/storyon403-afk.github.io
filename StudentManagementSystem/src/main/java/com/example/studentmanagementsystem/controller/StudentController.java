package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.services.Stu_JDBCU;
import com.example.studentmanagementsystem.services.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class StudentController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ChoiceBox<String> sexChoiceBox;
    @FXML private TextField addressField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, Integer> ageColumn;
    @FXML private TableColumn<Student, String> sexColumn;
    @FXML private TableColumn<Student, String> addressColumn;

    private  Warrning warrnings;
    private Prompt prompts;
    private final Stu_JDBCU dbUtil = new Stu_JDBCU();
    private final ObservableList<Student> studentData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        sexChoiceBox.setItems(FXCollections.observableArrayList("man", "woman"));

        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));
        ageColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        sexColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSex() ? "man" : "woman"));
        addressColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));

        studentTable.setItems(studentData);
        loadAllStudents();
    }

    private void loadAllStudents() {
        studentData.clear();
        List<Student> list = dbUtil.getAllStudents();
        studentData.addAll(list);
    }

    @FXML
    private void handleAdd() {
        try {
            Student stu = new Student(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    "man".equals(sexChoiceBox.getValue()),
                    addressField.getText()
            );
            if (dbUtil.addStudent(stu)) loadAllStudents();
        } catch (Exception e) {
            warrnings = new Warrning("Please fill in the correct information.");
        }
    }

    @FXML
    private void handleUpdate() {

            String idText = idField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();
            String sexValue = sexChoiceBox.getValue();
            String address = addressField.getText();

            if(idText.isEmpty() || name.isEmpty() || ageText.isEmpty() || sexValue == null || address.isEmpty()) {
                warrnings = new Warrning("Please fill in all fields.");
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                int age = Integer.parseInt(ageText);
                boolean sex = "man".equals(sexValue);

                Student stu = new Student(id, name, age, sex, address);
                if (dbUtil.updateStudent(stu)) loadAllStudents();
            } catch (NumberFormatException e) {
                warrnings = new Warrning("ID and Age must be numbers.");
            } catch (Exception e) {
                warrnings = new Warrning("An error occurred while adding the student.");
        }

    }

    @FXML
    private void handleDelete() {
        try {
            int id = Integer.parseInt(idField.getText());
            if (dbUtil.deleteStudent(id)) loadAllStudents();
        } catch (Exception e) {
            warrnings = new Warrning("Please fill in the correct Student id.");
        }
    }

    @FXML
    private void handleSearchById() {
        try {
            int id = Integer.parseInt(idField.getText());
            Student stu = dbUtil.getStudentById(id);
            studentData.clear();
            if (stu != null) studentData.add(stu);
            else prompts = new Prompt("The student with this ID was not found.");
        } catch (Exception e) {
            warrnings = new Warrning("Please fill in the correct Student id.");
        }
    }

    @FXML
    private void handleSearchByName() {
        String name = nameField.getText();
        List<Student> list = dbUtil.searchStudentsByName(name);
        studentData.clear();
        studentData.addAll(list);
    }

    @FXML
    private void handleShowAll() {
        loadAllStudents();
    }

}
