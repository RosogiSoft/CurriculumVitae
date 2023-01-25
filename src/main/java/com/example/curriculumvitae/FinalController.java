package com.example.curriculumvitae;

import com.example.curriculumvitae.ResumeGenerator.Generator;
import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;

public class FinalController {
    public Label resumePathLabel;

    public void endApplication(ActionEvent actionEvent) throws Exception {
        WelcomeController.person.printData();
        String filePath = System.getProperty("user.home") + File.separator + "Desktop";
        String filename = WelcomeController.person.getName() + ".docx";
        Generator generator = new Generator(filePath + File.separator + filename);

        //Attach photo by id in DataBase
        generator.initFile();
        Stage stage = (Stage) resumePathLabel.getScene().getWindow();
        stage.close();
    }

    private void setResumePathLabel() {
        //тут можно написать обработку для отображения пути сохранения файлика
    }
}
