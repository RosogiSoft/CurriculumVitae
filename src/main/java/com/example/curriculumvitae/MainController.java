package com.example.curriculumvitae;

import com.example.curriculumvitae.helper.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    public static Person person;
    public Label errorLog;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() {
        person = new Person();
    }

    public void startButton(ActionEvent actionEvent) throws IOException {
        connectToTheServer();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void connectToTheServer() {
        boolean statusOK = true;

        if (!statusOK) {
            errorLog.setText("Отсутсвует подключение к серверу!");
            errorLog.setVisible(true);
            return;
        }
        errorLog.setVisible(false);

    }
}