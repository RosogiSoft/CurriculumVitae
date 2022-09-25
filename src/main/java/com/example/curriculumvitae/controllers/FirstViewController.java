package com.example.curriculumvitae.controllers;

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Pattern pattern;
    private Matcher matcher;

    private final String[] specialityName = {
            "Автоматизация технологических процессов и производств (по отраслям)",
            "Земельно-имущественные отношения",
            "Инфокоммуникационные сети и системы связи",
            "Информационная безопасность телекоммуникационных систем",
            "Информационные системы (по отраслям)",
            "Информационные системы и программирование",
            "Компьютерные сети",
            "Многоканальные телекоммуникационные системы",
            "Монтаж, техническое обслуживание и ремонт электронных приборов и устройств",
            "Монтажник радиоэлектронной аппаратуры и приборов",
            "Обеспечение информационной безопасности телекоммуникационных систем",
            "Оператор нефтепереработки",
            "Организация и технология защиты информации",
            "Оснащение средствами автоматизации технологических процессов и производств (по отраслям)",
            "Почтовая связь",
            "Программирование в компьютерных системах",
            "Радиосвязь, радиовещание и телевидение",
            "Сетевое и системное администрирование",
            "Сети связи и системы коммутации",
            "Системы и средства диспетчерского управления",
            "Средства связи с подвижными объектами",
            "Техническое обслуживание и ремонт автомобильного транспорта",
            "Техническое обслуживание и ремонт радиоэлектронной техники (по отраслям)",
            "Экономика и бухгалтерский учет (по отраслям)",
            "Электронные приборы и устройства",
            "Электроснабжение (по отраслям)",
            "Другое..."
    };

    public void initialize(){
        speciality.getItems().addAll(specialityName);
    }

    public void nextScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("second_view.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void checkInput(){
        checkName();
        checkDateOfBirth();
        checkPhoneNumber();
        checkMailAddress();
        checkGroupNumber();
        checkSpeciality();

    }

    private void checkName(){
        String regex = "";

    }

    private void checkDateOfBirth(){
        String regex = "(0?[1-9]|[12][0-9]|3[01])([\\.\\\\\\/-])(0?[1-9]|1[012])\\2(((19|20)\\d\\d)|(\\d\\d))";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(dateOfBirth.getText());

        if (!matcher.matches()){
            System.out.println("error in DoB");
            return;
        }

    }

    private void checkPhoneNumber(){
        String regex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(phoneNumber.getText());

        if (!matcher.matches()){
            System.out.println("error in PN");
            return;
        }
    }

    private void checkMailAddress(){
        String regex = "";

    }

    private void checkGroupNumber(){
        String regex = "";

    }

    private void checkSpeciality(){
        String regex = "";
    }
}
