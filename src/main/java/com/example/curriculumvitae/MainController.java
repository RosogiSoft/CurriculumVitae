package com.example.curriculumvitae;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
public class MainController {


    public Label errorLog;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void startButton(ActionEvent actionEvent) throws IOException {
        connect();
        Parent root = FXMLLoader.load(getClass().getResource("first_view.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void connect(){
        boolean statusOK = true;

        if (!statusOK){
            errorLog.setText("Отсутсвует подключение к серверу!");
            errorLog.setVisible(true);
            return;
        }
        errorLog.setVisible(false);

    }
}