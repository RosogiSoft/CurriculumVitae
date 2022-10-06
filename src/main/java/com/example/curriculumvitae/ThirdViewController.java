package com.example.curriculumvitae;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class ThirdViewController {

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

    public void initialize(){

        setCheckBoxArrayList();
    }

    public void setCheckBoxArrayList(){
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

        switch (MainController.person.getSpeciality()){
            case "Инфокоммуникационные сети и системы связи":
                for (CheckBox checkBox : checkBoxes){
                    checkBox.setText("хуй");
                }break;

            case "Информационная безопасность телекоммуникационных систем":
                for (CheckBox checkBox : checkBoxes){
                    checkBox.setText("залупа");
                }break;
        }
    }




}
