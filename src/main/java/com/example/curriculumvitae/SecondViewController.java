package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class SecondViewController {

    File selectedFile;
    Image image;
    public ImageView imageView;
    public TextField filePath;
    public Label errorImage;
    private Scene scene;

    public void SelectFilePath(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(this.imageView.getScene().getWindow());
        if (selectedFile != null) {
            openImage(selectedFile);
        }
    }

    private void openImage(File selectedFile) throws FileNotFoundException {
        filePath.setText(selectedFile.getPath());
        FileInputStream fileInputStream = new FileInputStream(selectedFile.getPath());
        image = new Image(fileInputStream);
        imageView.setImage(image);
        MainController.person.setImage(selectedFile);
    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        DataBaseConnect.addData(MainController.person);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third_view.fxml")));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
