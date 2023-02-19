package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoftSkillsController {

    @FXML
    public ChoiceBox<String> skillOne;
    @FXML
    public ChoiceBox<String> skillTwo;
    @FXML
    public ChoiceBox<String> skillThree;
    @FXML
    public ChoiceBox<String> skillFour;
    @FXML
    public ChoiceBox<String> skillFive;

    private final ArrayList<String> softSkills = DataBaseConnect.getSoftSkills();
    private ArrayList<ChoiceBox> choiceBoxes = new ArrayList<>();


    public void initialize(){
        addChoiceBoxes();
        for (ChoiceBox choiceBox : choiceBoxes) {
            choiceBox.getItems().addAll(softSkills);
        }
    }

    private void addChoiceBoxes(){
        choiceBoxes.add(skillOne);
        choiceBoxes.add(skillTwo);
        choiceBoxes.add(skillThree);
        choiceBoxes.add(skillFour);
        choiceBoxes.add(skillFive);
    }

    private void writeData(){
        ArrayList<String> softSkillsList = new ArrayList<>();
        for (ChoiceBox choiceBox : choiceBoxes) {
            softSkillsList.add(choiceBox.getValue().toString());
        }
        WelcomeController.person.setSoftSkills(softSkillsList);
    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        writeData();
        DataBaseConnect.addFullDataStudent(WelcomeController.person);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("final_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
