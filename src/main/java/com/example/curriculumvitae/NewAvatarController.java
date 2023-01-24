package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.transform.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class NewAvatarController {
    @FXML
    public ImageView imageView;
    @FXML
    public TextField filePathTextFiled;
    public FileChooser fileChooser = new FileChooser();
    public File file;
    public Image image;

    private PixelReader pixelReader;
    private WritableImage writableImage;
    private int height = 120, width = 90;
    private int heightRes, widthRes;
    private int step = 10;
    private int x = 0, y = 0;

    public void SelectFile(ActionEvent actionEvent) throws IOException {
        file = fileChooser.showOpenDialog(this.imageView.getScene().getWindow());

        if (file != null && fileCheck(file)) {
            loadFile(file);
            findCenterOfImage(image);
            getResolution(image);
            redrawImage(image);
        } else {
            System.out.println("Что-то пошло не так(");
        }
    }

//Модуль проверки файла на вес, тип и последующая загрузка изображение с проверкой ращрешения изображения
    private boolean fileCheck(File file){
        return  (checkSizeOfFile(file) && checkTypeOfFile(file));
    }
    private boolean checkSizeOfFile(File file){
        if (file.length() <= 16777216){ //16 мегабайт
            return true;
        } else {
            filePathTextFiled.setText("Изображение слишком большое!");
        }
        return false;
    }
    private boolean checkTypeOfFile(File file){
        String name = file.getName();
        name = name.split("\\.")[1];
        if (name.equals("png") || name.equals("jpg") || name.equals("jpeg")){
            return true;
        } else {
            filePathTextFiled.setText("Выберете файл с раширением png, jpeg или png!");
        }
        return false;
    }
    private boolean checkResolutionOfImage(Image image){
        return image.getWidth() <= 2000 && image.getHeight() <= 2000;
    }
    private void loadFile(File file) throws FileNotFoundException {
        filePathTextFiled.setText(file.getPath());
        FileInputStream fileInputStream = new FileInputStream(file.getPath());
        image = new Image(fileInputStream);
    }

//Методы для изменения отображения
    public void zoomOut(ActionEvent actionEvent) {
        if ((height + 100) < heightRes && (width + 100) < heightRes){
            height = height + 50;
            width = width + 50;
            step = step + 5;
            redrawImage(image);
        } else {
            System.out.println("More zoom in is impossible");
        }

    }
    public void zoomIn(ActionEvent actionEvent) {
        if (height - 50 > 0 && width - 50 > 0){
            height = height - 50;
            width = width - 50;
            step = step - 5;
            redrawImage(image);
        } else {
            System.out.println("It's already maximum of zoom");
        }
    }
    public void shiftRight(ActionEvent actionEvent) {
        if ((x + step) < widthRes){
            x = x + step;
            redrawImage(image);
        } else {
            System.out.println("Next X cord is more than resolution");
        }
    }
    public void shiftLeft(ActionEvent actionEvent) {
        if (x > 0){
            x = x - step;
            redrawImage(image);
        } else {
            System.out.println("Next X cord is bellow 0");
        }
    }
    public void shiftUp(ActionEvent actionEvent) {
        if (y - step > 0){
            y = y - step;
            redrawImage(image);
        } else {
            System.out.println("Next Y cord is bellow 0");
        }
    }
    public void shiftDown(ActionEvent actionEvent) {
        if ((y + step + 1) < heightRes ){
            y = y + step;
            redrawImage(image);
        } else {
            System.out.println("Next Y cord is more than resolution");
        }
    }
    //Дополнительные методы для удобства работы
    private void findCenterOfImage(Image image){
        x = (int) this.image.getWidth() / 2 - width;
        y = (int) this.image.getHeight() / 2 - height;
    }
    private void getResolution(Image image){
        heightRes = (int) image.getHeight();
        widthRes = (int) image.getWidth();
    }
    private void redrawImage(Image image){
        pixelReader = image.getPixelReader();
        writableImage = new WritableImage(pixelReader, x, y, height, width);
        imageView.setImage(writableImage);
    }

    private void safeCroppedImage(Image image){
        pixelReader = image.getPixelReader();
        writableImage = new WritableImage(pixelReader, x, y, height, width);
        File croppedPhoto = (File) writableImage.getPixelReader();
        WelcomeController.person.setImage(croppedPhoto);
    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        safeCroppedImage(image);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("speciality_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
