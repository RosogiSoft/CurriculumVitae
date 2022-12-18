package com.example.curriculumvitae;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.File;

public class NewAvatarController {
    public ImageView imageView;
    public TextField filePathTextFiled;
    public Label errorImage;

    public File file;

    private boolean fileCheck(File file){
        if (checkSizeOfFile(file) && checkTypeOfFile(file) && checkResolutionOfFile(file)){
            return true;
        } else {
            return false;
        }
    }
    private boolean checkSizeOfFile(File file){
        return file.length() <= 15360;
    }
    private boolean checkTypeOfFile(File file){
        String name = file.getName();
        name = name.split("\\.")[1];
        if (name.equals("png") || name.equals("jpg")) {
            return true;
        } else {
            return true;
        }
    }
    private boolean checkResolutionOfFile(File file){
        return true;
    }
}
