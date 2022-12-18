package com.example.curriculumvitae.helper;

import com.example.curriculumvitae.WelcomeController;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.JcEnumeration;

import java.util.ArrayList;
import java.util.List;

public class GeneratorFilling {

    public List<Object> mainInfo(){
        ArrayList<Object> mainInfoArr = new ArrayList<>();
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(WelcomeController.person.getName(),
                22 * 2, new BooleanDefaultTrue(), JcEnumeration.LEFT));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(
                String.format("Дата рождения: %s", WelcomeController.person.getDateOfBirth()), 14 * 2
                ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph("Город: Москва"));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format("Телефон: %s",
                WelcomeController.person.getPhoneNumber()), 14 * 2
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format(
                "E-mail: %s", WelcomeController.person.getMailAddress()), 14 * 2));
        return mainInfoArr;
    }

    public List<Object> educationInfo(){
        ArrayList<Object> educationArrayList = new ArrayList<>();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraph(
                ""
        ));
        return educationArrayList;
    }
}
