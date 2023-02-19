package com.example.curriculumvitae.ResumeGenerator;

import com.example.curriculumvitae.WelcomeController;
import com.example.curriculumvitae.databaseModel.DataBaseConnect;
import com.example.curriculumvitae.helper.GeneratorFilling;
import com.example.curriculumvitae.helper.ParagraphPreprocess;
import com.example.curriculumvitae.helper.TablePreprocess;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private final String path;
    private final GeneratorFilling generatorFilling = new GeneratorFilling();

    public Generator(String path) {
        this.path = path;
    }


    public void initFile() throws Exception {
        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.getContent().add(
                ParagraphPreprocess.addImageToParagraph(
                        ParagraphPreprocess.simpleAddImageToP(
                                wordPackage, Files.readAllBytes(
                                        Path.of("shapka.png")
                                ), "Logo", "Logo", 1, 2, false
                        )));
        mainDocumentPart.getContent().add(setTable(wordPackage, ParagraphPreprocess.simpleAddImageToP(
                wordPackage, WelcomeController.person.getImageFile(), "UserPhoto",
                "UserPhoto", 3, 4, false, 2360
        )));
        File docxFile = new File(path);
        wordPackage.save(docxFile);
    }

    private Tbl setTable(WordprocessingMLPackage wordPackage, Inline image) {
        int writableWidth = wordPackage
                .getDocumentModel()
                .getSections()
                .get(0)
                .getPageDimensions()
                .getWritableWidthTwips();
        List<Object> tableItems = generatorFilling.addElementsFirstCol(image);
        List<Object> descriptionCells = new ArrayList<>();
        int columnNumber = 2;
        Tbl table = TblFactory.createTable(6, columnNumber, writableWidth / columnNumber);
        int j = 0;
        List<Object> rows = table.getContent();
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (int i = 0; i < cells.size(); i++) {
                if (i % 2 == 0) {
                    Tc cell = (Tc) cells.get(i);
                    TablePreprocess.setCellStyleFirstCol(cell, 3105);
                    cell.getContent().add(tableItems.get(j));
                    j++;
                }
            }
        }
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (int i = 0; i < cells.size(); i++) {
                if (i % 2 != 0) {
                    Tc cell = (Tc) cells.get(i);
                    TablePreprocess.setCellStyleSecondColl(cell, 7145);
                    descriptionCells.add(cell);
                }
            }
        }
        for (int i = 0; i < descriptionCells.size(); i++) {
            Tc cell = null;
            switch (i) {
                case 0:
                    cell = (Tc) descriptionCells.get(i);
                    for (int c = 0; c < generatorFilling.mainInfo().size(); c++) {
                        cell.getContent().add(generatorFilling.mainInfo().get(c));
                    }
                    break;
                case 1:
                    cell = (Tc) descriptionCells.get(i);
                    for (int c = 0; c < generatorFilling.educationInfo().size(); c++) {
                        cell.getContent().add(generatorFilling.educationInfo().get(c));
                    }
                    break;
                case 2:
                    break;

                case 3:
                    cell = (Tc) descriptionCells.get(i);
                    cell.getContent().add(
                            ParagraphPreprocess.addTextToParagraph(
                                    WelcomeController.person.getSpecialityInfo(), 14 * 2)
                    );
                    cell.getContent().add(ParagraphPreprocess.addTextToParagraph(
                            String.format("Дополнительные компетенции: %s",
                                    WelcomeController.person.getAdditionalCompetencies()), 14 * 2));
                    break;

                case 4:
                    cell = (Tc) descriptionCells.get(i);
                    for (int c = 0; c < generatorFilling.personalQualities().size(); c++) {
                        cell.getContent().add(generatorFilling.personalQualities().get(c));
                    }
                    break;
                case 5:
                    cell = (Tc) descriptionCells.get(i);
                    for (int c = 0; c < generatorFilling.additionalInformation().size(); c++) {
                        cell.getContent().add(generatorFilling.additionalInformation().get(c));
                    }
                    break;
            }
        }
        return table;
    }
}