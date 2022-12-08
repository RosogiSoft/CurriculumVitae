package com.example.curriculumvitae;

import com.example.curriculumvitae.ResumeGenerator.Generator;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AditionalInfoController {

    public TextArea aditionalTextField;

    public void nextButton(ActionEvent actionEvent) throws Exception {
        String filename = "example.docx";
        Generator generator = new Generator("src/main/resources/com/example/curriculumvitae/wordExamples/" + filename);
        generator.initFile(1);
    }
}
