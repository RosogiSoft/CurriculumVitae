package com.example.curriculumvitae.helper;

import com.example.curriculumvitae.WelcomeController;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.JcEnumeration;

import java.util.ArrayList;
import java.util.List;

public class GeneratorFilling {

    public List<Object> mainInfo() {
        ArrayList<Object> mainInfoArr = new ArrayList<>();
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(WelcomeController.person.getName(),
                22 * 2, new BooleanDefaultTrue(), JcEnumeration.LEFT));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(
                String.format("Дата рождения: %s", WelcomeController.person.getDateOfBirth()), 14 * 2
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph("Город: Москва", 14 * 2));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format("Телефон: %s",
                WelcomeController.person.getPhoneNumber()), 14 * 2
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format(
                "E-mail: %s", WelcomeController.person.getMailAddress()), 14 * 2));
        return mainInfoArr;
    }

    public List<Object> educationInfo() {
        ArrayList<Object> educationArrayList = new ArrayList<>();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraphBold(
                WelcomeController.person.getSpeciality(), 16 * 2, new BooleanDefaultTrue(), JcEnumeration.RIGHT
        ));
        StringBuilder stringBuilder = new StringBuilder();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraph(
                stringBuilder
                        .append(String.format("Дата окончания %s", WelcomeController.person.getYearOfEnding()))
                        .append(", ").append(String.format("Форма обучения: %s", WelcomeController.person.getFormOfStudy()))
                        .toString(), 14 * 2
        ));
        StringBuilder stringBuilder1 = new StringBuilder();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraph(
                stringBuilder1
                        .append(WelcomeController.person.getFaculty()).append(", ")
                        .append(WelcomeController.person.getEstablishment()).append(", ")
                        .append("Москва")
                        .toString()
        ));
        return educationArrayList;
    }

    public List<Object> personalQualities() {
        ArrayList<Object> personalQualitiesArrayList = new ArrayList<>();
        personalQualitiesArrayList.add(ParagraphPreprocess.addTextToParagraph(
                " • Умею работать в команде и общаться с другими людьми.", 14 * 2
        ));
        personalQualitiesArrayList.add(
                ParagraphPreprocess.addTextToParagraph(
                        " • Всегда ответственно выполняю поставленные задачи, " +
                                "  быстро учусь новому и постоянно развиваюсь как профессионал.", 14 * 2
                ));
        personalQualitiesArrayList.add(
                ParagraphPreprocess.addTextToParagraph(
                        " • Всегда нацелен на результат и легко адаптируюсь к новым задачам.", 14 * 2
                ));
        return personalQualitiesArrayList;
    }

    public List<Object> additionalInformation() {
        ArrayList<Object> additionalInformation = new ArrayList<>();
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Водительское удостоверение: %s",
                                WelcomeController.person.getDriverLicense()), 14 * 2
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительный язык: %s",
                                WelcomeController.person.getForeignLanguage()), 14 * 2
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительная связь: %s ", WelcomeController.person.getSocialNetwork())
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительная информация: %s",
                                WelcomeController.person.getAdditionalInfo())
                ));
        return additionalInformation;
    }
}
