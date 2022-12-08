package com.example.curriculumvitae.helper;

import com.example.curriculumvitae.MainController;
import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.JcEnumeration;

import java.util.ArrayList;
import java.util.List;

public class GeneratorFilling {

    public List<Object> mainInfo(){
        ArrayList<Object> mainInfoArr = new ArrayList<>();
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(MainController.person.getName(),
                22 * 2, new BooleanDefaultTrue(), JcEnumeration.LEFT));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(
                String.format("Дата рождения: %s", MainController.person.getDateOfBirth()), 14 * 2
                ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph("Город: Москва"));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format("Телефон: %s",
                MainController.person.getPhoneNumber()), 14 * 2
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format(
                "E-mail: %s", MainController.person.getMailAddress()), 14 * 2));
        return mainInfoArr;
    }

    /*public List<Object> educationInfo(){
        ArrayList<Object> educationArrayList = new ArrayList<>();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraph());
        return educationArrayList;
    }*/
}