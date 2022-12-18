package com.example.curriculumvitae;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EducationController {

    public TextField establishment;
    public TextField faculty;
    public ChoiceBox<String> formOfStudy;
    public ChoiceBox<String> yearOfEnding;

    public void initialize() {
        initChoiceBoxes();
    }

    public void nextScreen(ActionEvent actionEvent) throws IOException {
        writeEducationData();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("additionalInfo_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void writeEducationData() {
        WelcomeController.person.setEstablishment(establishment.getText());
        WelcomeController.person.setFaculty(faculty.getText());
        WelcomeController.person.setFormOfStudy(formOfStudy.getValue());
        WelcomeController.person.setYearOfEnding(yearOfEnding.getValue());
    }

    private void initChoiceBoxes() {
        List<String> arrayListOfDates = new ArrayList<>();
        for (int i = 2015; i <= 2030; i++) {
            arrayListOfDates.add(String.valueOf(i));
        }
        ObservableList<String> observListOfDate = FXCollections.observableList(arrayListOfDates);
        yearOfEnding.setItems(observListOfDate);
        List<String> arrayListOfStudy = new ArrayList<>();
        arrayListOfStudy.add("Очное");
        arrayListOfStudy.add("Очно/Заочное");
        arrayListOfStudy.add("Заочное");
        ObservableList<String> observListOfStudy = FXCollections.observableList(arrayListOfStudy);
        formOfStudy.setItems(observListOfStudy);
    }
}