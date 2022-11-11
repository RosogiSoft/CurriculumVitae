package com.example.curriculumvitae;

import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstViewController {
    @FXML
    public ChoiceBox<String> speciality;
    @FXML
    public TextField groupNumber;
    @FXML
    public TextField mailAddress;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField dateOfBirth;
    @FXML
    public TextField name;
    @FXML
    public Label errorDate;
    @FXML
    public Label errorNumber;
    @FXML
    public Label errorMail;
    @FXML
    public Label errorGroup;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Pattern pattern;
    private Matcher matcher;

    public void initialize(){
        speciality.getItems().addAll(DataBaseConnect.getSpecializationData());

    }

    public void nextScreen(ActionEvent actionEvent) throws IOException {

        if (checkInput()){
            writeData();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("second_view.fxml")));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    private void writeData(){
        MainController.person.setName(name.getText());
        MainController.person.setDateOfBirth(dateOfBirth.getText());
        MainController.person.setPhoneNumber(phoneNumber.getText());
        MainController.person.setMailAddress(mailAddress.getText());
        MainController.person.setGroupNumber(groupNumber.getText());
        MainController.person.setSpeciality(speciality.getValue());
        //В методе описать забор значения из таблицы в БД по выбранному тексту в боксе
        //MainController.person.setSpecialityCode(specialityCodeGenerator());
    }

    private int specialityCodeGenerator(){
        int code = 1;

        return code;
    }

    private boolean checkInput() {
        if (!checkName() || !checkDateOfBirth() || !checkPhoneNumber()
                || !checkMailAddress() || !checkPhoneNumber() || !checkGroupNumber() || !checkSpeciality()){
            return false;
        }
        return true;

    }

    private boolean checkName(){
        return !name.getText().isEmpty();
        //([А-ЯЁ][а-яё]+[\-\s]?){3,} - regex for Names in case we need them
    }
    
    private boolean checkDateOfBirth(){
        String regex = "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))";
        if (!regexCheck(regex, dateOfBirth.getText())){
            errorDate.setVisible(true);
            errorDate.setText("!");
            return false;
        }
        errorDate.setVisible(false);
        return true;
    }

    private boolean checkPhoneNumber(){

        String regex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        if (regexCheck(regex, phoneNumber.getText())){
            errorNumber.setVisible(true);
            errorNumber.setText("!");
            return false;
        }
        errorNumber.setVisible(false);
        return true;
    }

    private boolean checkMailAddress()  {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        if (regexCheck(regex, mailAddress.getText())){
            errorMail.setVisible(true);
            errorMail.setText("!");
            return false;
        }
        errorMail.setVisible(false);
        return true;
    }

    private boolean checkGroupNumber()   {
        String regex = "[1-5]\\-[А-я][А-я]?[А-я]?[А-я]?[А-я][1-9]?[0-9]\\-?[1-9]?[0-9]";
        if (!regexCheck(regex, groupNumber.getText())){
            errorGroup.setVisible(true);
            errorGroup.setText("!");
            return false;
        }
        errorGroup.setVisible(false);
        return true;
    }

    private  boolean regexCheck(String regex, String input){
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        if (!matcher.matches()){
            return false;
        }
        return true;
    }

    private boolean checkSpeciality(){
        return !speciality.getValue().isEmpty();
    }

            
}
