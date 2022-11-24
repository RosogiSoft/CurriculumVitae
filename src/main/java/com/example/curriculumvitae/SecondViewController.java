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
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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
    public TextField filePathTextFiled;
    public Label errorImage;

    //Для хранения исходных данных о загружаемом изображении
    private double originWidth, originHeight;
    //Для хранения текущих значений о координатах
    private int currentPositionX, currentPositionY;

    private int differenceX, differenceY;
    private int originPositionX, originPositionY;
    //Хранения разрешения для вычисления ориентира (4:3, 16:9 и т.д.)
    private int width = 300, height = 300;
    //Для хранения шага масштабирования изображения
    private int zoomStep = 5;

    PixelReader pixelReader;
    WritableImage writableImage;

    public void SelectFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(this.imageView.getScene().getWindow());
        if (selectedFile != null) {
            filePathTextFiled.setText(selectedFile.getPath());
            FileInputStream fileInputStream = new FileInputStream(selectedFile.getPath());
            image = new Image(fileInputStream);
            setImage(image);

        }
    }

    private void setImage(Image image){
        findOriginalResolution();
        findCenterOfImage();
        pixelReader = image.getPixelReader();
        writableImage = new WritableImage(pixelReader, currentPositionX, currentPositionY, height, width);
        imageView.setImage(writableImage);
    }

    private void redrawImage(){
        writableImage = new WritableImage(pixelReader, currentPositionX, currentPositionY, height, width);
        imageView.setImage(writableImage);
    }


    private void findCenterOfImage(){
        currentPositionX = (int) (originWidth / 2) - (width / 2);
        currentPositionY = (int) (originHeight / 2) - (height / 2);
    }

    private void findOriginalResolution(){
        originHeight = image.getHeight();
        originWidth = image.getWidth();
    }

    public void mouseDragged(DragEvent mouseEvent) {
        differenceX = (int) (mouseEvent.getSceneX() - currentPositionX);
        differenceY = (int) (mouseEvent.getSceneY() - currentPositionY);

        /*if ((int) mouseEvent.getSceneX() - originPositionX > 0){
            currentPositionX = currentPositionX + differenceX;
        }
        else if ((int) mouseEvent.getSceneX() - originPositionX < 0 ){
            currentPositionX = currentPositionX - differenceX;
        }
        else if ((int) mouseEvent.getSceneY() - originPositionY > 0){
            currentPositionY = currentPositionY + differenceY;
        }
        else if ((int) mouseEvent.getSceneY() - originPositionY < 0 ){
            currentPositionY = currentPositionY - differenceY;
        }*/

        currentPositionX = currentPositionX + differenceX;
        currentPositionY = currentPositionY + differenceY;

        if (currentPositionY > 0 && currentPositionX > 0 && currentPositionY < originHeight && currentPositionX < originWidth){
            redrawImage();
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        originPositionX = (int) mouseEvent.getSceneX();
        originPositionY = (int) mouseEvent.getSceneY();
    }

    public void scroll(ScrollEvent scrollEvent) {
        System.out.println("Текущее значение DeltaY = " + scrollEvent.getDeltaY());
        int delta = (int) scrollEvent.getDeltaY();
        if (delta < 0){
            if (width > zoomStep && height > zoomStep){
                width = width - zoomStep;
                height = height - zoomStep;
            }
        } else if (delta > 0){
            if (width < originWidth && height < originHeight){
                width = width + zoomStep;
                height = height + zoomStep;
            }
        }
        if (width > 0 && height > 0 && width < originWidth + width && height < originHeight + height){
            redrawImage();
        }

    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        MainController.person.setImage(selectedFile);
        DataBaseConnect.addData(MainController.person);
        DataBaseConnect.addDataPic(MainController.person);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("third_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
