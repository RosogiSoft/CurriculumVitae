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
import java.io.IOException;
import java.util.Objects;

public class AvatarController {
    /*
    TODO:
    1) Добавить в вью вывод ошибки (маленькое изображение по разрешению или слишком большое по весу)
    2) Добавить обработчик выхода за пределы в виде отдельной функции (Все проверки должны быть в отдельном методе дабы
        вызывать его при вызове redrawImage() и scroll() и  не писать все внутри методов обработчиков событий
    3) Релизовать кроп изображения (Найти координату нового старта координатной оси, ширину, динну, обрезать изображение
        и сохранить в качестве текущего)
    4) Реализовать движение картинки при помощи кнопок (x и y координаты)
    5*) Заменить кнопочную реализацию на релизацию движения мышки (стартовая точка по нажатию, финальная по отпусканию,
        проверка пределов, изменение изображение)
    6) Рефакторинг кода
     */
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
    private int widthRatio = 300, heightRatio = 300;
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
            if (checkImageResolution(image)){
                setImage(image);
            }

        }
    }

    private boolean checkImageResolution(Image image){
        if (this.image.getHeight() < heightRatio || this.image.getWidth() < widthRatio){
            System.out.println("Слишком маленькое изображение! Выберите что-то другое");
            return false;
        }
        return true;
    }

    private void setImage(Image image){
        findOriginalResolution();
        findCenterOfImage();
        pixelReader = image.getPixelReader();
        writableImage = new WritableImage(pixelReader, currentPositionX, currentPositionY, heightRatio, widthRatio);
        imageView.setImage(writableImage);
    }

    private void redrawImage(){
        writableImage = new WritableImage(pixelReader, currentPositionX, currentPositionY, heightRatio, widthRatio);
        imageView.setImage(writableImage);
    }


    private void findCenterOfImage(){
        currentPositionX = (int) (originWidth / 2) - (widthRatio / 2);
        currentPositionY = (int) (originHeight / 2) - (heightRatio / 2);
    }

    private void findOriginalResolution(){
        originHeight = image.getHeight();
        originWidth = image.getWidth();
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        differenceX = (int) (mouseEvent.getSceneX() - currentPositionX);
        differenceY = (int) (mouseEvent.getSceneY() - currentPositionY);

        currentPositionX = currentPositionX + differenceX;
        currentPositionY = currentPositionY + differenceY;

        if (currentPositionY > 0 && currentPositionX > 0 && currentPositionY < originHeight && currentPositionX < originWidth){
            redrawImage();
        }
    }
    public void mouseDragged(DragEvent mouseEvent) {
       /* differenceX = (int) (mouseEvent.getSceneX() - currentPositionX);
        differenceY = (int) (mouseEvent.getSceneY() - currentPositionY);

        *//*if ((int) mouseEvent.getSceneX() - originPositionX > 0){
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
        }*//*

        currentPositionX = currentPositionX + differenceX;
        currentPositionY = currentPositionY + differenceY;

        if (currentPositionY > 0 && currentPositionX > 0 && currentPositionY < originHeight && currentPositionX < originWidth){
            redrawImage();
        }*/
    }

    public void mousePressed(MouseEvent mouseEvent) {
        originPositionX = (int) mouseEvent.getSceneX();
        originPositionY = (int) mouseEvent.getSceneY();
    }

    public void scroll(ScrollEvent scrollEvent) {
        System.out.println("Текущее значение DeltaY = " + scrollEvent.getDeltaY());
        int delta = (int) scrollEvent.getDeltaY();
        if (delta < 0){
            if (widthRatio > zoomStep && heightRatio > zoomStep){
                widthRatio = widthRatio - zoomStep;
                heightRatio = heightRatio - zoomStep;
            }
        } else if (delta > 0){
            if (widthRatio < originWidth && heightRatio < originHeight){
                widthRatio = widthRatio + zoomStep;
                heightRatio = heightRatio + zoomStep;
            }
        }
        if (widthRatio > 0 && heightRatio > 0 && widthRatio < originWidth + widthRatio && heightRatio < originHeight + heightRatio){
            redrawImage();
        }

    }

    public void nextButton(ActionEvent actionEvent) throws IOException {
        WelcomeController.person.setImage(selectedFile);
        DataBaseConnect.addData(WelcomeController.person);
        DataBaseConnect.addDataPic(WelcomeController.person);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("speciality_view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void zoomIn(ActionEvent actionEvent) {
    }

    public void zoomOut(ActionEvent actionEvent) {
    }

    public void shiftRight(ActionEvent actionEvent) {
    }

    public void shiftUp(ActionEvent actionEvent) {
    }

    public void shiftDown(ActionEvent actionEvent) {
    }

    public void shiftLeft(ActionEvent actionEvent) {
    }
}
