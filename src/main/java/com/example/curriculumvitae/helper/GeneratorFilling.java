package com.example.curriculumvitae.helper;

import com.example.curriculumvitae.WelcomeController;
import com.topologi.diffx.Main;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.JcEnumeration;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class GeneratorFilling {

    private final int HEADING_SIZE = 22 * 2;
    private final int MAIN_SIZE = 12 * 2;
    private final int BIG_SIZE = 16 * 2;

    public List<Object> addElementsFirstCol(Inline image) {
        List<Object> array = new ArrayList<>();
        array.add(ParagraphPreprocess.addImageToParagraph(image, JcEnumeration.CENTER));
        array.add(ParagraphPreprocess.addTextToParagraph("Образование", MAIN_SIZE, new BooleanDefaultTrue(),
                JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Дополнительное образование", MAIN_SIZE, new BooleanDefaultTrue(),
                JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Профессиональные навыки", MAIN_SIZE, new BooleanDefaultTrue(),
                JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Личные качества", MAIN_SIZE, new BooleanDefaultTrue(),
                JcEnumeration.RIGHT));
        array.add(ParagraphPreprocess.addTextToParagraph("Дополнительная информация", MAIN_SIZE, new BooleanDefaultTrue(),
                JcEnumeration.RIGHT));
        return array;
    }
    public List<Object> mainInfo() {
        ArrayList<Object> mainInfoArr = new ArrayList<>();
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(WelcomeController.person.getName(),
                HEADING_SIZE, new BooleanDefaultTrue(), JcEnumeration.LEFT));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(
                String.format("Дата рождения: %s", WelcomeController.person.getDateOfBirth()), MAIN_SIZE
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph("Город: Москва", MAIN_SIZE));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format("Телефон: %s",
                WelcomeController.person.getPhoneNumber()), MAIN_SIZE
        ));
        mainInfoArr.add(ParagraphPreprocess.addTextToParagraph(String.format(
                "E-mail: %s", WelcomeController.person.getMailAddress()), MAIN_SIZE));
        return mainInfoArr;
    }

    public List<Object> educationInfo() {
        ArrayList<Object> educationArrayList = new ArrayList<>();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraphBold(
                WelcomeController.person.getSpeciality(), BIG_SIZE, new BooleanDefaultTrue(), JcEnumeration.RIGHT
        ));
        StringBuilder stringBuilder = new StringBuilder();
        educationArrayList.add(ParagraphPreprocess.addTextToParagraph(
                stringBuilder
                        .append(String.format("Дата окончания %s", WelcomeController.person.getYearOfEnding()))
                        .append(", ").append(String.format("Форма обучения: %s", WelcomeController.person.getFormOfStudy()))
                        .toString(), MAIN_SIZE
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
       /* personalQualitiesArrayList.add(ParagraphPreprocess.addTextToParagraph(
                " • Умею работать в команде и общаться с другими людьми.", MAIN_SIZE
        ));
        personalQualitiesArrayList.add(
                ParagraphPreprocess.addTextToParagraph(
                        " • Всегда ответственно выполняю поставленные задачи, " +
                                "  быстро учусь новому и постоянно развиваюсь как профессионал.", MAIN_SIZE
                ));
        personalQualitiesArrayList.add(
                ParagraphPreprocess.addTextToParagraph(
                        " • Всегда нацелен на результат и легко адаптируюсь к новым задачам.", MAIN_SIZE
                ));*/
        if (WelcomeController.person.getSoftSkills() != null){
            for (String initialString : WelcomeController.person.getSoftSkills()){
                personalQualitiesArrayList.add(ParagraphPreprocess
                        .addTextToParagraph(String.format(" • %s", initialString), MAIN_SIZE));
            }
        }
        return personalQualitiesArrayList;
    }

    public List<Object> additionalInformation() {
        ArrayList<Object> additionalInformation = new ArrayList<>();
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Водительское удостоверение: %s",
                                WelcomeController.person.getDriverLicense()), MAIN_SIZE
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительный язык: %s",
                                WelcomeController.person.getForeignLanguage()), MAIN_SIZE
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительная связь: %s ", WelcomeController.person.getSocialNetwork()),
                        MAIN_SIZE
                ));
        additionalInformation.add(
                ParagraphPreprocess.addTextToParagraph(
                        String.format(" • Дополнительная информация: %s",
                                WelcomeController.person.getAdditionalInfo()),
                        MAIN_SIZE
                ));
        return additionalInformation;
    }
}
