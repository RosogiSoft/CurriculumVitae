package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecialityController {

    public CheckBox checkBox1;
    public CheckBox checkBox2;
    public CheckBox checkBox3;
    public CheckBox checkBox4;
    public CheckBox checkBox5;
    public CheckBox checkBox6;
    public CheckBox checkBox7;
    public CheckBox checkBox8;
    public CheckBox checkBox9;
    public CheckBox checkBox10;
    public CheckBox checkBox11;
    public CheckBox checkBox12;
    public CheckBox checkBox13;
    public CheckBox checkBox14;
    public CheckBox checkBox15;
    ArrayList<CheckBox> checkBoxes;

    public void initialize() {
        setTextBySpecCode(setCheckBoxArrayList(), WelcomeController.person.getSpecialityCode());
    }

    private ArrayList<CheckBox> setCheckBoxArrayList() {
        checkBoxes = new ArrayList<>();
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);
        checkBoxes.add(checkBox4);
        checkBoxes.add(checkBox5);
        checkBoxes.add(checkBox6);
        checkBoxes.add(checkBox7);
        checkBoxes.add(checkBox8);
        checkBoxes.add(checkBox9);
        checkBoxes.add(checkBox10);
        checkBoxes.add(checkBox11);
        checkBoxes.add(checkBox12);
        checkBoxes.add(checkBox13);
        checkBoxes.add(checkBox14);
        checkBoxes.add(checkBox15);
        return checkBoxes;
    }

    private void setTextBySpecCode(List<CheckBox> listCheckBox, int specCode) {
        ArrayList<String> checkBoxArrayString = DataBaseConnect.getSpecializationCheckBox(specCode);
        int i = 0;
        for (String contString : checkBoxArrayString) {
            if (contString != null) {
                listCheckBox.get(i).setText(contString);
            } else {
                listCheckBox.get(i).setVisible(false);
            }
            i++;
        }
    }

    public void nextButton(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("education_veiw.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
