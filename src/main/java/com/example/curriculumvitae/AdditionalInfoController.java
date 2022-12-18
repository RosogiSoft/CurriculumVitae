package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import com.example.curriculumvitae.helper.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class AdditionalInfoController {

    public TextArea additionalTextField;
    public ChoiceBox<String> foreignLanguage;
    public ChoiceBox<String> driverLicense;
    public TextArea additionalCompetencies;
    public TextField socialNetwork;

    public void initialize(){
        setForeignLanguage();
        setDriverLicense();
    }

    public void nextButton(ActionEvent actionEvent) throws Exception {
        pushData();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("final_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void pushData() {
        WelcomeController.person.setAdditionalInfo(getAdditionalTextField());
        WelcomeController.person.setForeignLanguage(getForeignLanguage());
        WelcomeController.person.setDriverLicense(getDriverLicense());
        WelcomeController.person.setAdditionalCompetencies(getAdditionalCompetencies());
        WelcomeController.person.setSocialNetwork(getSocialNetwork());
    }

    public String getAdditionalTextField() {
        return additionalTextField.getText();
    }

    public String getForeignLanguage() {
        return foreignLanguage.getValue();
    }

    public String getDriverLicense() {
        return driverLicense.getValue();
    }

    public String  getAdditionalCompetencies() {
        return additionalCompetencies.getText();
    }

    public String getSocialNetwork() {
        return socialNetwork.getText();
    }

    private void setForeignLanguage(){
        final String[] languages = {
                "Английский",
                "Французский",
                "Испанский",
                "Китайский",
                "Немецкий ",
                "Другой"
        };
        foreignLanguage.getItems().addAll(languages);
    }

    private void setDriverLicense(){
        final String[] license = {
                "Нет водительского удостоверения",
                "Есть В/У категори A",
                "Есть В/У категори B",
                "Есть В/У категори C",
                "Есть В/У категори D",
                "Есть В/У категори M",
                "Есть В/У нескольких категорий",
                "Другое"
        };
        driverLicense.getItems().addAll(license);
    }
}
